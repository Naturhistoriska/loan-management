package se.nrm.dina.loan.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author idali
 */
public class Main {

  public static void main(String[] args) throws ParseException {

    String dateStr = "Tue Jul 22 00:00:00 CEST 2014";
    DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

    Date date = (Date) formatter.parse(dateStr);
    System.out.println(date);

    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    System.out.println("str date : " + format.format(date));
  }
}
