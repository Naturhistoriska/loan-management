package se.nrm.dina.loan.web.service;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse; 
import se.nrm.dina.loan.web.vo.SolrRecord;
import se.nrm.dina.mongodb.loan.vo.Sample;

/**
 *
 * @author idali
 */
@Stateless
@Slf4j
public class SolrClient implements Serializable { 
  private final static String LOCAL_SOLR_URL = "http://localhost:8983/solr/";                             // local
  private final static String REMOTE_SOLR_URL_LOAN = "http://dina-loans:8983/solr/";                      // dina-portal  
  private final static String REMOTE_SOLR_URL_AS = "http://dina-db.nrm.se:8983/solr/";

  private static String SOLR_URL;

  private final static String EXCLUDED_COLLECTIONS = " -cln:(262144 294912 458752 491521)";

  private StringBuilder sb;

  private final SolrServer solrServer;
  private SolrQuery solrQuery;

  private String host;

  public SolrClient() {

    InetAddress inetAddress = null;
    try {
      inetAddress = InetAddress.getLocalHost();
    } catch (UnknownHostException une) {
      log.warn(une.getMessage());
    }
    if (inetAddress != null) {
      host = inetAddress.getHostName();
    }

    if (host.toLowerCase().contains("local")) {
      SOLR_URL = LOCAL_SOLR_URL;
    } else if (host.toLowerCase().contains("dina-loans")) {
      SOLR_URL = REMOTE_SOLR_URL_LOAN;
    } else {
      SOLR_URL = REMOTE_SOLR_URL_AS;
    }
    solrServer = new HttpSolrServer(SOLR_URL);
  }

  public SolrRecord searchByCatalogNumber(String catalognumber) {
    String searchTaxt = "cn:" + catalognumber + EXCLUDED_COLLECTIONS;
    solrQuery = new SolrQuery();
    solrQuery.setQuery(searchTaxt);
    try {
      QueryResponse response = solrServer.query(solrQuery);

      if (response.getResults().getNumFound() > 0) {
        return response.getBeans(SolrRecord.class).get(0);
      }
    } catch (SolrServerException ex) {
      log.warn(ex.getMessage());
    }
    return null;
  }

  public List<SolrRecord> searchByTaxa(Sample sample, String field, String catalogNumbers) {

    log.info("searchByTaxa :{}", solrServer);

    List<SolrRecord> solrRecords = new ArrayList<>();
    buildSearchTextString(sample, field, catalogNumbers);
    log.info("search text : {}", sb.toString().trim());
    try {
      solrQuery = new SolrQuery();
      solrQuery.setQuery(sb.toString().trim());

      solrQuery.setStart(0);
      solrQuery.setRows(5000);
      QueryResponse response = solrServer.query(solrQuery);

      solrRecords = response.getBeans(SolrRecord.class);
    } catch (SolrServerException ex) {
      log.warn(ex.getMessage());
    }
    return solrRecords;
  }

  private void buildSubString(String text, String field, StringBuilder sb) {

    if (text.contains("(") || text.contains(")") || text.contains(",")) {
      replaceChars(text);
    }

    String[] strings = text.split(" ");
    if (strings.length > 1) {
      sb.append("+(");
      for (String s : strings) {
        if (!s.isEmpty()) {
          sb.append("+");
          sb.append(field);
          sb.append(":*");
          sb.append(s);
          sb.append("* ");
        }
      }
      sb.append(") ");
    } else {
      sb.append("+");
      sb.append(field);
      sb.append(":*");
      sb.append(text);
      sb.append("* ");
    }
  }

  private void buildSearchTextString(Sample sample, String field, String catalogNumbers) { 
    log.info("sample : {} -- {}", sample.getFamily(), sample.getGenus());

    sb = new StringBuilder();

    if (sample.getFamily() != null && !sample.getFamily().isEmpty()) {
      buildSubString(sample.getFamily(), "fm", sb);
    }

    switch (field) {
      case "sp":
        if (sample.getGenus() != null && !sample.getGenus().isEmpty()) {
          buildSubString(sample.getGenus(), "gn", sb);
        }
        if (sample.getSpecies() != null && !sample.getSpecies().isEmpty()) {
          buildSubString(sample.getSpecies(), field, sb);
        }
        break;
      case "gn":
        if (sample.getGenus() != null && !sample.getGenus().isEmpty()) {
          buildSubString(sample.getGenus(), "gn", sb);
        }
        break;
    }

    sb.append("+taxarank:220");

    if (catalogNumbers != null && !catalogNumbers.isEmpty()) {
      sb.append(" -cn:(");
      sb.append(catalogNumbers);
      sb.append(")");
    }
    sb.append(EXCLUDED_COLLECTIONS);
  }

  private String replaceChars(String value) {
    String s = value.replaceAll("[\\[\\](),]", " ");
    return s.trim();
  }
}
