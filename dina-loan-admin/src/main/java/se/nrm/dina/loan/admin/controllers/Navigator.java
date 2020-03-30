package se.nrm.dina.loan.admin.controllers;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named; 
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author idali
 */
//@ManagedBean(name = "navigationController", eager = true)
@Named("navigate")
@RequestScoped
@Slf4j
public class Navigator implements Serializable {
  
  private ExternalContext externalContext;
 
  private static final String HOME_PATH = "/faces/secure/home.xhtml";
  private static final String CHANGE_PASSWORD_PATH = "/faces/secure/changepassword.xhtml";
  private static final String USER_ACCOUNT_PATH = "/faces/secure/manageaccount.xhtml";
  private static final String COLLECTION_PATH = "/faces/secure/managecollection.xhtml";
  private static final String LOAN_POLICY_PATH = "/faces/secure/loanpolicy.xhtml";
  private static final String LOAN_PATH = "/faces/secure/manageloan.xhtml";

  public String processHome() {
    return "/secure/home.xhtml";
  }
  
  public void gotoHome() {
    redirectPage(HOME_PATH);
  }
  
  public void gotoManageLoanPage() {
    redirectPage(LOAN_PATH);
  }
    
  public void gotoLoanPolicyPage() {
    redirectPage(LOAN_POLICY_PATH);
  }
  
  
  public void gotoManageCollectionPage() {
    redirectPage(COLLECTION_PATH);
  }
  
  public void gotoChangePasswordPage() {
    redirectPage(CHANGE_PASSWORD_PATH);
  }

  public void gotoManageAccountPage() {
    redirectPage(USER_ACCOUNT_PATH);
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
