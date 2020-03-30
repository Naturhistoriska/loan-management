package se.nrm.dina.loan.web.logic;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.email.Mail;
import se.nrm.dina.email.NrmMail;
import se.nrm.dina.loan.web.util.Department;
import se.nrm.dina.manager.dao.AccountDao;
import se.nrm.dina.manager.entities.TblUsers;
import se.nrm.dina.mongodb.loan.vo.Loan;

/**
 *
 * @author idali
 */
@Slf4j
public class LoanMailSender implements Serializable {
   
  @EJB
  private AccountDao dao;
   
  @Inject
  private Mail mail;

  @Inject
  private NrmMail nrmMail;
  
  public LoanMailSender() {
    
  }
  
  public void sendMails(String loanId, Loan loan, String department, String uuid,
          String loanPolicy, boolean isSwedish, boolean hasPrimaryContact, boolean isLocal) 
          throws MessagingException, AddressException, UnsupportedEncodingException { 
    log.info("sendMails");

    Map<String, String> emailMap = new HashMap<>();
    emailMap.put("isSwedish", String.valueOf(isSwedish));
    emailMap.put("loanId", loanId);
    emailMap.put("primarytitle", loan.getPrimaryUser().getTitle());
    emailMap.put("primarylastname", loan.getPrimaryUser().getLastname());
    emailMap.put("primaryFirstname", loan.getPrimaryUser().getFirstname());
    emailMap.put("primaryemail", loan.getPrimaryUser().getEmail());

    emailMap.put("collection", loan.getDepartment());
    emailMap.put("purpose", loan.getPurpose());
    emailMap.put("type", loan.getType());
    emailMap.put("subcollection", loan.getReleventCollection());

    if (loan.getPrimaryUser().getDepartment() != null && !loan.getPrimaryUser().getDepartment().isEmpty()) {
      emailMap.put("primarydepartment", loan.getPrimaryUser().getDepartment());
    }
    emailMap.put("primaryinstitution", loan.getPrimaryUser().getInstitution());
    emailMap.put("primarycountry", loan.getPrimaryUser().getAddress().getCountry());

    emailMap.put("hasPrimaryContact", String.valueOf(hasPrimaryContact));
    emailMap.put("isLocal", String.valueOf(isLocal));

    if (hasPrimaryContact) {
      emailMap.put("secondarytitle", loan.getSecondaryUser().getTitle());
      emailMap.put("secondarylastname", loan.getSecondaryUser().getLastname());
      emailMap.put("secondaryfirstname", loan.getSecondaryUser().getFirstname());
      emailMap.put("secondaryemail", loan.getSecondaryUser().getEmail());
      if (loan.getSecondaryUser().getDepartment() != null && !loan.getSecondaryUser().getDepartment().isEmpty()) {
        emailMap.put("secondarydepartment", loan.getSecondaryUser().getDepartment());
      }
      emailMap.put("secondaryinstitution", loan.getPrimaryUser().getInstitution());
      emailMap.put("secondarycountry", loan.getPrimaryUser().getAddress().getCountry());
    } 
    emailMap.put("department", Department.getDepartName(department)); 
    StringBuilder sb = new StringBuilder();
    sb.append(uuid);
    sb.append("-loanrequest_");
    sb.append(loanId); 
    StringBuilder adminSb = new StringBuilder(sb.toString());
    adminSb.append("_admin.pdf"); 
    sb.append(".pdf");
    emailMap.put("summaryFile", sb.toString().trim());
    emailMap.put("adminSummaryFile", adminSb.toString().trim());

    TblUsers curator = dao.findOneUserByEmail(loan.getCurator());
    if (curator != null) {
      emailMap.put("outofoffice", String.valueOf(curator.getOnvacation()));
    }
    emailMap.put("curratormail", loan.getCurator());
    emailMap.put("manager", loan.getManager()); 
    emailMap.put("loanPolicy", loanPolicy); 
    if (isLocal) {
      mail.send(emailMap);
    } else {
      nrmMail.send(emailMap);
    }
  } 
}
