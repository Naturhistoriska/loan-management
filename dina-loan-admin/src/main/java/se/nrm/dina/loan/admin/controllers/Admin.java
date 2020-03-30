package se.nrm.dina.loan.admin.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap; 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;  
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named; 
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.CellEditEvent; 
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TabChangeEvent; 
import se.nrm.dina.loan.admin.logic.MongoDataLogic; 
import se.nrm.dina.loan.admin.vo.CollectionManager; 
import se.nrm.dina.mongodb.loan.vo.Collection;
import se.nrm.dina.mongodb.loan.vo.Loan;

/**
 *
 * @author idali
 */
@Named(value = "admin")
@SessionScoped
@Slf4j
public class Admin implements Serializable {
  
  private String newCollectionName;
  private String newCollectionCurator;
  private String newCollectionGroupName;
  private String newCollectionManager;
  private boolean managerDisable = true;

  private String department = "Zoology";

  private String selectedGroupName;

  private String changedGroupName;
  private String changedManager;

  private final String defaultCollectionGruopName;
  private String defaultManager;

  private final String nonScientificLoans = "Non scientific";
  private String nonScientificLoansManager;

  private int activeTab;

  private Map<String, List<Collection>> map;
  private List<String> collectionGroupNames;
  private List<CollectionManager> cmList;
  
  private final String LOCAL_EXTERNAL_FILES = "https://localhost:8443/loan-admin/pdf?"; 
  private final String REMOTE_EXTERNAL_FILES_AS = "//www.dina-web.net/dina-admin/pdf?";
  
  private final String test = "https://www.dina-web.net/dina-external-datasource/pdf?";
   
  private final String externalFilePath; 
  private final String servername;
  
  @Inject
  private MongoDataLogic logic;
 
  @Inject
  private Messages message;
  
  public Admin() { 
    servername = ((HttpServletRequest) FacesContext.getCurrentInstance()
            .getExternalContext().getRequest()).getServerName();
    log.info("servername : {}", servername);
    if (servername.contains("localhost")) { 
      externalFilePath = LOCAL_EXTERNAL_FILES;
    } else {
      externalFilePath = REMOTE_EXTERNAL_FILES_AS;
    }
    defaultCollectionGruopName = "Insects";
    newCollectionGroupName = defaultCollectionGruopName; 
  }

  @PostConstruct
  public void init() {
    if (map == null || map.isEmpty()) {
      initialCollectionMap();
    }
  }
   
  public void onTabChange(TabChangeEvent event) {
    String activeIndex = ((AccordionPanel) event.getComponent()).getActiveIndex(); 
    activeTab = Integer.parseInt(activeIndex);
  }
   
  public void updateNonScientificManager() {
    log.info("updateNonScientificManager : {}", nonScientificLoansManager);
    logic.updateNonScientificLoansManager(nonScientificLoansManager);
  }
 

  public void departmentChanged() {
    log.info("departmentChanged : {}", department); 
    initialCollectionMap();
    if (collectionGroupNames.isEmpty()) {
      collectionGroupNames.add("------------ SELECT ------------");
    }
  }

  public void removeSubCollection(Collection collection) {
    log.info("removeSubCollection : {}", collection); 
    logic.removeCollection(collection);  
    initialCollectionMap();
  }

  public void addNewCollection() {
    log.info("addNewCollection : {}", newCollectionGroupName);

    if (!collectionGroupNames.contains(newCollectionGroupName)) {
      collectionGroupNames.add(newCollectionGroupName);
      newCollectionManager = null;
      managerDisable = false;
    } else {
      if (map.get(newCollectionGroupName) != null && !map.get(newCollectionGroupName).isEmpty()) {
        newCollectionManager = map.get(newCollectionGroupName).get(0).getManager();
        managerDisable = true;
      } else {
        newCollectionManager = null;
        managerDisable = false;
      }
    }
  }
 
  public void saveNewCollection() {
    log.info("saveNewCollection : {}", newCollectionGroupName);

    List<Collection> selectedGroup = map.get(newCollectionGroupName);  
    Collection collection = new Collection(newCollectionName, newCollectionCurator, 
            newCollectionGroupName, newCollectionManager, department);
    boolean isAddCollectionSuccess = logic.addNewCollection(selectedGroup, collection);
    
    if(!isAddCollectionSuccess) {
      addDuplicatCollectionWarning(newCollectionName, newCollectionGroupName);
    } else {
      initialCollectionMap();
      newCollectionName = null;
      newCollectionCurator = null;
      newCollectionGroupName = defaultCollectionGruopName;
      newCollectionManager = defaultManager; 
      managerDisable = true;
    } 
  }
 
  public String managermail(String key) { 
    log.info("managermail : {}", key);
    return map.get(key).get(0).getManager();
  }
  
  public void onRowEdit(RowEditEvent event) {
    log.info("onRowEdit : {}", ((Collection) event.getObject()).getName()); 
    Collection editCollection = (Collection) event.getObject(); 
    boolean isEditSuccess = logic.editCollection(editCollection, map.get(editCollection.getGroup()));
    
    if(isEditSuccess) {
       map = logic.findCollectionsByDepartment(department);
    } else {
      addDuplicatCollectionWarning(editCollection.getName(), editCollection.getGroup());
    } 
  }

  public void onRowCancel(RowEditEvent event) {
    log.info("onRowCancel : {}", ((Collection) event.getObject()).getName());
  }

  public void groupNameChanged() {
    log.info("groupNameChanged : {}", selectedGroupName);
  }
  
  public String pdf(Loan loan) { 
    StringBuilder sb = new StringBuilder();  
    sb.append(externalFilePath);
    sb.append("id=");
    sb.append(loan.getUuid().replace("-", "/"));
    sb.append("/loanrequest_");
    sb.append(loan.getId());
    sb.append("_admin.pdf");
 
    log.info("path : {}", sb.toString());
    return sb.toString();
  }
 
  public void updateCollection(CollectionManager cm) {
    log.info("updateCollection : {} -- {}", cm, department);
 
    if(logic.updateCollection(cm, department)) {
      initialCollectionMap();
    } else {
      message.addError("Missing value", "Collection name and manager email can not be empty.");
    } 
  }

  public void removeCollection(CollectionManager cm) {
    log.info("removeCollection : {} -- {}", cm, department); 
    initialCollectionMap();
  }

  public void onCellEdit(CellEditEvent event) {
    log.info("onCellEdit : {} -- {}", event.getOldValue(), event.getNewValue());
  }

  public void onStatusChanged(Loan loan) {
    log.info("onStatusChanged : {}", loan.getStatus());
  }

  public void cancel() {
    log.info("cancel");
  }
 
  public String getNewCollectionGroupName() {
    return newCollectionGroupName;
  }

  public void setNewCollectionGroupName(String newCollectionGroupName) {
    this.newCollectionGroupName = newCollectionGroupName;
  }

  public String getNewCollectionName() {
    return newCollectionName;
  }

  public void setNewCollectionName(String newCollectionName) {
    this.newCollectionName = newCollectionName;
  }

  public String getNewCollectionCurator() {
    return newCollectionCurator;
  }

  public void setNewCollectionCurator(String newCollectionCurator) {
    this.newCollectionCurator = newCollectionCurator;
  }

  public String getNewCollectionManager() {
    return newCollectionManager;
  }

  public void setNewCollectionManager(String newCollectionManager) {
    this.newCollectionManager = newCollectionManager;
  }

  public List<String> getCollectionGroupNames() {
    return collectionGroupNames;
  }

  public void setCollectionGroupNames(List<String> collectionGroupNames) {
    this.collectionGroupNames = collectionGroupNames;
  }
 
  public int getActiveTab() {
    return activeTab;
  }

  public void setActiveTab(int activeTab) {
    this.activeTab = activeTab;
  }

  public String getSelectedGroupName() {
    return selectedGroupName;
  }

  public void setSelectedGroupName(String selectedGroupName) {
    this.selectedGroupName = selectedGroupName;
  }

  public String getChangedGroupName() {
    return changedGroupName;
  }

  public void setChangedGroupName(String changedGroupName) {
    this.changedGroupName = changedGroupName;
  }

  public String getChangedManager() {
    return changedManager;
  }

  public void setChangedManager(String changedManager) {
    this.changedManager = changedManager;
  }

  public Map<String, List<Collection>> getMap() {
    return map;
  }

  public void setMap(Map<String, List<Collection>> map) {
    this.map = map;
  }

  public List<CollectionManager> getCmList() {
    return cmList;
  }

  public void setCmList(List<CollectionManager> cmList) {
    this.cmList = cmList;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public boolean isManagerDisable() {
    return managerDisable;
  }

  public void setManagerDisable(boolean managerDisable) {
    this.managerDisable = managerDisable;
  }

  public String getNonScientificLoansManager() {
    return nonScientificLoansManager;
  }

  public void setNonScientificLoansManager(String nonScientificLoansManager) {
    this.nonScientificLoansManager = nonScientificLoansManager;
  }
  
  private void initialCollectionMap() {
    log.info("initialCollectionMap");
    map = new TreeMap<>();
    cmList = new ArrayList<>();
    collectionGroupNames = new ArrayList<>();
    try {
      map = logic.findCollectionsByDepartment(department);
      nonScientificLoansManager = logic.getCollectionManager(map, nonScientificLoans);
      defaultManager = logic.getCollectionManager(map, defaultCollectionGruopName);
      newCollectionManager = defaultManager;
      collectionGroupNames = logic.getCollectionGroupName(map); 
      cmList = logic.getCollectionManagers(map); 
      map.remove(nonScientificLoans);
    } catch (Exception e) {
      message.addError("", "Can not connect to datasource.  Please contact administrator.");
    }
  }
  
  private void addDuplicatCollectionWarning(String collectionName, String collectionGroup) {
    StringBuilder sb = new StringBuilder();
    sb.append("Collection name [");
    sb.append(collectionName);
    sb.append("] is already exist in ");
    sb.append(collectionGroup);
    sb.append(".");

    message.addWarning("Duplicat Collection", sb.toString());
  }

}
