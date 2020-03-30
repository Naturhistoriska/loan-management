package se.nrm.dina.loan.web.controllers;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import se.nrm.dina.loan.web.util.CommonString;

/**
 *
 * @author idali
 */
@ApplicationScoped
@Named("policy")
public class LoanPolicies implements Serializable {
  
  private final String LOCAL_EXTERNAL_FILES = "http://localhost:8080/loan/pdf?pdf=";
  private final String REMOTE_EXTERNAL_FILES_AS = "https://www.dina-web.net/loan/pdf?pdf=";

  
  private final String DESTRUCTIVE_SAMPLING_POLICY;
  private final String SCIENTIFIC_PURPOSE_LOAN_POLICY;
  private final String EDUCATIONAL_PURPOSE_LOAN_POLICY;
  private final String SCIENTIFIC_PURPOSE_LOAN_POLICY_PAGE_ONE;
  private final String EDUCATIONAL_PURPOSE_LOAN_POLICY_PAGE_ONE;
  
  private final String localhost = "localhost";
  private final String servername; 
  
  public LoanPolicies() {
    String basePath;
    servername = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerName();
    if (servername.contains(localhost)) {
      basePath = LOCAL_EXTERNAL_FILES; 
    } else {
      basePath = REMOTE_EXTERNAL_FILES_AS; 
    }  
    SCIENTIFIC_PURPOSE_LOAN_POLICY = basePath + CommonString.getPolicyPdfName(true);
    DESTRUCTIVE_SAMPLING_POLICY = SCIENTIFIC_PURPOSE_LOAN_POLICY + "#page=2";
    SCIENTIFIC_PURPOSE_LOAN_POLICY_PAGE_ONE = SCIENTIFIC_PURPOSE_LOAN_POLICY + "#page=1";
    EDUCATIONAL_PURPOSE_LOAN_POLICY = basePath + CommonString.getPolicyPdfName(false);
    EDUCATIONAL_PURPOSE_LOAN_POLICY_PAGE_ONE = EDUCATIONAL_PURPOSE_LOAN_POLICY + "#page=1";
  }
  
  public String getDestructiveSamplingPolicy() {
    return DESTRUCTIVE_SAMPLING_POLICY;
  }

  public String getScientificPurposeLoanPolicy() {
    return SCIENTIFIC_PURPOSE_LOAN_POLICY;
  }

  public String getEducationalPurposeLoanPolicy() {
    return EDUCATIONAL_PURPOSE_LOAN_POLICY;
  }  

  public String getScientificPurposeLoanPolicyPageOne() {
    return SCIENTIFIC_PURPOSE_LOAN_POLICY_PAGE_ONE;
  }
  
  public String getEducationalPurposeLoanPolicyPageOne() {
    return EDUCATIONAL_PURPOSE_LOAN_POLICY_PAGE_ONE;
  }
}
