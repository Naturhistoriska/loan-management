package se.nrm.dina.mongodb.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ReadConcern;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map; 
import java.util.UUID; 
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.Oid; 
import se.nrm.dina.mongodb.loan.vo.Collection;
import se.nrm.dina.mongodb.loan.vo.Counters;
import se.nrm.dina.mongodb.loan.vo.Loan;
import se.nrm.dina.mongodb.util.Util;

/**
 *
 * @author idali
 */
@Stateless
@Slf4j
public class MongoJDBC implements Serializable {
  
  private MongoClient mongo;
  protected DB db;
  private Jongo jongo;

  private final String LOCAL_HOST = "localhost";
  private final String DB_HOST = "db";

  private String HOST = null;
  private final int PORT = 27017;

  private final String LOAN_COLLECTION_NAME = "loan";
  private final String COLLECTION_COLLECTION_NAME = "collection";
  private final String COUNTERS_COLLECTION_NAME = "counters";

  private MongoCollection loanCollection;
  private MongoCollection collectionCollection;
  private MongoCollection countersCollection;
  
  private ObjectMapper mapper;

  public MongoJDBC() {
    String nodeName;
    InetAddress inetAddress = null;
    try {
      inetAddress = InetAddress.getLocalHost();
    } catch (UnknownHostException une) {
      log.warn(une.getMessage());
    }
    if (inetAddress != null) {
      nodeName = inetAddress.getHostName();

      log.info("host : {}", nodeName);
      if (nodeName.contains("local")) {
        HOST = LOCAL_HOST;
      } else {
        HOST = DB_HOST;
      }
    }
  }

  @PostConstruct
  void init() {
     
    mongo = new MongoClient(HOST, PORT);
    db = mongo.getDB("loans");
    jongo = new Jongo(db);
    loanCollection = jongo.getCollection(LOAN_COLLECTION_NAME);
    collectionCollection = jongo.getCollection(COLLECTION_COLLECTION_NAME);
    countersCollection = jongo.getCollection(COUNTERS_COLLECTION_NAME);
  }
  
  
  public void saveCollection(Collection collection) {
    try {
      log.info("saveCollection : {}", collection);
      
      mapper = new ObjectMapper(); 
      collectionCollection.insert(mapper.writeValueAsString(collection));  
    } catch (JsonProcessingException ex) { 
      log.error(ex.getMessage());
    }
    
  }
 
  public List<Collection> findAllScientificCollections() {
    log.info("findAllScientificCollections");
    Iterable<Collection> collections = collectionCollection
            .find("{group: {$ne: 'Non scientific'}}").sort("{name:1}").as(Collection.class);

    List<Collection> results = new ArrayList<>();
    Util.stream(collections).forEach(c -> results.add(c));
    return results;
  }
  
  public int findCurrentYearCountByPurpose(String purpose, String fromDate, String endDate) {

    StringBuilder sb = new StringBuilder();
    sb.append("{$and:[{'purpose': '");
    sb.append(purpose);
    sb.append("'}, {submitDate: {$gte: '");
    sb.append(fromDate);
    sb.append("', $lte: '");
    sb.append(endDate);
    sb.append("'}}]}"); 

    Iterable<Loan> loans = loanCollection.find(sb.toString()).as(Loan.class);

    List<Loan> results = new ArrayList<>();
    Util.stream(loans).forEach(l -> results.add(l));
    return results.size();
  }
  
  public int findTotalCountByPurpose(String purpose) {

    Iterable<Loan> loans = loanCollection.find("{purpose: '" + purpose + "'}").as(Loan.class);
    List<Loan> results = new ArrayList<>();
    Util.stream(loans).forEach(l -> results.add(l));
    return results.size();
  }
  
  public List<Loan> findAllLoans() {
    log.info("findAllLoans");

    Iterable<Loan> loans = loanCollection.find().sort("{_id:-1}").as(Loan.class);
    List<Loan> results = new ArrayList<>();
    Util.stream(loans).forEach(l -> results.add(l));
    return results;
  }
 
  public int getNextSequence() {
    Counters counters = countersCollection.findAndModify("{_id: 'loanid'}")
            .with("{$inc: {seq: 1}}").returnNew().as(Counters.class);
    return counters.getSeq();
  }
  
  public void updateNonScientificLoansManager(String email) {
    log.info("updateNonScientificLoansManager: {}", email);

    String query = "{name: 'Non scientific'}";
    Collection collection = collectionCollection.findOne(query).as(Collection.class);
    collection.setManager(email);
    collection.setEmail(email);
    collectionCollection.update(query).with(collection);
  }
  
  public List<Collection> findCollectionsByDepartment(String department) {
    log.info("findCollectionsByDepartment : {} ", department);

    Iterable<Collection> collections = collectionCollection
            .find("{department: '" + department + "'}").sort("{name:1}")
            .as(Collection.class); 
    return Util.stream(collections)
            .collect(Collectors.toList());  
  }
  
//  private Iterable<Collection> findAllCollectionByDepartment(String department, String sort) {
//    return collectionCollection.find("{department: '" + department + "'}").sort("{" + sort + ":1}").as(Collection.class);
//  }
// 
  public void removeWholeCollection(String groupName) {
    collectionCollection.remove("{group: '" + groupName + "'}");
  }
    
 
  public List<String> findAllCollectionNameListByDepartmentName(String department) {
    log.info("findAllCollectionNameListByDepartmentName : {}", department);

    return Util.stream(findCollectionsByDepartment(department))
            .map(Collection::getName)
            .collect(Collectors.toList());
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  


  

  

//  public Map<String, List<Collection>> findAllCollection() {
//    log.info("findAllCollection");
//    Iterable<Collection> collections = collectionCollection.find().sort("{name:1}").as(Collection.class);
//
//    List<Collection> results = Util.stream(collections)
//            .collect(Collectors.toList());
//
//    Map<String, List<Collection>> map = new TreeMap<>();
//    results.stream().forEach((Collection c) -> {
//      if (map.containsKey(c.getGroup())) {
//        map.get(c.getGroup()).add(c);
//      } else {
//        List<Collection> list = new ArrayList<>();
//        list.add(c);
//        map.put(c.getGroup(), list);
//      }
//    });
//    return map;
//  }

  /**
   * findUniqueCuratorsEmails returns a list of unique curator's emails by each
   * department
   *
   * @param department
   * @return List<String>
   */
//  public List<String> findUniqueCuratorsEmails(String department) {
//    log.info("findUniqueCuratorsEmails : {}", department);
//
//    return Util.stream(findAllCollectionByDepartment(department, "email"))
//            .filter(c -> c.getEmail() != null)
//            .map(Collection::getEmail)
//            .distinct()
//            .collect(Collectors.toList());
//  }

  

  

  /**
   *
   * @param id
   * @return
   */
  // TODO: this method seems return all the collection name, not the collection group name.  But this method seems not in use?
//  public List<String> findAllCollectionGroupNames() {
//    log.info("findAllCollectionGroupNames");
//    Iterable<Collection> collections = collectionCollection.find().as(Collection.class);
//
//    Util.stream(collections).map(Collection::getName);
//
//    List<String> results = new ArrayList<>();
//    Util.stream(collections).forEach(c -> results.add(c.getName()));
//    return results;
//  }
  
  public Loan fineLoanById(String id) {
    log.info("fineLoanById : {}", id);

    return loanCollection.findOne("{_id: '" + id + "'}").as(Loan.class);
  }

  private String buildSubString(List<String> list) {
    StringBuilder sb = new StringBuilder();
    list.stream().map((s) -> {
      sb.append("'");
      sb.append(s);
      return s;
    }).forEach((_item) -> {
      sb.append("', ");
    });
    String string = StringUtils.substringBeforeLast(sb.toString(), ",");

    return string;
  }

  public List<Loan> findLoansWithQueryMap(Map<String, List<String>> map, String dateForSearch, String fromDate, String toDate, boolean searchDate) {
    log.info("findLoansWithQueryMap");

    StringBuilder sb = new StringBuilder();

    sb.append("{$and:[");

    map.entrySet().stream().map((entry) -> {
      sb.append("{'");
      sb.append(entry.getKey());
      return entry;
    }).map((entry) -> {
      sb.append("': {'$in': [");
      sb.append(buildSubString(entry.getValue()));
      return entry;
    }).forEach((_item) -> {
      sb.append("]}}, ");
    });

    String query;
    if (searchDate) {
      sb.append("{'");
      sb.append(dateForSearch);
      sb.append("': {$gte: '");
      sb.append(fromDate);
      sb.append("', $lte: '");
      sb.append(toDate);
      sb.append("'}}]}");
      query = sb.toString();
    } else {
      query = StringUtils.substringBeforeLast(sb.toString(), ",") + "]}";
    }

    Iterable<Loan> loans = loanCollection.find(query).as(Loan.class);
    List<Loan> results = new ArrayList<>();
    Util.stream(loans).forEach(l -> results.add(l));
    return results;
  }

  public List<Loan> findLoansWithQuery(List<String> list, Map<String, String> map) {
    log.info("findLoansWithQuery");

    StringBuilder sb = new StringBuilder();
    sb.append("{$and:[");

    map.entrySet().stream().map((entry) -> {
      sb.append("{");
      sb.append(entry.getKey());
      return entry;
    }).map((entry) -> {
      sb.append(": '");
      sb.append(entry.getValue());
      return entry;
    }).forEach((_item) -> {
      sb.append("'},");
    });

    if (list != null) {
      sb.append(" { 'status' : {'$in': [");

      list.stream().map((s) -> {
        sb.append("'");
        sb.append(s);
        return s;
      }).forEach((_item) -> {
        sb.append("', ");
      });
    }
    String query = StringUtils.substringBeforeLast(sb.toString(), ",") + "]}}]}";
    Iterable<Loan> loans = loanCollection.find(query).as(Loan.class);
    List<Loan> results = new ArrayList<>();
    Util.stream(loans).forEach(l -> results.add(l));
    return results;
  }

  public List<Loan> findLoansWithQuery(Map<String, String> queryMap) {

    log.info("findLoansWithQuery");

    StringBuilder sb = new StringBuilder();
    sb.append("{$and:[");
    queryMap.entrySet().stream().map((entry) -> {
      sb.append("{");
      sb.append(entry.getKey());
      return entry;
    }).map((entry) -> {
      sb.append(": '");
      sb.append(entry.getValue());
      return entry;
    }).forEach((_item) -> {
      sb.append("'},");
    });
    String query = StringUtils.substringBeforeLast(sb.toString(), ",") + "]}";

    Iterable<Loan> loans = loanCollection.find(query).as(Loan.class);
    List<Loan> results = new ArrayList<>();
    Util.stream(loans).forEach(l -> results.add(l));
    return results;
  }

  public void updateCollectionGroupName(String oldGroup, String newGroup, String newManager, String department) {
    log.info("updateCollectionGroupName");

    StringBuilder sb = new StringBuilder();
    sb.append("{$set: {group: '");
    sb.append(newGroup);
    sb.append("', manager: '");
    sb.append(newManager);
    sb.append("'}}");
    collectionCollection.update("{group: '" + oldGroup + "'}").multi().with(sb.toString());
  }

  public void updateLoan(Loan loan) {
    log.info("updateCollection");
    loanCollection.update("{_id: '" + loan.getId() + "'}").with(loan); 
  }

  public void updateLoan(Loan loan, boolean removeReleventCollection) {
    loanCollection.update("{_id: '" + loan.getId() + "'}").with(loan); 
    if(removeReleventCollection) {
      loanCollection.findAndModify("{_id: '" + loan.getId() + "'}").with("{$unset: { releventCollection: \"\" }}").as(Loan.class);
    } 
  }

  public void updateCollection(Collection collection) {
    log.info("updateCollection");
    collectionCollection.update(Oid.withOid(collection.getId())).with(collection);
  }

  public void updateAllCollections(String name, String email, String group) {
    log.info("updateCollection");

    String query = "{name: '" + name + "'}";
    Collection collection = collectionCollection.findOne(query).as(Collection.class);

    collection.setGroup(group);
    collectionCollection.update(query).with(collection);
  }

  

  public Map<String, List<String>> findAllCollectionNames() {
    log.info("findAllCollection");
    Iterable<Collection> collections = collectionCollection.find().as(Collection.class);

    List<Collection> results = new ArrayList<>();
    Util.stream(collections).forEach(c -> results.add(c));

    Map<String, List<String>> map = new LinkedHashMap<>();
    results.stream().forEach((Collection c) -> {
      if (map.containsKey(c.getGroup())) {
        map.get(c.getGroup()).add(c.getName());
      } else {
        List<String> list = new ArrayList<>();
        list.add(c.getName());
        map.put(c.getGroup(), list);
      }
    });
    return map;
  }

  public Collection findCollection(String name, String field) {
    return collectionCollection.findOne("{" + field + ": '" + name + "'}").as(Collection.class);
  }
 
  public void removeCollection(Collection collection) {
    collectionCollection.remove("{name: '" + collection.getName() + "'}");
  }
 
  public void save(Loan loan) {
    log.info("save : {}", loan);
    try { 
      mapper = new ObjectMapper();
      loanCollection.insert(mapper.writeValueAsString(loan)); 
    } catch (JsonProcessingException ex) {
      log.error(ex.getMessage());
    }
  }

  

  public boolean uuidExist(UUID uuid) {
    log.info("uuidExist : {}", uuid.toString());
    Loan loan = loanCollection.findOne("{uuid: '" + uuid.toString() + "'}").as(Loan.class);
    return loan != null;
  }

  @PreDestroy
  public void destroyBean() {
    log.info("The bean is being destroyed now, be careful!!!");
    mongo.close();
  }
}
