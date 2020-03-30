package se.nrm.dina.loan.admin.logic;

import java.io.Serializable;
import javax.inject.Inject;
import se.nrm.dina.email.Mail;
import se.nrm.dina.email.NrmMail;

/**
 *
 * @author idali
 */
public class MailHandler implements Serializable {
   
  @Inject
  private Mail mail;
   
  @Inject
  private NrmMail nrmMail;
  
  public MailHandler() {

  }

  public void sendMail(String emailAddress, String password, String serverName) {
    if (serverName.contains("localhost")) { 
      mail.sendNewAdminAccountMail(serverName, password, emailAddress);
    } else {
      nrmMail.sendNewAdminAccountMail(serverName, password, emailAddress);
    } 
  }
}
