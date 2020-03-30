package se.nrm.dina.loan.web.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.context.RequestContext;

/**
 *
 * @author idali
 */
@Named("languages")
@SessionScoped
@Slf4j
public class Languages implements Serializable {

  private String locale = "en";
  private boolean openGbif = false;
  
  public Languages() {
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    openGbif = false;
    this.locale = locale;
  }

  public String getLanguage() {
    openGbif = false; 
    return locale.equals("sv") ? "Change language to: English" : "Byt språk till: Svenska";
  }

  public boolean isIsSwedish() {
    return locale.equals("sv");
  }

  public boolean isOpenGbif() {
    return openGbif;
  }

  public void setOpenGbif(boolean openGbif) {
    this.openGbif = openGbif;
  } 
}
