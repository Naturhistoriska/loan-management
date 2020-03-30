package se.nrm.dina.loan.admin.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author idali
 */
@Named("message")
@SessionScoped
public class Messages implements Serializable {
  
  public Messages() {
    
  }
  
  public void addError(String errorTitle, String errorMsg) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorTitle, errorMsg));
  }

  public void addInfo(String msgTitle, String msg) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msgTitle, msg));
  }

  public void addWarning(String warningTitle, String warningMsg) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, warningTitle, warningMsg));
  }
}
