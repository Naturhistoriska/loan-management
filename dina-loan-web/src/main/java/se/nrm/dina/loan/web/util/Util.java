package se.nrm.dina.loan.web.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale; 

/**
 *
 * @author idali
 *
 *
 */
public class Util {
 
  private static LocalDate today;
  private static int year;
  private static int nextYear;
  private static Timestamp timeStamp;

  private final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private final static DateTimeFormatter GERMAN_FORMATTER = 
          DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

  public static Date addWeeksToDate(int numOfWeeks) { 
    Calendar now = Calendar.getInstance();

    now.add(Calendar.WEEK_OF_YEAR, numOfWeeks);
    return now.getTime();
  }

  /**
   * This method is only for Christmas holiday
   *
   * @return
   */
  public static Date holidayMinDate() { 

    if (isHoliday()) {
      String holidayStr = "14.01." + nextYear;
      try {
        LocalDate startDate = LocalDate.parse(holidayStr, GERMAN_FORMATTER);

        return new SimpleDateFormat("yyyy-MM-dd").parse(startDate.toString());
      } catch (ParseException ex) {
        return addWeeksToDate(6);
      }
    } else {
      return addWeeksToDate(2);
    }
  }

  public static boolean isHoliday() {

    today = LocalDate.now();
    year = today.getYear();
    nextYear = year + 1;
    String minStr = "30.11." + year;
    String maxStr = "01.01." + nextYear;

    LocalDate minDate = LocalDate.parse(minStr, GERMAN_FORMATTER);
    LocalDate maxDate = LocalDate.parse(maxStr, GERMAN_FORMATTER);
    return today.isAfter(minDate) && today.isBefore(maxDate);
  }

  /**
   * Convert Date to String with "yyyy-MM-dd" format
   *
   * @param date - Date
   * @return String
   */
  public static String dateToString(Date date) {
    if (date == null) {
      return "";
    }
    return FORMAT.format(date);
  }
  
  public static String getTimeStamp() {
    timeStamp = new Timestamp(System.currentTimeMillis());
    return dateToString(timeStamp);
  }
}
