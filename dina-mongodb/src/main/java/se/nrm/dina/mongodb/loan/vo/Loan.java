package se.nrm.dina.mongodb.loan.vo;
 
//import com.fasterxml.jackson.annotation.JsonInclude; 
 
import java.util.List;
import java.util.UUID;  

/**
 *
 * @author idali
 */ 
//@JsonInclude(JsonInclude.Include.ALWAYS) 
public class Loan  {
  
  private String _id; 
  private String loanNumber;
  private String uuid;
  private String department;
  private String purpose;
  private String eduPurpose;
  private String edPurposeFile;
  private String type;
  private String isDestructive;
  private String destructiveFile;
  private String destructiveMethod;
  private String photoInstraction;
  private String photoInstractionFile;
  private String loanDescription;
  private String loanDescriptionFile;
  private String exhPorpuseDesc;
  private String releventCollection;
  private String sampleSetAdditionalInfo;
  private String citesNumber;
  private String curator;
  private String manager;

  private String borrower;
  private String comments;

  private User primaryUser;
  private User secondaryUser;

  private String fromDate;
  private String toDate;

  private String submitDate;
  private String closeDate;

  private List<Sample> samples;

  private String status;
  
  public static final String COLLECTION_NAME = "loan";

  public Loan() {

  }

  public String getLoanNumber() {
    return loanNumber;
  }

  public void setLoanNumber(String loanNumber) {
    this.loanNumber = loanNumber;
  }
  
  public Loan(String id) {
    this._id = id;
  }

  public Loan(UUID uuid) {
    this.uuid = uuid.toString();
  }

  public String getId() {
    return _id;
  }

  public void setId(String _id) {
    this._id = _id;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDestructiveFile() {
    return destructiveFile;
  }

  public void setDestructiveFile(String destructiveFile) {
    this.destructiveFile = destructiveFile;
  }

  public String getDestructiveMethod() {
    return destructiveMethod;
  }

  public void setDestructiveMethod(String destructiveMethod) {
    this.destructiveMethod = destructiveMethod;
  }

  public String getPhotoInstraction() {
    return photoInstraction;
  }

  public void setPhotoInstraction(String photoInstraction) {
    this.photoInstraction = photoInstraction;
  }

  public String getPhotoInstractionFile() {
    return photoInstractionFile;
  }

  public void setPhotoInstractionFile(String photoInstractionFile) {
    this.photoInstractionFile = photoInstractionFile;
  }

  public String getLoanDescription() {
    return loanDescription;
  }

  public void setLoanDescription(String loanDescription) {
    this.loanDescription = loanDescription;
  }

  public String getLoanDescriptionFile() {
    return loanDescriptionFile;
  }

  public void setLoanDescriptionFile(String loanDescriptionFile) {
    this.loanDescriptionFile = loanDescriptionFile;
  }

  public String getReleventCollection() {
    return releventCollection;
  }

  public void setReleventCollection(String releventCollection) {
    this.releventCollection = releventCollection;
  }

  public String getSampleSetAdditionalInfo() {
    return sampleSetAdditionalInfo;
  }

  public void setSampleSetAdditionalInfo(String sampleSetAdditionalInfo) {
    this.sampleSetAdditionalInfo = sampleSetAdditionalInfo;
  }

  public User getPrimaryUser() {
    return primaryUser;
  }

  public void setPrimaryUser(User primaryUser) {
    this.primaryUser = primaryUser;
  }

  public User getSecondaryUser() {
    return secondaryUser;
  }

  public void setSecondaryUser(User secondaryUser) {
    this.secondaryUser = secondaryUser;
  }

  public List<Sample> getSamples() {
    return samples;
  }

  public void setSamples(List<Sample> samples) {
    this.samples = samples;
  }

  public String getIsDestructive() {
    return isDestructive;
  }

  public void setIsDestructive(String isDestructive) {
    this.isDestructive = isDestructive;
  }

  public String getCitesNumber() {
    return citesNumber;
  }

  public void setCitesNumber(String citesNumber) {
    this.citesNumber = citesNumber;
  }

  public String getEduPurpose() {
    return eduPurpose;
  }

  public void setEduPurpose(String eduPurpose) {
    this.eduPurpose = eduPurpose;
  }

  public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public String getToDate() {
    return toDate;
  }

  public void setToDate(String toDate) {
    this.toDate = toDate;
  }

  public String getExhPorpuseDesc() {
    return exhPorpuseDesc;
  }

  public void setExhPorpuseDesc(String exhPorpuseDesc) {
    this.exhPorpuseDesc = exhPorpuseDesc;
  }

  public String getEdPurposeFile() {
    return edPurposeFile;
  }

  public void setEdPurposeFile(String edPurposeFile) {
    this.edPurposeFile = edPurposeFile;
  }

  public String getSubmitDate() {
    return submitDate;
  }

  public void setSubmitDate(String submitDate) {
    this.submitDate = submitDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCloseDate() {
    return closeDate;
  }

  public void setCloseDate(String closeDate) {
    this.closeDate = closeDate;
  }

  public String getCurator() {
    return curator;
  }

  public void setCurator(String curator) {
    this.curator = curator;
  }

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public String getBorrower() {
    return borrower;
  }

  public void setBorrower(String borrower) {
    this.borrower = borrower;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public String getSecondaryBorrower() {

    return secondaryUser == null ? "" : secondaryUser.getFirstname() + " " + secondaryUser.getLastname();
  }

  @Override
  public String toString() {
    return _id;
  }
}
