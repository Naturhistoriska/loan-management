package se.nrm.dina.loan.web.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author idali
 */
public class NameMapping {
 
  private static final Map<String, String> NAME_MAP_EN;
  private static final Map<String, String> NAME_MAP_SV;

  private static final Map<Enum, String> MAIN_MENU_MAP_EN;
  private static final Map<Enum, String> MAIN_MENU_MAP_SV;

  private static final Map<Enum, String> EDUCATION_MENU_MAP_EN;
  private static final Map<Enum, String> EDUCATION_MENU_MAP_SV;

  private static final Map<Enum, String> ART_MENU_MAP_EN;
  private static final Map<Enum, String> ART_MENU_MAP_SV;

  private static final Map<Enum, String> COMMON_MAP_EN;
  private static final Map<Enum, String> COMMON_MAP_SV;

  static {
    MAIN_MENU_MAP_EN = new HashMap<>();
    MAIN_MENU_MAP_SV = new HashMap<>();

    MAIN_MENU_MAP_EN.put(BreadCrumbElement.OnlineForm, "Department");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page1, "Purpose");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page1b, "Not implemented");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page2, "Request type");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page3, "Project");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page4, "Collection");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page5, "Specimens");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page6, "Destructive sampling");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page6b, "Photo details");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page7, "CITES");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page8, "Contact details");
    MAIN_MENU_MAP_EN.put(BreadCrumbElement.Page9, "Review/Submit");

    MAIN_MENU_MAP_SV.put(BreadCrumbElement.OnlineForm, "Enhet");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page1, "Syfte");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page1b, "Inte anslutet");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page2, "Förfrågningstyp");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page3, "Projekt");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page4, "Samling");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page5, "Föremål");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page6, "Destruktiv hantering");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page6b, "Foto-detaljer");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page7, "CITES");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page8, "Adressuppgifter");
    MAIN_MENU_MAP_SV.put(BreadCrumbElement.Page9, "Granska/Skicka");

    EDUCATION_MENU_MAP_EN = new HashMap<>();
    EDUCATION_MENU_MAP_SV = new HashMap<>();

    EDUCATION_MENU_MAP_EN.put(BreadCrumbElement.Page2, "General loan information");
    EDUCATION_MENU_MAP_EN.put(BreadCrumbElement.Page3, "Purpose of use");
    EDUCATION_MENU_MAP_EN.put(BreadCrumbElement.Page4, "Loan details");
    EDUCATION_MENU_MAP_EN.put(BreadCrumbElement.Page5, "Storage");
    EDUCATION_MENU_MAP_EN.put(BreadCrumbElement.Page6, "Contact details");
    EDUCATION_MENU_MAP_EN.put(BreadCrumbElement.Page7, "Review/Submit");

    EDUCATION_MENU_MAP_SV.put(BreadCrumbElement.Page2, "Generell låneinformation");
    EDUCATION_MENU_MAP_SV.put(BreadCrumbElement.Page3, "Användningssyfte");
    EDUCATION_MENU_MAP_SV.put(BreadCrumbElement.Page4, "Lånedetaljer");
    EDUCATION_MENU_MAP_SV.put(BreadCrumbElement.Page5, "Förvaring");
    EDUCATION_MENU_MAP_SV.put(BreadCrumbElement.Page6, "Adressuppgifter");
    EDUCATION_MENU_MAP_SV.put(BreadCrumbElement.Page7, "Granska/Skicka");

    ART_MENU_MAP_EN = new HashMap<>();
    ART_MENU_MAP_SV = new HashMap<>();

    ART_MENU_MAP_EN.put(BreadCrumbElement.Page2, "General loan information");
    ART_MENU_MAP_EN.put(BreadCrumbElement.Page3, "Loan type");
    ART_MENU_MAP_EN.put(BreadCrumbElement.Page4, "Loan details");
    ART_MENU_MAP_EN.put(BreadCrumbElement.Page5, "Contact details");
    ART_MENU_MAP_EN.put(BreadCrumbElement.Page6, "Review/Submit");

    ART_MENU_MAP_SV.put(BreadCrumbElement.Page2, "Generell låneinformation");
    ART_MENU_MAP_SV.put(BreadCrumbElement.Page3, "Lånetyp");
    ART_MENU_MAP_SV.put(BreadCrumbElement.Page4, "Lånedetaljer");
    ART_MENU_MAP_SV.put(BreadCrumbElement.Page5, "Adressuppgifter");
    ART_MENU_MAP_SV.put(BreadCrumbElement.Page6, "Granska/Skicka");

    NAME_MAP_EN = new HashMap<>();
    NAME_MAP_SV = new HashMap<>();

    NAME_MAP_EN.put("exhibition", "Exhibitions");
    NAME_MAP_EN.put("education", "Education");

    NAME_MAP_SV.put("exhibition", "Utställning");
    NAME_MAP_SV.put("education", "Utbildning");

    COMMON_MAP_EN = new HashMap<>();
    COMMON_MAP_SV = new HashMap<>();

    COMMON_MAP_EN.put(CommonNames.NoResults, "No records exist in our database for this search term");
    COMMON_MAP_EN.put(CommonNames.MissingCatNum, "Please fill in catalog number!");
    COMMON_MAP_EN.put(CommonNames.MissingFamily, "Please fill in family name!");
    COMMON_MAP_EN.put(CommonNames.MissingGenus, "Please fill in genus name!");
    COMMON_MAP_EN.put(CommonNames.MissingSpecies, "Please fill in species name!");
    COMMON_MAP_EN.put(CommonNames.DuplicatValue, " is selected in the list!");

    COMMON_MAP_EN.put(CommonNames.DestructivePolicy, "Destructive Sampling/DNA policy");
    COMMON_MAP_EN.put(CommonNames.ScientificLoanPolicy, "Loanpolicy for scientific purpose");
    COMMON_MAP_EN.put(CommonNames.CommonLoanPolicy, "Common loanpolicy");
    COMMON_MAP_EN.put(CommonNames.PreservationTypeNotSpecified, "Not specified");

    COMMON_MAP_EN.put(CommonNames.RequestFailed, "Loan request failed.  Please try again.");
    COMMON_MAP_EN.put(CommonNames.UploadFileFailed, "Upload file failed, please check file size or file type.");

    COMMON_MAP_EN.put(CommonNames.SendingEmailsFailed, "Failed sending emails. Please contact loan admin: tobias.malm@nrm.se");

    COMMON_MAP_EN.put(CommonNames.Idle, "No activity");
    COMMON_MAP_EN.put(CommonNames.IdleMsg, "Page is inactive for two hours. Session is expired. Redirect to start page.");

    COMMON_MAP_EN.put(CommonNames.DataSourceConnectionError, "Database connection error");

    COMMON_MAP_SV.put(CommonNames.NoResults, "Inga taxa i våran databas överensstämmer med din sökterm");
    COMMON_MAP_SV.put(CommonNames.MissingCatNum, "Fyll i samlings-id!");
    COMMON_MAP_SV.put(CommonNames.MissingFamily, "Fyll i familjnamn!");
    COMMON_MAP_SV.put(CommonNames.MissingGenus, "Fyll i släktnamn!");
    COMMON_MAP_SV.put(CommonNames.MissingSpecies, "Fyll i artnamn!");
    COMMON_MAP_SV.put(CommonNames.DuplicatValue, " är valt i listan!");

    COMMON_MAP_SV.put(CommonNames.DestructivePolicy, "Destructive Sampling/DNA policy");
    COMMON_MAP_SV.put(CommonNames.ScientificLoanPolicy, "Lånepolicy för vetenskapliga ändamål");
    COMMON_MAP_SV.put(CommonNames.CommonLoanPolicy, "Allmän lånepolicy");
    COMMON_MAP_SV.put(CommonNames.PreservationTypeNotSpecified, "ej specificerad");

    COMMON_MAP_SV.put(CommonNames.RequestFailed, "Låneansökan misslyckades.  Försök igen.");
    COMMON_MAP_SV.put(CommonNames.UploadFileFailed, "Kunde inte ladda upp filen.  Kontrollera filstorlek eller filtyp.");

    COMMON_MAP_SV.put(CommonNames.SendingEmailsFailed, "Misslyckades med e-mailutskick. Vänligen kontakta låneadministrator: tobias.malm@nrm.se");

    COMMON_MAP_SV.put(CommonNames.Idle, "Ingen aktivitet");
    COMMON_MAP_SV.put(CommonNames.IdleMsg, "Sidan är inaktiv i två timmar. Session har gått ut. Redirectory till startsidan.");

    COMMON_MAP_SV.put(CommonNames.DataSourceConnectionError, "Databas anslutningsfel");
  }

  public static String getName(String key, String locale) {
    return locale.equals("en") ? NAME_MAP_EN.get(key) : NAME_MAP_SV.get(key);
  }
 
  public static String getMainMenuSvByKey(Enum key) {
    return MAIN_MENU_MAP_SV.get(key);
  }

  public static String getMainMenuEnByKey(Enum key) {
    return MAIN_MENU_MAP_EN.get(key);
  }

  public static String getEduMenuSvByKey(Enum key) {
    return EDUCATION_MENU_MAP_SV.get(key);
  }

  public static String getEduMenuEnByKey(Enum key) {
    return EDUCATION_MENU_MAP_EN.get(key);
  }

  public static String getArtMenuSvByKey(Enum key) {
    return ART_MENU_MAP_SV.get(key);
  }

  public static String getArtMenuEnByKey(Enum key) {
    return ART_MENU_MAP_EN.get(key);
  }

  public static Map<Enum, String> getMAIN_MENU_MAP_EN() {
    return MAIN_MENU_MAP_EN;
  }

  public static Map<Enum, String> getMAIN_MENU_MAP_SV() {
    return MAIN_MENU_MAP_SV;
  }

  public static Map<Enum, String> getEDUCATION_MENU_MAP_EN() {
    return EDUCATION_MENU_MAP_EN;
  }

  public static Map<Enum, String> getEDUCATION_MENU_MAP_SV() {
    return EDUCATION_MENU_MAP_SV;
  }

  public static Map<Enum, String> getART_MENU_MAP_EN() {
    return ART_MENU_MAP_EN;
  }

  public static Map<Enum, String> getART_MENU_MAP_SV() {
    return ART_MENU_MAP_SV;
  }

  public static String getMsgByKey(Enum key, boolean isSwedish) {
    return isSwedish ? COMMON_MAP_SV.get(key) : COMMON_MAP_EN.get(key);
  }

}
