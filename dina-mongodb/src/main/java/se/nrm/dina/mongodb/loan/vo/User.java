package se.nrm.dina.mongodb.loan.vo;

import java.io.Serializable;

/**
 *
 * @author idali
 */
public class User implements Serializable {

  private String title;
  private String firstname;
  private String lastname;
  private String institution;
  private String department;
  private Address address;
  private String phone;
  private String email;

  public User() {
    address = new Address();
  }

  public String getTitle() {
    return title;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getInstitution() {
    return institution;
  }

  public String getDepartment() {
    return department;
  }

  public Address getAddress() {
    return address;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setInstitution(String institution) {
    this.institution = institution;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {

    StringBuilder sb = new StringBuilder();
    sb.append(title);
    sb.append(" ");
    sb.append(firstname);
    sb.append(" ");
    sb.append(lastname);
    return sb.toString().trim();
  }

  @Override
  public String toString() {
    return "User : [" + firstname + " " + lastname + "] email [" + email;
  }
}
