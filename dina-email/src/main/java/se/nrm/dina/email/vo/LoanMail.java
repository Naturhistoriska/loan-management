package se.nrm.dina.email.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.nrm.dina.email.model.loan.util.ConstantText;

/**
 *
 * @author idali
 */
public class LoanMail implements Serializable {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final String loanId;
  private final String primarytitle;
  private final String primarylastName;
  private final String primaryfirstName;
  private final String primaryinstitution;
  private final String primarydepartment;
  private final String primarycountry;

  private final String secondarytitle;
  private final String secondarylastName;
  private final String secondaryfirstName;
  private final String secondaryinstitution;
  private final String secondarydepartment;
  private final String secondarycountry;

  private final String collection;
  private final String purpose;
  private final String type;
  private final String subCollection;

  private final String department;
  private final String summaryFilePath;
  private final String policyFileName;
  private final String managerEmail;
  private final String curator;

  private final boolean hasPrimary;
  private final boolean isSwedish;
  private final String host;

  private final String START_DIV_TAG_WITH_FONT = "<div style=\"font-size: 1.2em; \">";
  private final String START_DIV_TAG = "<div>";

  private final String END_DIV_TAG = "</div>";
  private final String BR_TAG = "<br />";
  private final String BOLD = "<b>";
  private final String BOLD_END = "</b>";

  private final String START_LINK_TAG = "<a href=\"";
  private final String END_LINK_TAG = "</a>";

  private final String END_TAG = "\" >";

  public LoanMail(Map<String, String> map) {

    this.isSwedish = Boolean.valueOf(map.get("isSwedish"));
    this.hasPrimary = Boolean.valueOf(map.get("hasPrimaryContact"));
    this.host = map.get("isLocal");

    this.loanId = map.get("loanId");

    this.primarytitle = map.get("primarytitle").trim();
    this.primarylastName = map.get("primarylastname").trim();
    this.primaryfirstName = map.get("primaryFirstname");
    this.primaryinstitution = map.get("primaryinstitution");
    this.primarydepartment = map.get("primarydepartment");
    this.primarycountry = map.get("primarycountry");

    this.managerEmail = map.get("manager");

    if (hasPrimary) {
      this.secondarytitle = map.get("secondarytitle").trim();
      this.secondarylastName = map.get("secondarylastname").trim();
      this.secondaryfirstName = map.get("secondaryfirstname").trim();
      this.secondaryinstitution = map.get("secondaryinstitution");
      this.secondarydepartment = map.get("secondarydepartment");
      this.secondarycountry = map.get("secondarycountry");
    } else {
      this.secondarytitle = "";
      this.secondarylastName = "";
      this.secondaryfirstName = "";
      this.secondaryinstitution = "";
      this.secondarydepartment = null;
      this.secondarycountry = "";
    }

    this.curator = map.get("curratormail");

    this.department = map.get("department").trim();
    this.collection = map.get("collection");
    this.purpose = map.get("purpose");
    this.type = map.get("type");
    this.subCollection = map.get("subcollection");

    this.summaryFilePath = map.get("summaryFile").trim();
    this.policyFileName = map.get("loanPolicy").trim();
  }

  private void appendTitle(StringBuilder sb) {
    sb.append(ConstantText.getInstance().getDEAR(isSwedish));
    sb.append(" ");
    sb.append(hasPrimary ? secondarytitle : primarytitle);
    sb.append(" ");
    sb.append(hasPrimary ? secondarylastName : primarylastName);
    sb.append(",");
  }

  private void appendMailTitle(final StringBuilder sb, String mailTitle) {
    sb.append("<h1>");
    sb.append(mailTitle);
    sb.append("</h1>");
    sb.append(new Timestamp(System.currentTimeMillis()));
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getReferenceNumber(isSwedish));
    sb.append(loanId);
    sb.append(BR_TAG);
    sb.append(BR_TAG);
  }

  private void appendExternalLink(final StringBuilder sb, final boolean isPolicy) {
    sb.append(START_LINK_TAG);
    sb.append(isPolicy ? "" : ConstantText.getInstance().getBaseExternalFilePath(host));
    sb.append(isPolicy ? policyFileName : summaryFilePath);
    sb.append(END_TAG);
    sb.append(isPolicy ? ConstantText.getInstance().getLoanPolicyLinkText(isSwedish) : ConstantText.getInstance().getLoanSummaryLinkText(isSwedish));
    sb.append(END_LINK_TAG);

    logger.info("policy link : {}", sb.toString());
  }

  private void appendBorrowEmail(StringBuilder sb) {

    sb.append(START_DIV_TAG_WITH_FONT);
    sb.append(START_DIV_TAG);
    appendTitle(sb);
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getLOAN_BODY_TEXT(isSwedish));
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    appendExternalLink(sb, false);
    sb.append(END_DIV_TAG);

    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(START_DIV_TAG);
    sb.append(ConstantText.getInstance().getSINCERELY(isSwedish));
//        sb.append(BR_TAG);
//        sb.append(ConstantText.getInstance().getCOLLECTION_MANAGER(isSwedish));
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getDEPARTMENT(isSwedish, department));
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getNRM(isSwedish));
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getPOBOX(isSwedish));
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getCOUNTRY(isSwedish));
    sb.append(END_DIV_TAG);
    sb.append(END_DIV_TAG);
  }

  private void appendOutOfOfficeEmail(StringBuilder sb) {

    sb.append(START_DIV_TAG_WITH_FONT);
    sb.append(START_DIV_TAG);
    appendTitle(sb);
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getOutOfOfficeNote1(isSwedish));
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getOutOfOfficeNote2(isSwedish));
    sb.append(BR_TAG);
    sb.append(BR_TAG);

    sb.append(END_DIV_TAG);

    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(START_DIV_TAG);
    sb.append(ConstantText.getInstance().getSINCERELY(isSwedish));
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getCOLLECTION_MANAGER(isSwedish));
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getDEPARTMENT(isSwedish, department));
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getNRM(isSwedish));
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getPOBOX(isSwedish));
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getCOUNTRY(isSwedish));
    sb.append(END_DIV_TAG);
    sb.append(END_DIV_TAG);
  }

  public String buildSubject() {

    StringBuilder sb = new StringBuilder();
    sb.append(ConstantText.getInstance().getRECEIPT(isSwedish));
    sb.append(" [");
    sb.append(ConstantText.getInstance().getReferenceNumber(isSwedish));
    sb.append(loanId);
    sb.append("]");

    return sb.toString();
  }

  public String buildOutOfOfficeSubject() {
    return buildSubject() + ConstantText.getInstance().getOutofOfficeTitle(isSwedish);
  }

  public String buildAdminMailSubject() {
    StringBuilder sb = new StringBuilder();
    if (type.equals("Information")) {
      sb.append(ConstantText.getInstance().getInformationRequest());
    } else {
      sb.append(ConstantText.getInstance().getLoanRequest(false));
    }
    sb.append(" [");
    sb.append(ConstantText.getInstance().getReferenceNumber(false));
    sb.append(loanId);
    sb.append("]");

    return sb.toString();
  }

  public String getPdfFileName() {
    return "loanrequest_" + loanId + "_admin.pdf";
  }

  public String buildPrimaryBorrowerMailBody() {

    logger.info("buildPrimaryBorrowerMailBody");

    StringBuilder sb = new StringBuilder();

    sb.append(ConstantText.getInstance().getHi(isSwedish));
    sb.append(" ");
    sb.append(primarytitle);
    sb.append(" ");
    sb.append(primarylastName);
    sb.append(BR_TAG);
    sb.append(BR_TAG);

    sb.append(ConstantText.getInstance().getPrimaryText(isSwedish));
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    appendExternalLink(sb, true);
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(ConstantText.getInstance().getAcceptText(isSwedish));
    sb.append(BR_TAG);
    sb.append(BR_TAG);

    sb.append(ConstantText.getInstance().getManagerText1(isSwedish));
    sb.append(curator);
    sb.append(ConstantText.getInstance().getManagerText2(isSwedish));

    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;        ");
    sb.append(ConstantText.getInstance().getAgreementAccept(isSwedish));
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;        ");
    sb.append(ConstantText.getInstance().getAgreementDecline(isSwedish));
    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(BR_TAG);

    appendMailTitle(sb, ConstantText.getInstance().getRECEIPT(isSwedish));

    appendBorrowEmail(sb);

    return sb.toString();
  }

  public String buildBorrowerMailBody() {

    logger.info("buildBorrowerMailBody");

    StringBuilder sb = new StringBuilder();
    appendMailTitle(sb, ConstantText.getInstance().getRECEIPT(isSwedish));
    appendBorrowEmail(sb);

    return sb.toString();
  }

  public String buildOutOfOfficeMailBody() {
    StringBuilder sb = new StringBuilder();
    appendMailTitle(sb, ConstantText.getInstance().getLoanRequest(isSwedish));
    appendOutOfOfficeEmail(sb);

    return sb.toString();
  }

  public String buildAdminEmailBody() {
    logger.info("buildAdminEmailBody");

    StringBuilder sb = new StringBuilder();
    if (type.equals("Information")) {
      appendMailTitle(sb, ConstantText.getInstance().getInformationRequest());
    } else {
      appendMailTitle(sb, ConstantText.getInstance().getLoanRequest(false));
    }
    sb.append(BR_TAG);
    sb.append(START_DIV_TAG);
    sb.append(ConstantText.getInstance().getCuratorMailTitle());
//        sb.append(" ");
//        sb.append(ConstantText.getInstance().getDEPARTMENT(department));
    sb.append(", ");
    sb.append(BR_TAG);
    sb.append(BR_TAG);

    if (type.equals("Information")) {
      sb.append(ConstantText.getInstance().getCuratorMailTextInformation());
    } else {
      sb.append(ConstantText.getInstance().getCuratorMailText());
    }

    if (subCollection != null) {
      sb.append(BOLD);
      sb.append(subCollection);
      sb.append(BOLD_END);
    } else {
      sb.append(ConstantText.getInstance().getCuratorMailTextNonScientific());
    }

    sb.append(ConstantText.getInstance().getCURATOR_MAIL_TEXT_1());
    sb.append(END_DIV_TAG);

    sb.append(BR_TAG);
    sb.append(BR_TAG);

    if (hasPrimary) {
      sb.append(ConstantText.getInstance().getCuratorMailPrimaryText());
      sb.append(BR_TAG);
      sb.append(BR_TAG);
    }

    sb.append("<h3>");
    if (hasPrimary) {
      sb.append(ConstantText.getInstance().getPrimaryBorrower());
    } else {
      sb.append(ConstantText.getInstance().getBorrower());
    }
    sb.append("</h3>");

    sb.append(primarytitle);
    sb.append(" ");
    sb.append(primaryfirstName);
    sb.append(" ");
    sb.append(primarylastName);
    sb.append(BR_TAG);

    if (primarydepartment != null) {
      sb.append(primarydepartment);
      sb.append(BR_TAG);
    }
    sb.append(primaryinstitution);
    sb.append(BR_TAG);

    sb.append(primarycountry);
    sb.append(BR_TAG);
    sb.append(BR_TAG);

    if (hasPrimary) {
      sb.append("<h3>");
      sb.append(ConstantText.getInstance().getSecondaryBorrower());
      sb.append("</h3>");

      sb.append(secondarytitle);
      sb.append(" ");
      sb.append(secondaryfirstName);
      sb.append(" ");
      sb.append(secondarylastName);
      sb.append(BR_TAG);
      if (secondarydepartment != null) {
        sb.append(secondarydepartment);
        sb.append(BR_TAG);
      }
      sb.append(secondaryinstitution);
      sb.append(BR_TAG);
      sb.append(secondarycountry);
      sb.append(BR_TAG);
      sb.append(BR_TAG);
    }

    sb.append("<h3>");
    if (type.equals("Information")) {
      sb.append(ConstantText.getInstance().getRequestInformation());
    } else {
      sb.append(ConstantText.getInstance().getLoanInformation());
    }
    sb.append("</h3>");
    sb.append("Collection: ");
    sb.append(collection);
    sb.append(BR_TAG);
    sb.append("Loan purpose: ");
    sb.append(purpose);
    sb.append(BR_TAG);

    if (type != null) {
      sb.append("Loan type: ");
      sb.append(type);
      sb.append(BR_TAG);
    }

    if (subCollection != null) {
      sb.append("Relevant subcollection: ");
      sb.append(subCollection);
    }

    sb.append(BR_TAG);
    sb.append(BR_TAG);
    sb.append(BR_TAG);

    sb.append(START_DIV_TAG);
    if (type.equals("Information")) {
      sb.append(ConstantText.getInstance().getInformationRequestAttachment());
    } else {
      sb.append(ConstantText.getInstance().getLoanRequestAttachment(isSwedish));
    }
    sb.append(END_DIV_TAG);

    sb.append(BR_TAG);
    sb.append(BR_TAG);

    sb.append(ConstantText.getInstance().getRequestSummary1());
    sb.append(START_LINK_TAG);
    if (host.contains("localhost")) {
      sb.append("http://localhost/loan-admin\"");
    } else if (host.contains("dina-loans")) {
      sb.append("http://dina-loans.nrm.se/loan-admin\"");
    } else {
      sb.append("https://dina-web.net/loan-admin\"");
    }
    sb.append(" target=\"_blank\">NRM Online Loan Form Administration</a>");
    sb.append(ConstantText.getInstance().getRequestSummary2());

    sb.append(BR_TAG);
    sb.append(END_DIV_TAG);
    return sb.toString();
  }

}
