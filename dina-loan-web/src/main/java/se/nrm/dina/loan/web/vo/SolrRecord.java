package se.nrm.dina.loan.web.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.beans.Field;

/**
 *
 * @author idali
 */
public class SolrRecord implements Serializable {

  Calendar cal = Calendar.getInstance();

  @Field("id")
  String id;

  @Field("cn")
  String catalogNum;

  @Field("sp")
  String fullname;

  @Field("fm")
  String family;

  @Field("gn")
  String genus;

  @Field("lc")
  String locality;

  @Field("cy")
  String country;

  @Field("col")
  String[] collector;

  @Field("auth")
  String[] author;

  @Field("pr")
  String[] preparations;

  @Field("sto")
  String[] storage;

  @Field("tsn")
  String type;

  @Field("sd")
  Date startDate;

//    @Field("img")
//    String[] imgMbids;
  public String getCatalogNum() {
    return catalogNum;
  }

  public void setCatalogNum(String catalogNum) {
    this.catalogNum = catalogNum;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String[] getAuthor() {
    return author;
  }

  public void setAuthor(String[] author) {
    this.author = author;
  }

  public String getAuth() {
    return StringUtils.join(author, "; ");
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getFamily() {
    return family;
  }

  public void setFamily(String family) {
    this.family = family;
  }

  public String getGenus() {
    return genus;
  }

  public void setGenus(String genus) {
    this.genus = genus;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLocalityWithCountry() {
    StringBuilder sb = new StringBuilder();
    if (locality != null && !locality.isEmpty()) {
      sb.append(locality);
      if (country != null && !country.isEmpty()) {
        sb.append(", ");
      }
    }
    if (country != null && !country.isEmpty()) {
      sb.append(country);
    }

    return sb.toString().trim();
  }

  public String[] getCollector() {
    return collector;
  }

  public void setCollector(String[] collector) {
    this.collector = collector;
  }

  public String getCollectors() {
    return StringUtils.join(collector, "; ");
  }

  public String[] getPreparations() {
    return preparations;
  }

  public void setPreparations(String[] preparations) {
    this.preparations = preparations;
  }

  public String getPreparationString() {
    return StringUtils.join(preparations, "; ");
  }

  public String[] getStorage() {
    return storage;
  }

  public void setStorage(String[] storage) {
    this.storage = storage;
  }

  public String getStorageString() {
    if (storage != null) {
      return StringUtils.join(storage, "; ");
    } else {
      return "";
    }
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getCollectedYear() {

    if (startDate == null) {
      return "";
    }
    cal.setTime(startDate);

    int year = cal.get(Calendar.YEAR);
    return String.valueOf(year);
  }
}
