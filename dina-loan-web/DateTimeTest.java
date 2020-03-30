/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.loan.web;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle; 
import java.util.Date;
import java.util.Locale; 

/**
 *
 * @author idali
 */
public class DateTimeTest {
    public static void main(String[] args) {
        
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' HH.mm.ss");  
        
        
        LocalDate today = LocalDate.now();  
        
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
        
        LocalDate minDate = LocalDate.parse("29.11.2015", germanFormatter);  
        LocalDate maxDate = LocalDate.parse("01.01.2016", germanFormatter);  
        
        if (today.isAfter(minDate) && today.isBefore(maxDate)) {
            try {
                

                LocalDate startDate = LocalDate.parse("14.01.2016", germanFormatter);  

                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(startDate.toString()); 
                
                System.out.println("date : " + date);
            } catch (ParseException ex) {
                
            }
        } else { 
            System.out.println("not in range");
        } 
    }
}
