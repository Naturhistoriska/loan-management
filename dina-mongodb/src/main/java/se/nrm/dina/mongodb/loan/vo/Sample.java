package se.nrm.dina.mongodb.loan.vo;

import java.io.Serializable;

/**
 *
 * @author idali
 */
public class Sample implements Serializable {

  private String catalogNumber;
  private String family;
  private String genus;
  private String species;
  private String locality;
  private String country;
  private String collectedYear;
  private String auctor;
  private String collector;
  private String typeStatus;
  private String type;
  private String location;
  private String other;

  public Sample() {
  }

  public Sample(String catalogNumber, String family, String genus, String species, String locality, String country,
          String collectedYear, String auctor, String collector, String typeStatus, String type, String location, String other) {
    this.catalogNumber = catalogNumber;
    this.family = family;
    this.genus = genus;
    this.species = species;
    this.locality = locality;
    this.country = country;
    this.collectedYear = collectedYear;
    this.auctor = auctor;
    this.collector = collector;
    this.typeStatus = typeStatus;
    this.type = type;
    this.location = location;
    this.other = other;
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public void setCatalogNumber(String catalogNumber) {
    this.catalogNumber = catalogNumber;
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

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public String getCollector() {
    return collector;
  }

  public void setCollector(String collector) {
    this.collector = collector;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getAuctor() {
    return auctor;
  }

  public void setAuctor(String auctor) {
    this.auctor = auctor;
  }

  public String getTypeStatus() {
    return typeStatus;
  }

  public void setTypeStatus(String typeStatus) {
    this.typeStatus = typeStatus;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getOther() {
    return other;
  }

  public void setOther(String other) {
    this.other = other;
  }

  public String getCollectedYear() {
    return collectedYear;
  }

  public void setCollectedYear(String collectedYear) {
    this.collectedYear = collectedYear;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return species + " -- " + family;
  }
}
