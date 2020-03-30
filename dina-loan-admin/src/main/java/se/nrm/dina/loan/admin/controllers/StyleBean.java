package se.nrm.dina.loan.admin.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author idali
 */
@SessionScoped
@Named
public class StyleBean implements Serializable {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final String ODD_ROW = "oddpanel";
  private final String EVEN_ROW = "evenpanel";

  private final String LOAN_PENDING = "requestPending";
  private final String LOAN_DENIED = "loanDenied";
  private final String LOAN_ACCEPTED = "loanAccepted";

  public StyleBean() {
  }

  public String loanRowColor(String status) {
    switch (status) {
      case "Request pending":
        return LOAN_PENDING;
      case "Request denied":
        return LOAN_DENIED;
      case "Request accepted":
        return LOAN_ACCEPTED;
      default:
        return "Request pending";
    }
  }

  public String getPanelColor(int step) {
    logger.info("getPanelColor : {}", step);

    return (step % 2 == 0) ? ODD_ROW : EVEN_ROW;
  }
}
