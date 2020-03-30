package se.nrm.dina.loan.admin.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;  
import se.nrm.dina.loan.admin.logic.MongoDataLogic;
import se.nrm.dina.loan.admin.util.Util; 
import se.nrm.dina.mongodb.loan.vo.Loan;

/**
 *
 * @author idali
 */
@Named(value = "search")
@SessionScoped
@Slf4j
public class SearchLoanController implements Serializable {
 
  private final String defaultDepartment = "Zoology";
  private String loanId;
  private String status;
  private String curator;
  private String releventCollection;
  private String borrower;

  private String searchLoanId; 

  private SelectItem[] statusOptions;
  private final List<String> statusList;

  private SelectItem[] curatorOptions;
  private List<String> curators;

  private SelectItem[] collectionOptions;
  private List<String> collections;

  private List<Loan> loans;
  private Loan editedLoan;

  private List<String> collectionGroups;

  private Date fromDate;
  private Date toDate;
  private Date mindate;

  private String dateForSearch;

  private List<Loan> filteredLoans;

  private List<String> selectedDepartments;
  private List<String> selectedPurposes;
  private List<String> selectedTypes;

  private boolean selectAllDepartments;
  private boolean selectAllPurposes;
  private boolean selectAllTypes;

  @Inject
  private MongoDataLogic logic;
   
  public SearchLoanController() {
    loans = new ArrayList<>();

    statusOptions = new SelectItem[5];

    statusOptions[0] = new SelectItem("", "Select");
    statusOptions[1] = new SelectItem("Request pending", "Request pending");
    statusOptions[2] = new SelectItem("In progress", "In progress");
    statusOptions[3] = new SelectItem("Request accepted", "Request accepted");
    statusOptions[4] = new SelectItem("Request denied", "Request denied");

    statusList = new ArrayList<>();
    statusList.add("Request pending");
    statusList.add("In progress");
    statusList.add("Request denied");
    statusList.add("Request accepted");

    selectedDepartments = new ArrayList<>();
    selectedDepartments.add("Zoology");

    selectedPurposes = new ArrayList<>();
    selectedPurposes.add("All");
    selectedPurposes.add("Scientific purpose");
    selectedPurposes.add("Education/Exhibition");
    selectedPurposes.add("Commercial/art/other");

    selectedTypes = new ArrayList<>();
    selectedTypes.add("All");
    selectedTypes.add("Physical");
    selectedTypes.add("Photo");
    selectedTypes.add("Information");

    selectAllDepartments = false;
    selectAllPurposes = true;
    selectAllTypes = true;

    dateForSearch = "submitDate";

    mindate = Util.getInstance().getStartDate();
  }

  @PostConstruct
  public void init() {
    if (loans == null || loans.isEmpty()) {
      loans = new ArrayList<>();
      loans = logic.findAllLoans();
    }

    if (curators == null || curators.isEmpty()) {
      curators = Util.getInstance().getCuratorsFromLoanList(loans); 
      curatorOptions = Util.getInstance().buildSelectItemOptions(curators); 
    }

    if (collections == null || collections.isEmpty()) {
      collections = new ArrayList<>();
      collections = logic.findCollectionNamesByDepartment(defaultDepartment);
      collectionOptions = Util.getInstance().buildSelectItemOptions(collections); 
      collections.add(0, "");
    }
  }

  public void cancel() {
    loanId = null;
    curator = null;
    releventCollection = null;
    borrower = null;
  }

  public void search() {

    Map<String, String> queryMap = new LinkedHashMap<>();

    if (loanId != null && !loanId.isEmpty()) {
      queryMap.put("_id", loanId.trim());
    }

    if (curator != null && !curator.isEmpty()) {
      queryMap.put("curator", curator.trim());
    }

    if (releventCollection != null && !releventCollection.isEmpty()) {
      queryMap.put("releventCollection", releventCollection.trim());
    }

    if (borrower != null && !borrower.isEmpty()) {
      queryMap.put("borrower", borrower);
    }
  }

  public void refreshLoans() {
    log.info("refreshLoans");
    searchFilterLoans();
  }

  public void onRowEdit(Loan loan) {
    log.info("onRowEdit: {} -- {}", loan, loan.getReleventCollection()); 
    logic.editLoan(loan);
  }

  public List<String> completeText(String query) {

    return collectionGroups.stream()
            .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
  }

  private void searchFilterLoans() {

    Map<String, List<String>> conditions = new HashMap<>();

    //TODO:  only zoology department is enabled.
    if (!selectedDepartments.contains("All")) {
      conditions.put("department", selectedDepartments);
    }

    if (!selectedPurposes.contains("All")) {
      conditions.put("purpose", selectedPurposes);
    }

    if (!selectedTypes.contains("All")) {
      conditions.put("type", selectedTypes);
    }

    boolean searchForDate = true;
    String strFromDate = null;
    String strToDate = null;

    if (fromDate == null && toDate == null) {
      searchForDate = false;
    } else {
      if (fromDate == null) {
        strFromDate = "2014-01-01";
      } else {
        strFromDate = Util.getInstance().dateToString(fromDate);
      }

      if (toDate == null) {
        strToDate = Util.getInstance().getTodayAsString();
      } else {
        strToDate = Util.getInstance().dateToString(toDate);
      }
    }

    loans = logic.searchLoanWithConditions(conditions, dateForSearch, strFromDate, strToDate, searchForDate);
    filteredLoans = null; 
  }

  public void filterLoansByType() {
    log.info("filterLoansByType: {}", selectedTypes);

    if (selectAllTypes) {
      if (selectedTypes.contains("All")) {
        selectedTypes.remove("All");
      } else {
        selectedTypes.clear();
      }
      selectAllTypes = false;
    } else {
      if (selectedTypes.contains("All")) {
        selectedTypes = new ArrayList<>();
        selectedTypes.add("All");
        selectedTypes.add("Physical");
        selectedTypes.add("Photo");
        selectAllTypes = true;
      } else if (selectedTypes.size() == 2) {
        selectedTypes.add("All");
        selectAllTypes = true;
      }
    }
    searchFilterLoans();
  }

  public void filterLoansByPurpose() {
    log.info("filterLoansByPurpose: {}", selectedPurposes);

    if (selectAllPurposes) {
      if (selectedPurposes.contains("All")) {
        selectedPurposes.remove("All");
      } else {
        selectedPurposes.clear();
      }
      selectAllPurposes = false;
    } else {
      if (selectedPurposes.contains("All")) {
        selectedPurposes = new ArrayList<>();
        selectedPurposes.add("All");
        selectedPurposes.add("Scientific purpose");
        selectedPurposes.add("Education/Exhibition");
        selectedPurposes.add("Commercial/art/other");
        selectAllPurposes = true;
      } else if (selectedPurposes.size() == 3) {
        selectedPurposes.add("All");
        selectAllPurposes = true;
      }
    }
    searchFilterLoans();
  }

  public void filterLoansByDepartment() {
    log.info("filterLoansByDepartment: {}", selectedDepartments);

    if (selectAllDepartments) {
      if (selectedDepartments.contains("All")) {
        selectedDepartments.remove("All");
      } else {
        selectedDepartments.clear();
      }
      selectAllDepartments = false;
    } else {
      if (selectedDepartments.contains("All")) {
        selectedDepartments = new ArrayList<>();
        selectedDepartments.add("All");
        selectedDepartments.add("Botany");
        selectedDepartments.add("Environmental Specimen Bank");
        selectedDepartments.add("Geology");
        selectedDepartments.add("Palaeobiology");
        selectedDepartments.add("Zoology");

        selectAllDepartments = true;
      } else if (selectedDepartments.size() == 5) {
        selectedDepartments.add("All");
        selectAllDepartments = true;
      }
    }

    searchFilterLoans();
  }

  public void handleDateSelect() {
    log.info("handleDateSelect");

    mindate = fromDate;

    searchFilterLoans();
  }

  public void dateSearch() {
    log.info("dateSearch : {} -- {}", dateForSearch, fromDate + " -- " + toDate);

    searchFilterLoans();
  }

  public List<String> getSelectedDepartments() {
    return selectedDepartments;
  }

  public void setSelectedDepartments(List<String> selectedDepartments) {
    this.selectedDepartments = selectedDepartments;
  }

  public List<String> getSelectedPurposes() {
    return selectedPurposes;
  }

  public void setSelectedPurposes(List<String> selectedPurposes) {
    this.selectedPurposes = selectedPurposes;
  }

  public List<String> getSelectedTypes() {
    return selectedTypes;
  }

  public void setSelectedTypes(List<String> selectedTypes) {
    this.selectedTypes = selectedTypes;
  }

  public String getLoanId() {
    return loanId;
  }

  public void setLoanId(String loanId) {
    this.loanId = loanId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCurator() {
    return curator;
  }

  public void setCurator(String curator) {
    this.curator = curator;
  }

  public String getReleventCollection() {
    return releventCollection;
  }

  public void setReleventCollection(String releventCollection) {
    this.releventCollection = releventCollection;
  }

  public List<String> getStatusList() {
    return statusList;
  }

  public List<Loan> getLoans() {
    return loans;
  }

  public void setLoans(List<Loan> loans) {
    this.loans = loans;
  }

  public String getSearchLoanId() {
    return searchLoanId;
  }

  public void setSearchLoanId(String searchLoanId) {
    this.searchLoanId = searchLoanId;
  }

  public Loan getEditedLoan() {
    return editedLoan;
  }

  public void setEditedLoan(Loan editedLoan) {
    this.editedLoan = editedLoan;
  }

  public String getDateForSearch() {
    return dateForSearch;
  }

  public void setDateForSearch(String dateForSearch) {
    this.dateForSearch = dateForSearch;
  }

  public SelectItem[] getStatusOptions() {
    return statusOptions;
  }

  public void setStatusOptions(SelectItem[] statusOptions) {
    this.statusOptions = statusOptions;
  }

  public Date getMaxDate() {
    Calendar now = Calendar.getInstance();
    return now.getTime();
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public Date getMindate() {
    return mindate;
  }

  public String getBorrower() {
    return borrower;
  }

  public void setBorrower(String borrower) {
    this.borrower = borrower;
  }

  public List<Loan> getFilteredLoans() {
    return filteredLoans;
  }

  public void setFilteredLoans(List<Loan> filteredLoans) {
    this.filteredLoans = filteredLoans;
  }

  public SelectItem[] getCuratorOptions() {
    return curatorOptions;
  }

  public void setCuratorOptions(SelectItem[] curatorOptions) {
    this.curatorOptions = curatorOptions;
  }

  public List<String> getCurators() {
    return curators;
  }

  public void setCurators(List<String> curators) {
    this.curators = curators;
  }

  public SelectItem[] getCollectionOptions() {
    return collectionOptions;
  }

  public void setCollectionOptions(SelectItem[] collectionOptions) {
    this.collectionOptions = collectionOptions;
  }

  public List<String> getCollections() {
    return collections;
  }

  public void setCollections(List<String> collections) {
    this.collections = collections;
  }

}
