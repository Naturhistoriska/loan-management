package se.nrm.dina.loan.web.controllers;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 

/**
 *
 * @author idali
 */
@Named("contact")
@ApplicationScoped
@Slf4j
public class Contact implements Serializable {
  
  private final String contactUsEn = "Contact us";
  private final String contactUsSv = "Kontakta oss";
  
  private final String sutitle1En = "You are welcome to contact us with questions, recommendations and comments.";
  private final String sublitle1Sv = "Hör gärna av dig till oss med frågor, tips och kommentarer.";
  
  private final String sutitle2En = "YoWe always try to help or improve best we can.";
  private final String sublitle2Sv = "Vi försöker alltid hjälpa till så gott vi kan.";
  
  
  private final String contactInfoEn = "Postal address and contact information";
  private final String contactInfoSv = "Postadress och kontaktuppgifter";
  
  private final String relevanDepatmentEn = "For scientific questions, please contact the relevant department.";
  private final String relevanDepatmentSv = "För vetenskapliga frågor, vänligen kontakta relevant enhet.";
   
  private final String loanFormEn = "NRM loan request form";
  private final String loanFormSv = "NRM Låneansökan";
   
  private final String att = "Att. Stefan Daume"; 
  
  private final String phoneEn = "Phone (exchange):";
  private final String phoneSv = "Telefon (växel):";
  
  private final String phoneNumberSv = "08-519 540 66 (fråga efter Bioinformatik och Genetik avdelening)";
  private final String phoneNumberEn = "08-519 540 66 (ask for Bioinformatics and Genetics)";
  
  private final String nrmEn = "Swedish Museum of Natural History";
  private final String nrmSv = "Naturhistoriska riksmuseet";
  
  private final String poBox = "Box 50007";
  private final String zip = "104 05 Stockholm";
  
  private final String swedenEn = "Sweden";
  private final String swedenSv = "Sverige";
  
  private final String email = " E-mail:";
  private final String emailAddress = "team@mail.dina-web.net";
   
  @Inject
  private Languages language;
  
  public String getContactUs() {
    return language.isIsSwedish() ? contactUsSv : contactUsEn;
  }
  
  public String getSubtitle1() {
    return language.isIsSwedish() ? sublitle1Sv : sutitle1En;
  }
  
  public String getSubtitle2() {
    return language.isIsSwedish() ? sublitle2Sv : sutitle2En;
  }
  
  public String getContactInfo() { 
    return language.isIsSwedish() ? contactInfoSv : contactInfoEn; 
  }
  
  public String getRevanDepartment() {
    return language.isIsSwedish() ? relevanDepatmentSv : relevanDepatmentEn;
  }
  
  public String getLoanForm() {
    return language.isIsSwedish() ? loanFormSv : loanFormEn;
  }
  
  public String getAtt() {
    return att;
  }
  
  public String getNrmName() {
    return language.isIsSwedish() ? nrmSv : nrmEn;
  }
  
  public String getPhone() {
    return language.isIsSwedish() ? phoneSv : phoneEn;
  }
  
  public String getPoBox() {
    return poBox;
  }
  
  public String getPhoneNumber() {
    return language.isIsSwedish() ? phoneNumberSv : phoneNumberEn;
  }
  
  public String getZip() {
    return zip;
  }
   
  public String getCountry() {
    return language.isIsSwedish() ? swedenSv : swedenEn;
  }
  
  public String getEmail() {
    return email;
  }
  
  public String getEmailAddress() {
    return emailAddress;
  }
}
