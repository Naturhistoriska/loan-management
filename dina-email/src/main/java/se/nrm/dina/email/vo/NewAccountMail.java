package se.nrm.dina.email.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author idali
 */
public class NewAccountMail {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final String START_DIV_TAG_WITH_FONT = "<div style=\"font-size: 1.2em; \">";
  private final String START_DIV_TAG = "<div>";
  private final String END_DIV_TAG = "</div>";
  private final String BR_TAG = "<br />";
  private final String START_LINK_TAG = "<a href=\"";

  private final boolean isLocal;

  public NewAccountMail() {
    isLocal = System.getProperty("os.name").trim().equals("Mac OS X");
  }

  public String appendMailBody(String username, String password) {

    logger.info("Account ");

    StringBuilder sb = new StringBuilder();
    sb.append(START_DIV_TAG_WITH_FONT);
    sb.append(START_DIV_TAG);
    sb.append("<h3>");
    sb.append("Hi ");
    sb.append(username);
    sb.append("</h3>");
    sb.append(BR_TAG);
    sb.append(BR_TAG);

    sb.append("Your online loan account has been created with the following information:  ");
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append("Username: ");
    sb.append(username);
    sb.append(BR_TAG);
    sb.append("Password: ");
    sb.append(password);
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append("Click ");
    sb.append(START_LINK_TAG);
    if (isLocal) {
      sb.append("http://localhost:8080/loan-admin\"");
//        } else if (host.contains("dina-loans")) {
//            sb.append("http://dina-loans.nrm.se/loan-admin\"");
    } else {
      sb.append("https://www.dina-web.net/loan-admin\"");
    }
    sb.append(" target=\"_blank\">NRM Online Loan Form Administration</a>");
    sb.append(" to login and change password");
    sb.append(END_DIV_TAG);
    sb.append(END_DIV_TAG);

    return sb.toString();
  }

}
