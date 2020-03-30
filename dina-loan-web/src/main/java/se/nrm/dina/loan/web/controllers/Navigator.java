package se.nrm.dina.loan.web.controllers;

import java.io.IOException;
import java.io.Serializable; 
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j; 

/**
 *
 * @author idali
 */
@Named("navigate")
@SessionScoped
@Slf4j
public class Navigator implements Serializable {

  private ExternalContext externalContext;

  private static final String HOME_PATH = "/faces/pages/home.xhtml";
  private static final String ABOUT_PATH = "/faces/pages/about.xhtml";
  private static final String POLICY_PATH = "/faces/pages/policy.xhtml";
  private static final String CONTACT_PATH = "/faces/pages/contact.xhtml"; 

  private HttpServletRequest req;
  
  private boolean isHomePage;
   
  @Inject
  private StyleBean style;
  @Inject
  private BreadCrumbBean breadCrumb;
    
  public Navigator() {
    log.info("Navigator");
    isHomePage = true;
  }
  
  public void gotoHomePage() {
    style.setCurrentTab(1);
    isHomePage = true; 
    redirectPage(HOME_PATH);
  }

  public void gotoAboutPage() {
    log.info("gotoAboutPage");
    style.setCurrentTab(2);
    isHomePage = false;
    breadCrumb.setNavigationPathEnabled(false);
    redirectPage(ABOUT_PATH);
  }

  public void gotoPolicyPage() {
    log.info("gotoPolicyPage");
    style.setCurrentTab(3);
    isHomePage = false;
    breadCrumb.setNavigationPathEnabled(false);
    redirectPage(POLICY_PATH);
  }

  public void gotoContactPage() {
    log.info("gotoContactPage");
    style.setCurrentTab(4);
    isHomePage = false;
    breadCrumb.setNavigationPathEnabled(false);
    redirectPage(CONTACT_PATH);
  }

  public boolean isIsHomePage() {
    return isHomePage;
  }
   
  private void redirectPage(String path) {
    log.info("redirectPage : {}", path);
 
    externalContext = FacesContext.getCurrentInstance().getExternalContext();
    try {
      externalContext.redirect(externalContext.getRequestContextPath() + path);
    } catch (IOException ex) {
    } 
  } 
}
