package se.nrm.dina.email.vo;
 

/**
 *
 * @author idali
 */
public class PasswordRecoveryMail { 

  private final String START_DIV_TAG_WITH_FONT = "<div style=\"font-size: 1.2em; \">";
  private final String START_DIV_TAG = "<div>";
  private final String END_DIV_TAG = "</div>";
  private final String BR_TAG = "<br />";
  private final String START_LINK_TAG = "<a href=\"";

  private final boolean isLocal;

  public PasswordRecoveryMail() {
    isLocal = System.getProperty("os.name").trim().equals("Mac OS X");
  }

  public String buildPasswordRecoveryMsg(final String password) {
    StringBuilder sb = new StringBuilder();
    sb.append(START_DIV_TAG_WITH_FONT);
    sb.append(START_DIV_TAG);
    sb.append("Hi,");
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append("Your online loan account password has been chaged.  You can login with the new password:  ");
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append("Password: ");
    sb.append(password);
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append("Click ");
    sb.append(START_LINK_TAG);
    if (isLocal) {
      sb.append("http://localhost:8080/loan-admin\"");
    } else {
      sb.append("https://www.dina-web.net/loan-admin\"");
    }
    sb.append(" to login and change password");
    sb.append(END_DIV_TAG);
    sb.append(END_DIV_TAG);

    return sb.toString();
  }

}
