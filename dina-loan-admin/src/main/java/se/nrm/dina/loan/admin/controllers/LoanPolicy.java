package se.nrm.dina.loan.admin.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.FileUploadEvent;
import se.nrm.dina.loan.admin.policypdf.FileHandler;

/**
 *
 * @author idali
 */
@SessionScoped
@Named("policy")
@Slf4j
public class LoanPolicy implements Serializable {
  
  
  private final String LOCAL_EXTERNAL_FILES = "https://localhost:8443/loan-admin/pdf?"; 
  private final String REMOTE_EXTERNAL_FILES_AS = "//www.dina-web.net/dina-admin/pdf?";

  private boolean isNewPolicy;
  private final String externalFilePath;
  private final String servername;
  
  
  @Inject
  private FileHandler fileHandler;
  
  public LoanPolicy() {
    isNewPolicy = false;
    
    servername = ((HttpServletRequest) FacesContext.getCurrentInstance()
            .getExternalContext().getRequest()).getServerName();
    log.info("servername : {}", servername);
    if (servername.contains("localhost")) { 
      externalFilePath = LOCAL_EXTERNAL_FILES;
    } else {
      externalFilePath = REMOTE_EXTERNAL_FILES_AS;
    }
  }
  
   public void uploadScFile(FileUploadEvent event) {
    log.info("uploadScFile : {} -- {}", event.getFile(), event.getFile().getFileName());
    fileHandler.saveFile(event.getFile(), true); 
    isNewPolicy = true;
  }

  public void uploadEdFile(FileUploadEvent event) { 
    log.info("uploadEdFile : {} -- {}", event.getFile(), event.getFile().getFileName());
    fileHandler.saveFile(event.getFile(), false);

    isNewPolicy = true;
  }

  public void updatePolicy() {
    log.info("updatePolicy"); 
    isNewPolicy = false;
  }
 
  public boolean isIsNewPolicy() {
    return isNewPolicy;
  }
 
  public String getScPolicy() {
    log.info( "getScPolicy : {}",  externalFilePath + "pdf=Loanpolicyforscientificpurposes.pdf");
    return externalFilePath + "pdf=Loanpolicyforscientificpurposes.pdf";
  }

  public String getEdPolicy() {
    log.info( "getEdPolicy : {}",  externalFilePath);
    return externalFilePath + "pdf=Loanpolicycommon.pdf";
  } 
}
