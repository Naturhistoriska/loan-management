package se.nrm.dina.email.model.loan.util;

import java.util.HashMap;
import java.util.Map; 

/**
 *
 * @author idali
 */
public class ConstantText {

  private final String LOAN_REQUEST_EN;
  private final String LOAN_REQUEST_SV;

  private final String INFORMATION_REQUEST;

  private final String LOAN_REFERENCE_EN;
  private final String LOAN_REFERENCE_SV;

  private final String RECEIPT_EN;
  private final String RECEIPT_SV;

  private final String DEAR_EN;
  private final String DEAR_SV;

  private final String HI_EN;
  private final String HI_SV;

  private final String AND_EN;
  private final String AND_SV;

  private final String LOAN_BODY_TEXT_EN;
  private final String LOAN_BODY_TEXT_SV;

  private final String LOAN_SUMMARY_LINK_EN;
  private final String LOAN_SUMMARY_LINK_SV;

  private final String SINCERELY_EN;
  private final String SINCERELY_SV;
  private final String COLLECTION_MANAGER_EN;
  private final String COLLECTION_MENAGER_SV;

  private final String NRM_EN;
  private final String NRM_SV;
  private final String POBOX_EN;
  private final String POBOX_SV;
  private final String COUNTRY_EN;
  private final String COUNTRY_SV;

  private final String PRIMARY_MAIL_TEXT_EN;
  private final String PRIMARY_MAIL_TEXT_SV;

  private final String LOAN_POLICY_LINK_EN;
  private final String LOAN_POLICY_LINK_SV;

  private final String CURATOR_EMAIL1_EN;
  private final String CURATOR_EMAIL1_SV;

  private final String CURATOR_EMAIL2_EN;
  private final String CURATOR_EMAIL2_SV;

  private final String LOAN_ACCEPTANCE_TEXT_EN;
  private final String LOAN_ACCEPTANCE_TEXT_SV;

  private final String AGREEMENT_ACCEPT_EN;
  private final String AGREEMENT_ACCEPT_SV;

  private final String AGREEMENT_DECLINE_EN;
  private final String AGREEMENT_DECLINE_SV;

  private final Map<String, String> departmentMap_en;
  private final Map<String, String> departmentMap_sv;

  private final String BR;
  private static ConstantText instance = null;

  private final String LOCAL_EXTERNAL_FILES = "http://localhost:8080/loan/pdf?id=";
  private final String REMOTE_EXTERNAL_FILES_LOAN = "http://dina-loans.nrm.se/loan/pdf?id=";
  private final String REMOTE_EXTERNAL_FILES_AS = "https://dina-web.net/loan/pdf?id=";
  
  private final String LOAN_AGREEMENT_EN;
  private final String LOAN_AGREEMENT_SV;

  private final String LOAN_REQUEST_ATTACHMENT_EN;
  private final String LOAN_REQUEST_ATTACHMENT_SV;

  private final String INFORMATION_REQUEST_ATTACHMENT;

  private final String RESPONSIBLE_CUROTOR_EN;
  private final String RESPONSIBLE_CUROTOR_SV;

  private final String CURATOR_MAIL_TITLE;
  private final String CURATOR_MAIL_TEXT;
  private final String CURATOR_MAIL_TEXST_INFORMATION;

  private final String CURATOR_MAIL_TEXT_NON_SCIENTIFIC;
  private final String CURATOR_MAIL_TEXT_1;

  private final String CURATOR_MAIL_PRIMARY_TEXT;

  private final String BORROWER;
  private final String PRIMARY_BORROWER;
  private final String SECONDARY_BORROWER;

  private final String LOAN_INFORMATION;
  private final String REQUEST_INFORMATION;

  private final String REQUEST_SUMMARY_1;
  private final String REQUEST_SUMMARY_2;

  private final String OUT_OF_OFFICE_EN;
  private final String OUT_OF_OFFICE_SV;

  private final String OUT_OF_OFFICE_NOTE_EN_1;
  private final String OUT_OF_OFFICE_NOTE_SV_1;

  private final String OUT_OF_OFFICE_NOTE_EN_2;
  private final String OUT_OF_OFFICE_NOTE_SV_2;

  ConstantText() {

    RECEIPT_EN = "Receipt";
    RECEIPT_SV = "Kvitto";

    LOAN_REQUEST_EN = "Loan request";
    LOAN_REQUEST_SV = "Låneansökan";

    LOAN_REFERENCE_EN = "Reference #: ";
    LOAN_REFERENCE_SV = "Referens #: ";

    INFORMATION_REQUEST = "Information request";

    PRIMARY_MAIL_TEXT_EN = "A request for a loan was submitted to the Swedish Museum of Natural History by the person listed below, and with you listed as primary borrow.";
    PRIMARY_MAIL_TEXT_SV = "En låneansökan har inkommit till Naturhistoriska Riksmuseet I Stockholm, med dig som primär låntagare.";

    LOAN_POLICY_LINK_EN = "Please read our Loan Policy here.";
    LOAN_POLICY_LINK_SV = "Du kan läsa vår utlånspolicy här.";

    LOAN_ACCEPTANCE_TEXT_EN = "The loan request will not be considered before we have recieved your acceptance.";
    LOAN_ACCEPTANCE_TEXT_SV = "Låneansökan kommer inte att beaktas innan vi fått ditt godkännande.";

    CURATOR_EMAIL1_EN = "Please forward this email to the responsible curator ";
    CURATOR_EMAIL1_SV = "Vänligen returnera detta mail till vår ansvarig personal ";

    CURATOR_EMAIL2_EN = " stating whether:";
    CURATOR_EMAIL2_SV = " och uppge om:";

    AGREEMENT_ACCEPT_EN = "I accept to take responsibility as primary borrower of the requested loan and agree to the terms in the loan policy";
    AGREEMENT_ACCEPT_SV = "Jag accepterar att ansvara för det berörda lånet och godkänner villkoren i riksmuseet lånepolicy.";

    AGREEMENT_DECLINE_EN = "I decline to take responsibility as primary borrower of the requested loan";
    AGREEMENT_DECLINE_SV = "Jag nekar till ansvar för det berörda lånet.";

    LOAN_BODY_TEXT_EN = "We have received your loan request and will process it in due course.";
    LOAN_BODY_TEXT_SV = "Vi har tagit emot din låneansökan och kommer att bearbeta den så fort vi kan.";
    LOAN_SUMMARY_LINK_EN = "Review loan request summary";
    LOAN_SUMMARY_LINK_SV = "Granska låneansökan";

    LOAN_REQUEST_ATTACHMENT_EN = "Loan request is attached.";
    LOAN_REQUEST_ATTACHMENT_SV = "Loan request is attached.";

    INFORMATION_REQUEST_ATTACHMENT = "Information request is attached";

    SINCERELY_EN = "Sincerely,";
    SINCERELY_SV = "Vänliga hälsningar,";
    COLLECTION_MANAGER_EN = "Collection Manager";
    COLLECTION_MENAGER_SV = "Collection Manager";

    LOAN_AGREEMENT_EN = "Loan agreement";
    LOAN_AGREEMENT_SV = "Låneavtal";
 
    departmentMap_en = new HashMap<>();
    departmentMap_sv = new HashMap<>();

    departmentMap_en.put("Botany", "Department of Botany");
    departmentMap_en.put("EnvionmentSpecimenBank", "Department of Environmental Specimen Bank");
    departmentMap_en.put("Geology", "Department of Geology");
    departmentMap_en.put("Palaeobiology", "Department of Palaeobiology");
    departmentMap_en.put("Zoology", "Department of Zoology");

    departmentMap_sv.put("Botany", "Enheten för Botanik");
    departmentMap_sv.put("EnvionmentSpecimenBank", "Enheten för Miljöprovbanken");
    departmentMap_sv.put("Geology", "Enheten för Geologi");
    departmentMap_sv.put("Palaeobiology", "Enheten för Paleobiologi");
    departmentMap_sv.put("Zoology", "Enheten för Zoologi");

    NRM_EN = "Swedish Museum of Natural History";
    NRM_SV = "Naturhistoriska riksmuseet";
    POBOX_EN = "Box 50007, 10405 Stockholm";
    POBOX_SV = "Box 50007, 10405 Stockholm";
    COUNTRY_EN = "Sweden";
    COUNTRY_SV = "Sveriga";

    DEAR_EN = "Dear";
    DEAR_SV = "Hej";

    HI_EN = "Hi";
    HI_SV = "Bäste";

    AND_EN = "and";
    AND_SV = "och";

    BR = "<br />";

    RESPONSIBLE_CUROTOR_EN = "Please forward this mail to responsible curator: ";
    RESPONSIBLE_CUROTOR_SV = "Please forward this mail to responsible curator: ";

    CURATOR_MAIL_TITLE = "Dear responsible curator ";
    CURATOR_MAIL_TEXT = "A loan request regarding specimens of ";
    CURATOR_MAIL_TEXST_INFORMATION = "An information request regarding specimens of ";
    CURATOR_MAIL_TEXT_NON_SCIENTIFIC = "A loan request ";
    CURATOR_MAIL_TEXT_1 = " has been submitted, as shown below.";

    CURATOR_MAIL_PRIMARY_TEXT = "This loan request involves a primary borrower.  Before taking action - wait for a letter of responsibility acceptance from the primary borrower.";

    LOAN_INFORMATION = "Loan information";
    REQUEST_INFORMATION = "Request information";

    BORROWER = "Borrower";
    PRIMARY_BORROWER = "Primary borrower";
    SECONDARY_BORROWER = "Secondary borrower";

    REQUEST_SUMMARY_1 = "Check and download request summary and attached files at the ";

    REQUEST_SUMMARY_2 = ".  If you don't have an account you should contact your department's Collection Manager who can create one for you.";

    OUT_OF_OFFICE_EN = " - Responsible curator away from office.";
    OUT_OF_OFFICE_SV = " - Ansvarig personal på semester.";

    OUT_OF_OFFICE_NOTE_EN_1 = "The curator responsible for your loan request is currently away from office.";
    OUT_OF_OFFICE_NOTE_EN_2 = "The processing of the loan request may therefor take a while longer than normal.";
    OUT_OF_OFFICE_NOTE_SV_1 = "Personalen som är ansvarig för er låneansökan är för tillfället borta från kontoret.";
    OUT_OF_OFFICE_NOTE_SV_2 = "Hantering av låneansökan kan därför ta längre tid än normalt.";

  }

  public static synchronized ConstantText getInstance() {
    if (instance == null) {
      instance = new ConstantText();
    }
    return instance;
  }

  public String getRequestSummary1() {
    return REQUEST_SUMMARY_1;
  }

  public String getRequestSummary2() {
    return REQUEST_SUMMARY_2;
  }

  public String getReferenceNumber(boolean isSwedish) {
    return isSwedish ? LOAN_REFERENCE_SV : LOAN_REFERENCE_EN;
  }

  public String getLoanRequest(boolean isSwedish) {
    return isSwedish ? LOAN_REQUEST_SV : LOAN_REQUEST_EN;
  }

  public String getInformationRequest() {
    return INFORMATION_REQUEST;
  }

  public String getPrimaryText(boolean isSwedish) {
    return isSwedish ? PRIMARY_MAIL_TEXT_SV : PRIMARY_MAIL_TEXT_EN;
  }

  public String getLoanPolicyLinkText(boolean isSwedish) {
    return isSwedish ? LOAN_POLICY_LINK_SV : LOAN_POLICY_LINK_EN;
  }

  public String getLoanSummaryLinkText(boolean isSwedish) {
    return isSwedish ? LOAN_SUMMARY_LINK_SV : LOAN_SUMMARY_LINK_EN;
  }

  public String getManagerText1(boolean isSwedish) {
    return isSwedish ? CURATOR_EMAIL1_SV : CURATOR_EMAIL1_EN;
  }

  public String getManagerText2(boolean isSwedish) {
    return isSwedish ? CURATOR_EMAIL2_SV : CURATOR_EMAIL2_EN;
  }

  public String getAgreementAccept(boolean isSwedish) {
    return isSwedish ? AGREEMENT_ACCEPT_SV : AGREEMENT_ACCEPT_EN;
  }

  public String getAgreementDecline(boolean isSwedish) {
    return isSwedish ? AGREEMENT_DECLINE_SV : AGREEMENT_DECLINE_EN;
  }

  public String getRECEIPT(boolean isSwedish) {
    return isSwedish ? RECEIPT_SV : RECEIPT_EN;
  }

  public String getDEAR(boolean isSwedish) {
    return isSwedish ? DEAR_SV : DEAR_EN;
  }

  public String getHi(boolean isSwedish) {
    return isSwedish ? HI_SV : HI_EN;
  }

  public String getAnd(boolean isSwedish) {
    return isSwedish ? AND_SV : AND_EN;
  }

  public String getAcceptText(boolean isSwedish) {
    return isSwedish ? LOAN_ACCEPTANCE_TEXT_SV : LOAN_ACCEPTANCE_TEXT_EN;
  }

  public String getPrimaryBorrower() {
    return PRIMARY_BORROWER;
  }

  public String getBorrower() {
    return BORROWER;
  }

  public String getSecondaryBorrower() {
    return SECONDARY_BORROWER;
  }

  public String getLOAN_BODY_TEXT(boolean isSwedish) {
    return isSwedish ? LOAN_BODY_TEXT_SV : LOAN_BODY_TEXT_EN;
  }

  public String getSINCERELY(boolean isSwedish) {
    return isSwedish ? SINCERELY_SV : SINCERELY_EN;
  }

  public String getCOLLECTION_MANAGER(boolean isSwedish) {
    return isSwedish ? COLLECTION_MENAGER_SV : COLLECTION_MANAGER_EN;
  }

  public String getDEPARTMENT(boolean isSwedish, String department) {
    return isSwedish ? departmentMap_sv.get(department) : departmentMap_en.get(department);
  }

  public String getDEPARTMENT(String department) {
    return departmentMap_en.get(department);
  }

  public String getNRM(boolean isSwedish) {
    return isSwedish ? NRM_SV : NRM_EN;
  }

  public String getResponsibleCurator(boolean isSwedish) {
    return isSwedish ? RESPONSIBLE_CUROTOR_SV : RESPONSIBLE_CUROTOR_EN;
  }

  public String getPOBOX(boolean isSwedish) {
    return isSwedish ? POBOX_SV : POBOX_EN;
  }

  public String getCOUNTRY(boolean isSwedish) {
    return isSwedish ? COUNTRY_SV : COUNTRY_EN;
  }

  public String getBaseExternalFilePath(String host) {
    if (host.contains("localhost")) {
      return LOCAL_EXTERNAL_FILES;
    } else if (host.contains("dina-loans")) {
      return REMOTE_EXTERNAL_FILES_LOAN;
    } else {
      return REMOTE_EXTERNAL_FILES_AS;
    }
  }

  public String getBaseExternalPolicyFilePath(String host) {
    if (host.contains("localhost")) {
      return "";
    } else {
      return "https:";
    }
  }

  public String getCuratorMailTitle() {
    return CURATOR_MAIL_TITLE;
  }

  public String getCuratorMailText() {
    return CURATOR_MAIL_TEXT;
  }

  public String getCuratorMailTextInformation() {
    return CURATOR_MAIL_TEXST_INFORMATION;
  }

  public String getCuratorMailTextNonScientific() {
    return CURATOR_MAIL_TEXT_NON_SCIENTIFIC;
  }

  public String getCURATOR_MAIL_TEXT_1() {
    return CURATOR_MAIL_TEXT_1;
  }

  public String getCuratorMailPrimaryText() {
    return CURATOR_MAIL_PRIMARY_TEXT;
  }

  public String getLoanInformation() {
    return LOAN_INFORMATION;
  }

  public String getRequestInformation() {
    return REQUEST_INFORMATION;
  }

  public String getLoanRequestAttachment(boolean isSwedish) {
    return isSwedish ? LOAN_REQUEST_ATTACHMENT_SV : LOAN_REQUEST_ATTACHMENT_EN;
  }

  public String getInformationRequestAttachment() {
    return INFORMATION_REQUEST_ATTACHMENT;
  }

  public String getLoanAgreement(boolean isSwedish) {
    return isSwedish ? LOAN_AGREEMENT_SV : LOAN_AGREEMENT_EN;
  }

  public String getOutofOfficeTitle(boolean isSwedish) {
    return isSwedish ? OUT_OF_OFFICE_SV : OUT_OF_OFFICE_EN;
  }

  public String getOutOfOfficeNote1(boolean isSwedish) {
    return isSwedish ? OUT_OF_OFFICE_NOTE_SV_1 : OUT_OF_OFFICE_NOTE_EN_1;
  }

  public String getOutOfOfficeNote2(boolean isSwedish) {
    return isSwedish ? OUT_OF_OFFICE_NOTE_SV_2 : OUT_OF_OFFICE_NOTE_EN_2;
  }
}
