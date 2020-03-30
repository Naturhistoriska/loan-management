package se.nrm.dina.loan.admin.util;

//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.faces.model.SelectItem;
import se.nrm.dina.mongodb.loan.vo.Loan;

/**
 *
 * @author idali
 */
public class Util {

  private final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  private static Util instance = null;

  public static synchronized Util getInstance() {
    if (instance == null) {
      instance = new Util();
    }
    return instance;
  }

  public List<String> getCuratorsFromLoanList(List<Loan> loans) { 
    return loans.stream()
              .filter(l -> l.getCurator() != null)
              .map(l -> l.getCurator())
            .distinct()
            .sorted()
            .collect(Collectors.toList());
  }

  public SelectItem[] buildSelectItemOptions(List<String> list) {
    SelectItem[] options = new SelectItem[list.size() + 1];
    options[0] = new SelectItem("", "Select");
    IntStream.range(0, list.size()) 
            .forEach(i -> {
              options[i + 1] = new SelectItem(list.get(i), list.get(i));
            });
    return options;
  }
     

  /**
   * Convert Date to String with "yyyy-MM-dd" format
   *
   * @param date - Date
   * @return String
   */
  public String dateToString(Date date) {
    if (date == null) {
      return "";
    }
    return FORMAT.format(date);
  }

  public String getTodayAsString() {
    Calendar now = Calendar.getInstance();
    Date date = now.getTime();
    return FORMAT.format(date);
  }

  public static Date getTodayDate() {
    Calendar now = Calendar.getInstance();
    return now.getTime();
  }

  public Date stringToDate(String strDate) {

    if (strDate == null || strDate.isEmpty()) {
      return null;
    }
    try {
      return (Date) FORMAT.parse(strDate);
    } catch (ParseException ex) {
      return null;
    }
  }

  public Date getStartDate() {
    try {
      return (Date) FORMAT.parse("2014-01-01");
    } catch (ParseException ex) {
      return null;
    }
  }

}
