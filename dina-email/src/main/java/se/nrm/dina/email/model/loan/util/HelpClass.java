package se.nrm.dina.email.model.loan.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author idali
 */
public class HelpClass {

  private final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * Convert Date to String with "yyyy-MM-dd" format
   *
   * @param date - Date
   * @return String
   */
  public static String dateToString(Date date) {

    if (date == null) {
      return null;
    } else {
      return df.format(date);
    }
  }

  public static String getNow() {
    Date now = new Date();
    return FORMAT.format(now);
  }
}
