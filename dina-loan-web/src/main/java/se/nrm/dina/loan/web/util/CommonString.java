package se.nrm.dina.loan.web.util;

/**
 *
 * @author idali
 */
public class CommonString {

  private static CommonString instance = null;

  private static final String EN_LOAN_REQUEST = "Loan request";
  private static final String SV_LOAN_REQUEST = "Låneansökan";

  private static final String SV_LOAN_REFERENCE = "Referens #: ";
  private static final String EN_LOAN_REFERENCE = "Reference #: ";

  private static final String EN_CONTACT_DETAIL = "Contact details";
  private static final String SV_CONTACT_DETAIL = "Adressuppgifter";

  private static final String EN_BORROWER = "Borrower";
  private static final String SV_BORROWER = "Låntagare";

  private static final String EN_CONTACT_DETAIL_PRIMARY = "Primary borrower";
  private static final String SV_CONTACT_DETAIL_PRIMARY = "Primär låntagare";

  private static final String EN_CONTACT_DETAIL_SECONDARY = "Secondary borrower";
  private static final String SV_CONTACT_DETAIL_SECONDARY = "Sekundär låntagare";

  private static final String EN_LOAN_DETAIL = "Loan request details";
  private static final String SV_LOAN_DETAIL = "Låneansökan - information";

  // loan details
  private static final String EN_DEPARTMENT = "Collection: ";
  private static final String SV_DEPARTMENT = "Samling: ";

  private static final String EN_RELEVAVANT_COLLECTION = "Relevant collection: ";
  private static final String SV_RELEVAVANT_COLLECTION = "Relevant samling: ";

  private static final String EN_LOAN_PURPOSE = "Purpose of the Loan: ";
  private static final String SV_LOAN_PURPOSE = "Syftet med lånet: ";

  private static final String EN_LOAN_TYPE = "Type of request: ";
  private static final String SV_LOAN_TYPE = "Typ av lån: ";

  private static final String EN_LOAN_DESCRIPTION = "Description of loan";
  private static final String SV_LOAN_DESCRIPTION = "Beskriv syftet med lånet";

  private static final String EN_ATTACHMENTS = "Attachment: ";
  private static final String SV_ATTACHMENTS = "Bifogat: ";

  private static final String EN_LOANREQUEST_PERIOD = "Request loan period";
  private static final String SV_LOANREQUEST_PERIOD = "Ansökt låneperiod";

  private static final String EN_LOANREQUEST_HOLIDAY_PERIOD = "Request loan period (Holiday)";
  private static final String SV_LOANREQUEST_HOLIDAY_PERIOD = "Ansökt låneperiod (juletider)";

  // Sample details
  private static final String EN_SAMPLE_DETAILS = "Loan request object details";
  private static final String SV_SAMPLE_DETAILS = "Låneansökan - föremålsinformation";

  private static final String EN_CATALOG_NUMBER = "Catalog number: ";
  private static final String SV_CATALOG_NUMBER = "Samlings-ID: ";

  private static final String EN_FAMALIY = "Famaliy: ";
  private static final String SV_FAMALIY = "Familj: ";

  private static final String EN_GENUS = "Genus: ";
  private static final String SV_GENUS = "Släkte: ";

  private static final String EN_SPECIES = "Species: ";
  private static final String SV_SPECIES = "Art: ";

  private static final String EN_LOCALITY = "Locality: ";
  private static final String SV_LOCALITY = "Locality: ";

  private static final String EN_COUNTRY = "Country: ";
  private static final String SV_COUNTRY = "Land: ";

  private static final String EN_AUTHOR = "Author, year: ";
  private static final String SV_AUTHOR = "Auktor, år: ";

  private static final String EN_TYP_STATUS = "Type status: ";
  private static final String SV_TYP_STATUS = "Typ-status: ";

  private static final String EN_PRESERVATION_TYPE = "Preservation type: ";
  private static final String SV_PRESERVATION_TYPE = "Konserverings-method: ";

  private static final String EN_COLLECTED_YEAR = "Collection year(s): ";
  private static final String SV_COLLECTED_YEAR = "Insamlingsår: ";

  private static final String EN_COLLECTOR = "Collector: ";
  private static final String SV_COLLECTOR = "Insamlare: ";

  private static final String EN_COMMENTS = "Other comments: ";
  private static final String SV_COMMENTS = "Ytterligare information: ";

  private static final String EN_ADDITIONAL_INFO = "Additional information regarding the whole loan set";
  private static final String SV_ADDITIONAL_INFO = "Övrig information angående hela setet av föremål";

  private static final String EN_ADDITIONAL_INFO_INFO = "Additional information regarding the information request";
  private static final String SV_ADDITIONAL_INFO_INFO = "Övrig information angående hela setet av informationsförfrågan";

  private static final String EN_LOCAION = "Storage";
  private static final String SV_LOCATION = "Förvaringsplats";

  // Destructive method
  private static final String EN_NO_DESTRUCTIVE = "This request does not involve destructive sampling.";
  private static final String SV_NO_DESTRUCTIVE = "Lånet innebär inget destruktivt arbete";

  private static final String EN_DESTRUCTIVE = "This request includes destructive sampling.  NRM's destructive sampling policy is read, understood and agreed.";
  private static final String SV_DESTRUCTIVE = "Lånet innebär destruktivt arbete. NRM's policy för destruktivt arbete är läst, förstådd och godkänd.";

  private static final String EN_DESTRUCTIVE_METHOD = "Desctructive method";
  private static final String SV_DESTRUCTIVE_METHOD = "Desktruktiv hantering";

  private static final String EN_PHOTO_METHOD = "Instructions for photographers";
  private static final String SV_PHOTO_METHOD = "Speciella önskemål för fotografer";

  private static final String EN_CITE = "CITES information";
  private static final String SV_CITE = "CITES-information";

  private static final String EN_CITE_NUMBER = "Institutional CITES registration number: ";
  private static final String SV_CITE_NUMBER = "Institutionens CITES-nummer: ";

  private static final String EN_FROM_DATE = "From date";
  private static final String SV_FROM_DATE = "Från";

  private static final String EN_END_DATE = "To date";
  private static final String SV_END_DATE = "Till";

  private static final String EN_EDUCATION = "Education/Exhibition";
  private static final String SV_EDUCATION = "Utbildning/Utställning";

  private static final String EN_DESCRIPTION = "Description of purpose and objects";
  private static final String SV_DESCRIPTION = "Beskrivning av syfte och föremål";

  private static final String EN_COMMERCIAL = "Commercial/art/other";
  private static final String SV_COMMERCIAL = "Kommersiellt/konst/annat";

  private static final String EN_PURPOSE = "Purpose";
  private static final String SV_PURPOSE = "Syfte";

  private static final String EN_EXHIBITION_DESCRIPTION = "Description of the exhibition environment";
  private static final String SV_EXHIBITION_DESCRIPTION = "Beskrivning av utställningsmiljön";

  private static final String EN_LOAN_PERIOD = "Request loan period: ";
  private static final String SV_LOAN_PERIOD = "Ansökt låneperiod: ";

  private static final String EN_TERMS_OF_LOAN_AGREEMENT = "Terms of Loan agreement";
  private static final String SV_TERMS_OF_LOAN_AGREEMENT = "Låneavtalsvillkor";

  private static final String EN_LOAN_POLICY = "The borrower has read, understood and accepted the terms in NRM's loan policy.";
  private static final String SV_LOAN_POLICY = "Låntagaren har läst, förstått och godkänt villkoren i NRM's lånepolicy.";

  private static final String EN_PRIMARY_BORROWER_AGREEMENT = "The institution has, via the primary borrower, agreed to be responsible for the loan as listed above.";
  private static final String SV_PRIMARY_BORROWER_AGREEMENT = "Insutitionen har, via den primära låntagaren, godkänt att ta ansvar för det lånade materialet under lånetiden.";

  private static final String EN_NO_CITES = "The institution lacks CITES registration number";
  private static final String SV_NO_CITES = "Institutionen saknar CITES-registrering";

  private static final String SCIENTIFIC_PURPOSE_LOAN_POLICY = "Loanpolicyforscientificpurposes.pdf";
  private static final String EDUCATIONAL_PURPOSE_LOAN_POLICY = "Loanpolicycommon.pdf";

  public static String getNoCITESText(boolean isSwedish) {
    return isSwedish ? SV_NO_CITES : EN_NO_CITES;
  }

  public static String getPolicyPdfName(boolean isScientifcPurpose) {
    return isScientifcPurpose ? SCIENTIFIC_PURPOSE_LOAN_POLICY : EDUCATIONAL_PURPOSE_LOAN_POLICY;
  }

  public static String getTermsOfAgreement(boolean isSwedish) {
    return isSwedish ? SV_TERMS_OF_LOAN_AGREEMENT : EN_TERMS_OF_LOAN_AGREEMENT;
  }

  public static String getLoanPolicy(boolean isSwedish) {
    return isSwedish ? SV_LOAN_POLICY : EN_LOAN_POLICY;
  }

  public static String getPrimaryBorrowerAgreement(boolean isSwedish) {
    return isSwedish ? SV_PRIMARY_BORROWER_AGREEMENT : EN_PRIMARY_BORROWER_AGREEMENT;
  }

  public static String getLoanRequest(boolean isSwedish) {
    return isSwedish ? SV_LOAN_REQUEST : EN_LOAN_REQUEST;
  }

  public static String getLoanReference(boolean isSwedish) {
    return isSwedish ? SV_LOAN_REFERENCE : EN_LOAN_REFERENCE;
  }

  public static String getBowrrow(boolean isSwedish) {
    return isSwedish ? SV_BORROWER : EN_BORROWER;
  }

  public static String getContactDetails(boolean isSwedish) {
    return isSwedish ? SV_CONTACT_DETAIL : EN_CONTACT_DETAIL;
  }

  public static String getDescription(boolean isSwedish) {
    return isSwedish ? SV_DESCRIPTION : EN_DESCRIPTION;
  }

  public static String getPrimaryContact(boolean isSwedish) {
    return isSwedish ? SV_CONTACT_DETAIL_PRIMARY : EN_CONTACT_DETAIL_PRIMARY;
  }

  public static String getSecondaryContact(boolean isSwedish) {
    return isSwedish ? SV_CONTACT_DETAIL_SECONDARY : EN_CONTACT_DETAIL_SECONDARY;
  }

  public static String getLoanDetail(boolean isSwedish) {
    return isSwedish ? SV_LOAN_DETAIL : EN_LOAN_DETAIL;
  }

  public static String getPurpose(boolean isSwedish) {
    return isSwedish ? SV_PURPOSE : EN_PURPOSE;
  }

  public static String getDepartment(boolean isSwedish) {
    return isSwedish ? SV_DEPARTMENT : EN_DEPARTMENT;
  }

  public static String getRelevantCollection(boolean isSwedish) {
    return isSwedish ? SV_RELEVAVANT_COLLECTION : EN_RELEVAVANT_COLLECTION;
  }

  public static String getNoDestructive(boolean isSwedish) {
    return isSwedish ? SV_NO_DESTRUCTIVE : EN_NO_DESTRUCTIVE;
  }

  public static String getDestructive(boolean isSwedish) {
    return isSwedish ? SV_DESTRUCTIVE : EN_DESTRUCTIVE;
  }

  public static String getLoanPurpose(boolean isSwedish) {
    return isSwedish ? SV_LOAN_PURPOSE : EN_LOAN_PURPOSE;
  }

  public static String getEducation(boolean isSwedish) {
    return isSwedish ? SV_EDUCATION : EN_EDUCATION;
  }

  public static String getCommercial(boolean isSwedish) {
    return isSwedish ? SV_COMMERCIAL : EN_COMMERCIAL;
  }

  public static String getLoanType(boolean isSwedish) {
    return isSwedish ? SV_LOAN_TYPE : EN_LOAN_TYPE;
  }

  public static String getLoanDescription(boolean isSwedish) {
    return isSwedish ? SV_LOAN_DESCRIPTION : EN_LOAN_DESCRIPTION;
  }

  public static String getAttachment(boolean isSwedish) {
    return isSwedish ? SV_ATTACHMENTS : EN_ATTACHMENTS;
  }

  public static String getSampleDetails(boolean isSwedish) {
    return isSwedish ? SV_SAMPLE_DETAILS : EN_SAMPLE_DETAILS;
  }

  public static String getCatelogNumber(boolean isSwedish) {
    return isSwedish ? SV_CATALOG_NUMBER : EN_CATALOG_NUMBER;
  }

  public static String getFamily(boolean isSwedish) {
    return isSwedish ? SV_FAMALIY : EN_FAMALIY;
  }

  public static String getGenus(boolean isSwedish) {
    return isSwedish ? SV_GENUS : EN_GENUS;
  }

  public static String getSpecies(boolean isSwedish) {
    return isSwedish ? SV_SPECIES : EN_SPECIES;
  }

  public static String getLocality(boolean isSwedish) {
    return isSwedish ? SV_LOCALITY : EN_LOCALITY;
  }

  public static String getCountry(boolean isSwedish) {
    return isSwedish ? SV_COUNTRY : EN_COUNTRY;
  }

  public static String getAuthor(boolean isSwedish) {
    return isSwedish ? SV_AUTHOR : EN_AUTHOR;
  }

  public static String getTypeStatus(boolean isSwedish) {
    return isSwedish ? SV_TYP_STATUS : EN_TYP_STATUS;
  }

  public static String getPreservationType(boolean isSwedish) {
    return isSwedish ? SV_PRESERVATION_TYPE : EN_PRESERVATION_TYPE;
  }

  public static String getCollectedYear(boolean isSwedish) {
    return isSwedish ? SV_COLLECTED_YEAR : EN_COLLECTED_YEAR;
  }

  public static String getCollector(boolean isSwedish) {
    return isSwedish ? SV_COLLECTOR : EN_COLLECTOR;
  }

  public static String getCommens(boolean isSwedish) {
    return isSwedish ? SV_COMMENTS : EN_COMMENTS;
  }

  public static String getAdditionalInfo(boolean isSwedish) {
    return isSwedish ? SV_ADDITIONAL_INFO : EN_ADDITIONAL_INFO;
  }

  public static String getAdditionalInfoInfo(boolean isSwedish) {
    return isSwedish ? SV_ADDITIONAL_INFO_INFO : EN_ADDITIONAL_INFO_INFO;
  }

  public static String getDestructiveMethod(boolean isSwedish) {
    return isSwedish ? SV_DESTRUCTIVE_METHOD : EN_DESTRUCTIVE_METHOD;
  }

  public static String getPhotoMethod(boolean isSwedish) {
    return isSwedish ? SV_PHOTO_METHOD : EN_PHOTO_METHOD;
  }

  public static String getCITE(boolean isSwedish) {
    return isSwedish ? SV_CITE : EN_CITE;
  }

  public static String getCITENumber(boolean isSwedish) {
    return isSwedish ? SV_CITE_NUMBER : EN_CITE_NUMBER;
  }

  public static String getFromDate(boolean isSwedish) {
    return isSwedish ? SV_FROM_DATE : EN_FROM_DATE;
  }

  public static String getEndDate(boolean isSwedish) {
    return isSwedish ? SV_END_DATE : EN_END_DATE;
  }

  public static String getExhibitionDescription(boolean isSwedish) {
    return isSwedish ? SV_EXHIBITION_DESCRIPTION : EN_EXHIBITION_DESCRIPTION;
  }

  public static String getLoanPeriod(boolean isSwedish) {
    return isSwedish ? SV_LOAN_PERIOD : EN_LOAN_PERIOD;
  }

  public static String getLocation(boolean isSwedish) {
    return isSwedish ? SV_LOCATION : EN_LOCAION;
  }

  public static String getLoanRequestPeriod(boolean isSwedish, boolean isHoliday) {
    if (isHoliday) {
      return isSwedish ? SV_LOANREQUEST_HOLIDAY_PERIOD : EN_LOANREQUEST_HOLIDAY_PERIOD;
    } else {
      return isSwedish ? SV_LOANREQUEST_PERIOD : EN_LOANREQUEST_PERIOD;
    }
  }

  public static synchronized CommonString getInstance() {
    if (instance == null) {
      instance = new CommonString();
    }
    return instance;
  }
}
