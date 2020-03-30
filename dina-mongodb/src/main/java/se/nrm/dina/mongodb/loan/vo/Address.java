package se.nrm.dina.mongodb.loan.vo;

import java.io.Serializable;

/**
 *
 * @author idali
 */
public class Address implements Serializable {

  private String address;
  private String city;
  private String state;
  private String zip;
  private String country;

  public Address() {
  }

  public String getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getZip() {
    return zip;
  }

  public String getCountry() {
    return country;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCityAndCountry() {
    StringBuilder sb = new StringBuilder();
    sb.append(zip);
    sb.append(" ");
    sb.append(city);
    sb.append(" ");
    if (state != null && !state.isEmpty()) {
      sb.append(state);
      sb.append(" ");
    }
    sb.append(country);
    return sb.toString().trim();
  }

  public String getCompleteAddress() {
    StringBuilder sb = new StringBuilder();
    sb.append(address);
    sb.append("\n");
    sb.append(zip);
    sb.append(" ");
    sb.append(city);
    sb.append(" ");
    sb.append(state);
    sb.append("\n");
    sb.append(country);
    return sb.toString().trim();
  }
}
