package se.nrm.dina.mongodb.loan.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObject;
import org.jongo.marshall.jackson.oid.MongoId;
//import org.jongo.marshall.jackson.oid.ObjectId;

/**
 *
 * @author idali
 */
public class Collection   {

  @MongoId
  protected String _id;
  private String name;
  private String email;
  private String group;
  private String manager;
  private String department;
  
  public static final String COLLECTION_NAME = "collection";

  @JsonCreator
  public Collection(@JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("group") String group,
          @JsonProperty("manager") String manager, @JsonProperty("department") String department) {
    this.name = name;
    this.email = email;
    this.group = group;
    this.manager = manager;
    this.department = department;
  }

  public String getId() {
    return _id;
  }

  public void setId(String _id) {
    this._id = _id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGroup() {
    return group == null ? "Insects" : group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getManager() {
    return manager == null ? "loanrequestZOO@nrm.se" : manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return name + " -- " + email + " --- " + group + " --- " + department + " --- " + manager;
  }
}
