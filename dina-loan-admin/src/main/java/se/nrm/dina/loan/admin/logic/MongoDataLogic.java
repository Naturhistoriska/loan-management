package se.nrm.dina.loan.admin.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.loan.admin.util.Util;
import se.nrm.dina.loan.admin.vo.CollectionManager;
import se.nrm.dina.mongodb.jdbc.MongoJDBC;
import se.nrm.dina.mongodb.loan.vo.Collection;
import se.nrm.dina.mongodb.loan.vo.Loan;

/**
 *
 * @author idali
 */
@Slf4j
public class MongoDataLogic implements Serializable {

  @Inject
  private MongoJDBC mongo;
 
  public List<Loan> findAllLoans() {
    return mongo.findAllLoans();
  }

  public void updateNonScientificLoansManager(String manager) {
    mongo.updateNonScientificLoansManager(manager);
  }

  public Map<String, List<Collection>> findCollectionsByDepartment(String department) {
    List<Collection> collections = mongo.findCollectionsByDepartment(department);
    Map<String, List<Collection>> map = new TreeMap<>();
    collections.stream()
            .forEach((Collection c) -> {
              List<Collection> list = new ArrayList<>();
              if (map.containsKey(c.getGroup())) {
                list = map.get(c.getGroup());
              }
              list.add(c);
              map.put(c.getGroup(), list);
            });
    return map;
  }

  
  public List<String> findCollectionNamesByDepartment(String department) {
    return mongo.findAllCollectionNameListByDepartmentName(department);
  }
  
  public String getCollectionManager(Map<String, List<Collection>> map, String typeOfLoan) {
    return map.entrySet().stream()
            .filter(entry -> entry.getKey().equals(typeOfLoan))
            .map(e -> e.getValue().get(0).getManager())
            .findFirst().get();
  }

  public List<String> getCollectionGroupName(Map<String, List<Collection>> map) {
    return map.entrySet().stream()
            .map(entry -> entry.getKey())
            .collect(Collectors.toList());
  }

  public List<CollectionManager> getCollectionManagers(Map<String, List<Collection>> map) {
    return map.entrySet().stream()
            .map(entry -> new CollectionManager(entry.getKey(), entry.getKey(), entry.getValue().get(0).getManager()))
            .collect(Collectors.toList());
  }

  public void removeCollection(Collection collection) {
    mongo.removeCollection(collection);
  }

  public boolean addNewCollection(List<Collection> group, Collection collection) {
    if (group != null && !group.isEmpty()) {
      if (isCollectionExist(group, collection.getName())) {
        return false;
      }
    } 
    mongo.saveCollection(collection);
    return true; 
  }
  
  public boolean editCollection(Collection editCollection, List<Collection> collectionList) { 
    if (isDuplicatName(collectionList, editCollection)) {
      return false; 
    }  
    mongo.updateCollection(editCollection); 
    return true;
  }
  
  public boolean updateCollection(CollectionManager collectionManager, String department) {
    if (collectionManager.getNewGroupName() != null && !collectionManager.getNewGroupName().isEmpty() 
            && collectionManager.getManager() != null && !collectionManager.getManager().isEmpty()) {
      mongo.updateCollectionGroupName(collectionManager.getGroup(), 
              collectionManager.getNewGroupName(), collectionManager.getManager(), department);
    } else {
      return false;
    }
    return true;
  }
  
  public void deleteCollection(String group) {
     mongo.removeWholeCollection(group);
  }

  public boolean isDuplicatName(List<Collection> collectionList, Collection collection) { 
    return collectionList.stream()
            .filter((c) -> (!c.getId().equals(collection.getId())))
            .anyMatch((c) -> (c.getName().toLowerCase().trim().equals(collection.getName().toLowerCase().trim())));
  }
  
  private boolean isCollectionExist(List<Collection> group, String name) {
    return group.stream()
            .anyMatch((c) -> (c.getName().toLowerCase().trim().equals(name.toLowerCase().trim())));
  }
  
  public void editLoan(Loan loan) { 
    if (!loan.getStatus().equals("Request pending")) {
      String strDate = Util.getInstance().getTodayAsString();
      loan.setCloseDate(strDate);
    } else {
      loan.setCloseDate("");
    } 
    mongo.updateLoan(loan, loan.getReleventCollection() == null); 
  }
  
  public List<Loan> searchLoanWithConditions(Map<String, List<String>> conditions, String dateForSearch,
          String fromDate, String toDate, boolean searchForDate ) {
    return mongo.findLoansWithQueryMap(conditions, dateForSearch, fromDate, toDate, searchForDate);
  }
}
