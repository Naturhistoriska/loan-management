package se.nrm.dina.loan.web.util;

/**
 *
 * @author idali
 */
public enum LoanPurpose {

  ScientificPurpose,
  EducationExhibition,
  CommercialArtOther;

  public String getText() {
    return this.name().toLowerCase();
  }

  public boolean isScientificPurpose() {
    return this == ScientificPurpose;
  }

  public boolean isEducation() {
    return this == EducationExhibition;
  }

  public boolean isCommercial() {
    return this == CommercialArtOther;
  }

  public boolean isScientificPurpose(String purpose) {
    return this.name().toLowerCase().equals(purpose.toLowerCase());
  }

  public boolean isEducation(String purpose) {
    return this.name().toLowerCase().equals(purpose.toLowerCase());
  }

  public boolean isCommercial(String purpose) {
    return this.name().toLowerCase().equals(purpose.toLowerCase());
  }
}
