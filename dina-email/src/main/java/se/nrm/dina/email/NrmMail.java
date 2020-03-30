package se.nrm.dina.email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import lombok.extern.slf4j.Slf4j; 
import se.nrm.dina.email.vo.LoanMail;
import se.nrm.dina.email.vo.NewAccountMail;
import se.nrm.dina.email.vo.PasswordRecoveryMail;

/**
 *
 * @author idali
 */
@Stateless
@Slf4j
public class NrmMail {
 
  private final static String SMTP_HOST_NAME = "mail.smtp.host";
  private final static String HOST = "mail.nrm.se";

  private final String LOCAL_LOAN_PDF = "/Users/idali/Documents/onlineloans/loan_files/";
  private final String REMOTE_LOAN_PDF_AS = "/home/admin/wildfly-8.1.0-0/loans/";

  private LoanMail loan;
  private NewAccountMail account;
  private PasswordRecoveryMail recovery;

  private String pdfFileName;

  public NrmMail() {

  }

  /**
   * Email send to new admin user when new admin account created
   *
   * @param username
   * @param password
   * @param emailAddress
   */
  public void sendNewAdminAccountMail(String username, String password, String emailAddress) {
    log.info("sendNewAdminAccountMail : {} ", emailAddress);

    Properties props = new Properties();
    props.put(SMTP_HOST_NAME, HOST);
    Session session = Session.getInstance(props, null);

    Message message = new MimeMessage(session);
    session.setDebug(true);

    account = new NewAccountMail();

    try {
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
      message.setSubject(MimeUtility.encodeText("Loan admin new account", "utf-8", "B"));

      message.setContent(account.appendMailBody(username, password), "text/html; charset=ISO-8859-1");
      Transport.send(message);
    } catch (MessagingException | UnsupportedEncodingException ex) {
      log.error(ex.getMessage());
    }
  }

  public void sendPasswordRecoverEmail(final String email, final String password) {
    log.info("sendPasswordRecoverEmail : {}", email);

    Properties props = new Properties();
    props.put(SMTP_HOST_NAME, HOST);

    Session session = Session.getInstance(props, null);
    Message message = new MimeMessage(session);
    try {
      recovery = new PasswordRecoveryMail();
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
      message.setSubject(MimeUtility.encodeText("Loan admin password recovery", "utf-8", "B"));
      message.setContent(recovery.buildPasswordRecoveryMsg(password), "text/html; charset=ISO-8859-1");
      Transport.send(message);
    } catch (MessagingException | UnsupportedEncodingException ex) {
      log.warn(ex.getMessage());
    }
  }

  /**
   * Email send to curator and loan requester when a new loan request created
   *
   * @param map
   * @throws AddressException
   * @throws MessagingException
   * @throws UnsupportedEncodingException
   */
  public void send(Map<String, String> map) throws AddressException, MessagingException, UnsupportedEncodingException { 
    log.info("send : {} -- {}", map.get("primaryemail"), map.get("secondaryemail"));

    Properties props = new Properties();
    props.put(SMTP_HOST_NAME, HOST);
    Session session = Session.getInstance(props, null);
    session.setDebug(true);

    loan = new LoanMail(map);
    buildPdfFilePath(map, "adminSummaryFile");

    String primaryEmail = map.get("primaryemail");
    String secondaryEmail = map.get("secondaryemail");

    String adminEmail = map.get("manager");
    String curatorEmail = map.get("curratormail");

//        testEmail = primaryEmail;
//        // for testing now
//        adminEmail = borrowerEmail;
    String outofoffice = map.get("outofoffice");
    boolean isOut = outofoffice == null ? false : Boolean.valueOf(outofoffice);

    Message message = new MimeMessage(session);
    if (Boolean.valueOf(map.get("hasPrimaryContact"))) {
      sendMailToPrimaryBorrower(primaryEmail, message);
      message = new MimeMessage(session);
      sendMailToBorrower(secondaryEmail, message);
    } else {
      sendMailToBorrower(primaryEmail, message);
    }

    message = new MimeMessage(session);
    sendMailToAdmin(adminEmail, curatorEmail, message);

    if (isOut) {
      message = new MimeMessage(session);
      sendOutOfOfficeNotification(primaryEmail, secondaryEmail, message);
    }
  }

  private void buildPdfFilePath(Map<String, String> map, String key) {

    StringBuilder sb = new StringBuilder();
    String host = map.get("isLocal");
    if (host.contains("localhost")) {
      sb.append(LOCAL_LOAN_PDF); 
    } else {
      sb.append(REMOTE_LOAN_PDF_AS);
    }
    sb.append(map.get(key).replace("-", "/"));

    pdfFileName = sb.toString();
  }

  private void sendOutOfOfficeNotification(String primary, String secondary, Message message) throws MessagingException, UnsupportedEncodingException {
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(primary));

    if (secondary != null && !secondary.isEmpty()) {
      message.addRecipient(Message.RecipientType.CC, new InternetAddress(secondary));
    }

    message.setSubject(MimeUtility.encodeText(loan.buildOutOfOfficeSubject(), "utf-8", "B"));
    message.setContent(loan.buildOutOfOfficeMailBody(), "text/html; charset=ISO-8859-1");
    Transport.send(message);
  }

  private void sendMailToBorrower(String emailAddress, Message message) throws MessagingException {
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
    message.setSubject(loan.buildSubject());

    message.setContent(loan.buildBorrowerMailBody(), "text/html; charset=ISO-8859-1");
    Transport.send(message);
  }

  private void sendMailToPrimaryBorrower(String emailAddress, Message message) throws MessagingException, UnsupportedEncodingException {

    message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
    message.setSubject(loan.buildSubject());

    message.setContent(loan.buildPrimaryBorrowerMailBody(), "text/html; charset=ISO-8859-1");
    Transport.send(message);
  }

  private void sendMailToAdmin(String adminEmail, String curatorEmail, Message message) throws MessagingException, UnsupportedEncodingException {
    log.info("sendAdminMail : {} -- {}", adminEmail, curatorEmail);

    message.addRecipient(Message.RecipientType.TO, new InternetAddress(adminEmail));
    message.addRecipient(Message.RecipientType.CC, new InternetAddress(curatorEmail));

    message.setSubject(MimeUtility.encodeText(loan.buildAdminMailSubject(), "utf-8", "B"));

    String body = loan.buildAdminEmailBody();

    BodyPart messageBodyPart1 = new MimeBodyPart();
    messageBodyPart1.setContent(body, "text/html; charset=ISO-8859-1");

    File pdfFile = new File(pdfFileName);

    MimeBodyPart messageBodyPart2 = new MimeBodyPart();
    DataSource source = new FileDataSource(pdfFile);
    messageBodyPart2.setDataHandler(new DataHandler(source));

    messageBodyPart2.setFileName(loan.getPdfFileName());

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart1);
    multipart.addBodyPart(messageBodyPart2);

    message.setContent(multipart);
    Transport.send(message);
  }
}
