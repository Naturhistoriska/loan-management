package se.nrm.dina.loan.web.pdf;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import javax.ejb.Stateless;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.loan.web.util.CommonString;
import se.nrm.dina.loan.web.util.RequestType;
import se.nrm.dina.mongodb.loan.vo.Loan;
import se.nrm.dina.mongodb.loan.vo.Sample;
import se.nrm.dina.mongodb.loan.vo.User;

/**
 *
 * @author idali
 */
@Stateless
@Slf4j
public class PDFCreator implements Serializable {

  private static final String NEW_LINE = "\n";

  private Paragraph paragraph;
  private BaseColor color = BaseColor.WHITE;

  private boolean isSwedish;

  private final Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
  private final Font SUB_TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
  private final Font SECTION_TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
  private final Font TEXT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
  private final Font LABEL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);

  private final String LOCAL_EXTERNAL_FILES = "http://localhost:8080/loan/pdf?att=";
  private final String REMOTE_EXTERNAL_FILES_LOANS = "http://dina-loans.nrm.se/loan/pdf?att=";
  private final String REMOTE_EXTERNAL_FILES_AS = "https://www.dina-web.net/loan/pdf?att=";

  private String externalPath;

  private Document document;
  private Document adminDocument;

  /**
   * Create loan request pdf files.
   *
   * @param loan
   * @param isSwedish
   * @param path
   * @param count
   * @param pathname
   * @throws FileNotFoundException
   * @throws DocumentException
   */
  public void createPDF(Loan loan, boolean isSwedish, String path, int count, String pathname) 
          throws FileNotFoundException, DocumentException {
    log.info("createPDF : {} -- {}", path, isSwedish);

    this.isSwedish = isSwedish;

    switch (pathname) {
      case "local":
        externalPath = LOCAL_EXTERNAL_FILES;
        break;
      case "dina-loans":
        externalPath = REMOTE_EXTERNAL_FILES_LOANS;
        break;
      default:
        externalPath = REMOTE_EXTERNAL_FILES_AS;
        break;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(path);
    sb.append("/loanrequest_");
    sb.append(loan.getId());

    File summaryAdminFile = new File(sb.toString() + "_admin.pdf");    
    sb.append(".pdf");
    File summaryFile = new File(sb.toString().trim());                 

    document = new Document(PageSize.LETTER);
    adminDocument = new Document(PageSize.LETTER);
    PdfWriter.getInstance(document, new FileOutputStream(summaryFile));
    PdfWriter.getInstance(adminDocument, new FileOutputStream(summaryAdminFile));
    document.open();
    adminDocument.open();

    addTitle(loan);
    addContact(loan);

    switch (count) {
      case 9:
        addLoanRequestForScientificPurpose(loan);
        addLoanSampleList(loan);
        if (!RequestType.Information.isInformation(loan.getType())) {
          if (RequestType.Physical.isPhysical(loan.getType())) {
            addDestructiveInformation(loan);
          } else {
            addPhotoInformation(loan);
          }
          addCITESInformation(loan);
        } break;
      case 7:
        addEducationRequest(loan);
        break;
      default:
        addCommercialInformation(loan);
        break;
    }

    if (!RequestType.Information.isInformation(loan.getType())) {
      addTermsOfLoanAgreement();
    }
    document.close();
    adminDocument.close();
  }

  /**
   * Terms of Loan agreement for borrower and primary borrower
   *
   * @throws DocumentException
   */
  private void addTermsOfLoanAgreement() throws DocumentException {
    paragraph = new Paragraph(CommonString.getTermsOfAgreement(isSwedish), SECTION_TITLE_FONT);

    addEmptyLine(paragraph, 1);
    paragraph.add(new Paragraph(CommonString.getLoanPolicy(isSwedish), TEXT_FONT));
    paragraph.add(new Paragraph(CommonString.getPrimaryBorrowerAgreement(isSwedish), TEXT_FONT));
    addEmptyLine(paragraph, 1);

    document.add(paragraph);
    adminDocument.add(paragraph);
  }

  private void addCommercialInformation(Loan loan) throws DocumentException {

    paragraph = new Paragraph(CommonString.getCommercial(isSwedish), SECTION_TITLE_FONT);
    addEmptyLine(paragraph, 2);
    document.add(paragraph);
    adminDocument.add(paragraph);

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(50);
    table.setHorizontalAlignment(Element.ALIGN_LEFT);

    PdfPCell cell = null;
    addCells(table, cell, 2, color, CommonString.getLoanType(isSwedish), loan.getType(), false, false);
    document.add(table);
    adminDocument.add(table);

    paragraph = new Paragraph();
    addEmptyLine(paragraph, 1);
    paragraph.add(new Chunk(CommonString.getDescription(isSwedish), SECTION_TITLE_FONT));
    addEmptyLine(paragraph, 1);

    String loanDesc = loan.getLoanDescription();
    if (loanDesc == null || loanDesc.isEmpty()) {
      loanDesc = " - ";
    }

    paragraph.add(new Chunk(loanDesc, TEXT_FONT));
    addEmptyLine(paragraph, 1);

    if (loan.getEdPurposeFile() != null && !loan.getEdPurposeFile().isEmpty()) {
      paragraph.add(new Chunk(CommonString.getAttachment(isSwedish), LABEL_FONT));
      paragraph.add(new Chunk(loan.getEdPurposeFile(), TEXT_FONT));
      addEmptyLine(paragraph, 2);
    }
    paragraph.add(new Chunk(CommonString.getLoanPeriod(isSwedish), LABEL_FONT));
    paragraph.add(new Chunk(loan.getFromDate() + " - " + loan.getToDate(), TEXT_FONT));
    addEmptyLine(paragraph, 1);
    document.add(paragraph);
    adminDocument.add(paragraph);
  }

  private void addEducationRequest(Loan loan) throws DocumentException {

    paragraph = new Paragraph(CommonString.getEducation(isSwedish), SECTION_TITLE_FONT);
    addEmptyLine(paragraph, 2);
    document.add(paragraph);
    adminDocument.add(paragraph);

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(50);
    table.setHorizontalAlignment(Element.ALIGN_LEFT);

    PdfPCell cell = null;
    addCells(table, cell, 2, color, CommonString.getPurpose(isSwedish), loan.getEduPurpose(), false, false);
    document.add(table);
    adminDocument.add(table);

    paragraph = new Paragraph();
    addEmptyLine(paragraph, 1);
    paragraph.add(new Chunk(CommonString.getDescription(isSwedish), SECTION_TITLE_FONT));
    addEmptyLine(paragraph, 1);

    String loanDescription = loan.getLoanDescription();
    if (loanDescription == null || loanDescription.isEmpty()) {
      loanDescription = " - ";
    }
    paragraph.add(new Chunk(loanDescription, TEXT_FONT));
    addEmptyLine(paragraph, 1);

    if (loan.getEdPurposeFile() != null && !loan.getEdPurposeFile().isEmpty()) {
      paragraph.add(new Chunk(CommonString.getAttachment(isSwedish), LABEL_FONT));
      paragraph.add(new Chunk(loan.getEdPurposeFile(), TEXT_FONT));
      addEmptyLine(paragraph, 2);
    }
    paragraph.add(new Chunk(CommonString.getLoanPeriod(isSwedish), LABEL_FONT));
    paragraph.add(new Chunk(loan.getFromDate() + " - " + loan.getToDate(), TEXT_FONT));
    addEmptyLine(paragraph, 2);
    document.add(paragraph);
    adminDocument.add(paragraph);

    paragraph = new Paragraph(CommonString.getExhibitionDescription(isSwedish), SECTION_TITLE_FONT);
    addEmptyLine(paragraph, 1);

    String exhPurposeDesc = loan.getExhPorpuseDesc();
    if (exhPurposeDesc == null || exhPurposeDesc.isEmpty()) {
      exhPurposeDesc = " - ";
    }
    paragraph.add(new Chunk(exhPurposeDesc, TEXT_FONT));
    addEmptyLine(paragraph, 1);
    document.add(paragraph);
    adminDocument.add(paragraph);
  }

  private void addCITESInformation(Loan loan) throws DocumentException {

    paragraph = new Paragraph(CommonString.getCITE(isSwedish), SECTION_TITLE_FONT);
    addEmptyLine(paragraph, 1);

    paragraph.add(new Chunk(CommonString.getCITENumber(isSwedish), LABEL_FONT));

    String citeNum = loan.getCitesNumber();
    if (citeNum == null || citeNum.isEmpty()) {
      citeNum = " - ";
    }
    paragraph.add(new Chunk(citeNum, TEXT_FONT));

    addEmptyLine(paragraph, 2);
    document.add(paragraph);
    adminDocument.add(paragraph);
  }

  private void addPhotoInformation(Loan loan) throws DocumentException {
    paragraph = new Paragraph(CommonString.getPhotoMethod(isSwedish), SECTION_TITLE_FONT);
    addEmptyLine(paragraph, 1);

    String photoInst = loan.getPhotoInstraction();
    if (photoInst == null || photoInst.isEmpty()) {
      photoInst = " - ";
    }
    paragraph.add(new Chunk(photoInst, TEXT_FONT));
    addEmptyLine(paragraph, 1);

    if (loan.getPhotoInstractionFile() != null && !loan.getPhotoInstractionFile().isEmpty()) {
      paragraph.add(new Chunk(CommonString.getAttachment(isSwedish), LABEL_FONT));
      paragraph.add(new Chunk(loan.getPhotoInstractionFile(), TEXT_FONT));
      addEmptyLine(paragraph, 2);
    }
    document.add(paragraph);
    adminDocument.add(paragraph);
  }

  private void addDestructiveInformation(Loan loan) throws DocumentException {
    paragraph = new Paragraph(CommonString.getDestructiveMethod(isSwedish), SECTION_TITLE_FONT);
    addEmptyLine(paragraph, 1);

    String destractive = loan.getIsDestructive();
    if (Boolean.valueOf(destractive)) {
      paragraph.add(new Chunk(CommonString.getDestructive(isSwedish), TEXT_FONT));
      addEmptyLine(paragraph, 1);
    } else {
      paragraph.add(new Chunk(CommonString.getNoDestructive(isSwedish), TEXT_FONT));
      addEmptyLine(paragraph, 1);
    }

    String destructiveMeth = loan.getDestructiveMethod();
    if (destructiveMeth == null || destructiveMeth.isEmpty()) {
      destructiveMeth = " - ";
    }
    paragraph.add(new Chunk(destructiveMeth, TEXT_FONT));
    addEmptyLine(paragraph, 1);

    if (loan.getDestructiveFile() != null && !loan.getDestructiveFile().isEmpty()) {
      paragraph.add(new Chunk(CommonString.getAttachment(isSwedish), LABEL_FONT));
      paragraph.add(new Chunk(loan.getDestructiveFile(), TEXT_FONT));
      addEmptyLine(paragraph, 2);
    }

    document.add(paragraph);
    adminDocument.add(paragraph);
  }

  private void addCells(PdfPTable table, PdfPCell cell, int index, BaseColor color, String label, String value, boolean isTop, boolean isBottom) {

    color = BaseColor.WHITE;
    if (index % 2 != 0) {
      color = new BaseColor(237, 246, 250);
    }

    cell = new PdfPCell(new Phrase(label, LABEL_FONT));
    cell.setPaddingLeft(15);
    if (isTop) {
      cell.setPaddingTop(5);
    } else if (isBottom) {
      cell.setPaddingBottom(5);
    }

    cell.setBorder(Rectangle.NO_BORDER);
    cell.setBackgroundColor(color);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase(value, TEXT_FONT));
    if (isTop) {
      cell.setPaddingTop(5);
    } else if (isBottom) {
      cell.setPaddingBottom(5);
    }
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setBackgroundColor(color);
    table.addCell(cell);
  }

  private void addTable(Sample sample, int index) throws DocumentException {

    color = BaseColor.WHITE;
    PdfPTable table = new PdfPTable(2);
    table.setHorizontalAlignment(Element.ALIGN_LEFT);
    table.setTotalWidth(new float[]{72, 144});

    PdfPCell cell = null;
    addCells(table, cell, index, color, CommonString.getCatelogNumber(isSwedish), sample.getCatalogNumber(), true, false);
    addCells(table, cell, index, color, CommonString.getFamily(isSwedish), sample.getFamily(), false, false);
    addCells(table, cell, index, color, CommonString.getGenus(isSwedish), sample.getGenus(), false, false);
    addCells(table, cell, index, color, CommonString.getSpecies(isSwedish), sample.getSpecies(), false, false);
    addCells(table, cell, index, color, CommonString.getAuthor(isSwedish), sample.getAuctor(), false, false);
    addCells(table, cell, index, color, CommonString.getTypeStatus(isSwedish), sample.getTypeStatus(), false, false);

    addCells(table, cell, index, color, CommonString.getCountry(isSwedish), sample.getCountry(), false, false);
    addCells(table, cell, index, color, CommonString.getLocality(isSwedish), sample.getLocality(), false, false);

    addCells(table, cell, index, color, CommonString.getCollectedYear(isSwedish), sample.getCollectedYear(), false, false);
    addCells(table, cell, index, color, CommonString.getCollector(isSwedish), sample.getCollector(), false, false);
    addCells(table, cell, index, color, CommonString.getPreservationType(isSwedish), sample.getType(), false, false);
    addCells(table, cell, index, color, CommonString.getLocation(isSwedish), sample.getLocation(), false, false);
    addCells(table, cell, index, color, CommonString.getCommens(isSwedish), sample.getOther(), false, true);

    adminDocument.add(table);

    table = new PdfPTable(2);
    table.setHorizontalAlignment(Element.ALIGN_LEFT);
    table.setTotalWidth(new float[]{72, 144});

    cell = null;
    addCells(table, cell, index, color, CommonString.getCatelogNumber(isSwedish), sample.getCatalogNumber(), true, false);
    addCells(table, cell, index, color, CommonString.getFamily(isSwedish), sample.getFamily(), false, false);
    addCells(table, cell, index, color, CommonString.getGenus(isSwedish), sample.getGenus(), false, false);
    addCells(table, cell, index, color, CommonString.getSpecies(isSwedish), sample.getSpecies(), false, false);
    addCells(table, cell, index, color, CommonString.getAuthor(isSwedish), sample.getAuctor(), false, false);
    addCells(table, cell, index, color, CommonString.getTypeStatus(isSwedish), sample.getTypeStatus(), false, false);

    addCells(table, cell, index, color, CommonString.getCountry(isSwedish), sample.getCountry(), false, false);
    addCells(table, cell, index, color, CommonString.getLocality(isSwedish), sample.getLocality(), false, false);

    addCells(table, cell, index, color, CommonString.getCollectedYear(isSwedish), sample.getCollectedYear(), false, false);
    addCells(table, cell, index, color, CommonString.getCollector(isSwedish), sample.getCollector(), false, false);

    addCells(table, cell, index, color, CommonString.getPreservationType(isSwedish), sample.getType(), false, false);
    addCells(table, cell, index, color, CommonString.getCommens(isSwedish), sample.getOther(), false, true);

    document.add(table);
  }

  private void addLoanSampleList(Loan loan) throws DocumentException {

    paragraph = new Paragraph(CommonString.getSampleDetails(isSwedish), SECTION_TITLE_FONT);

    addEmptyLine(paragraph, 2);
    document.add(paragraph);
    adminDocument.add(paragraph);

    if (loan.getSamples() != null && !loan.getSamples().isEmpty()) {
      int index = 0;
      for (Sample sample : loan.getSamples()) {
        index++;
        addTable(sample, index);
      }
    } else {
      paragraph = new Paragraph();
      paragraph.add(new Chunk(" - ", TEXT_FONT));
      document.add(paragraph);
      adminDocument.add(paragraph);
    }

    paragraph = new Paragraph();
    addEmptyLine(paragraph, 1);

    if (RequestType.Information.isInformation(loan.getType())) {
      paragraph.add(new Chunk(CommonString.getAdditionalInfoInfo(isSwedish), SECTION_TITLE_FONT));
    } else {
      paragraph.add(new Chunk(CommonString.getAdditionalInfo(isSwedish), SECTION_TITLE_FONT));
    }
    addEmptyLine(paragraph, 1);
    String additionalInfo = loan.getSampleSetAdditionalInfo();
    if (additionalInfo == null || additionalInfo.isEmpty()) {
      additionalInfo = " - ";
    }
    paragraph.add(new Chunk(additionalInfo, TEXT_FONT));
    addEmptyLine(paragraph, 2);
    document.add(paragraph);
    adminDocument.add(paragraph);
  }

  private void addLoanRequestForScientificPurpose(Loan loan) throws DocumentException {

    paragraph = new Paragraph(CommonString.getLoanDetail(isSwedish), SECTION_TITLE_FONT);
    addEmptyLine(paragraph, 2);
    document.add(paragraph);
    adminDocument.add(paragraph);

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(50);
    table.setHorizontalAlignment(Element.ALIGN_LEFT);

    PdfPCell cell = null;
    addCells(table, cell, 2, color, CommonString.getDepartment(isSwedish), loan.getDepartment(), false, false);
    addCells(table, cell, 2, color, CommonString.getLoanPurpose(isSwedish), loan.getPurpose(), false, false);
    addCells(table, cell, 2, color, CommonString.getLoanType(isSwedish), loan.getType(), false, false);
    addCells(table, cell, 2, color, CommonString.getRelevantCollection(isSwedish), loan.getReleventCollection(), false, false);

    document.add(table);
    adminDocument.add(table);

    if (!RequestType.Information.isInformation(loan.getType())) {
      paragraph = new Paragraph();
      addEmptyLine(paragraph, 1);
      paragraph.add(new Chunk(CommonString.getLoanDescription(isSwedish), SECTION_TITLE_FONT));           // Description of loan
      addEmptyLine(paragraph, 1);

      String loanDescription = loan.getLoanDescription();
      if (loanDescription == null || loanDescription.isEmpty()) {
        loanDescription = " - ";
      }
      paragraph.add(new Chunk(loanDescription, TEXT_FONT));
      addEmptyLine(paragraph, 2); 
      if (loan.getLoanDescriptionFile() != null && !loan.getLoanDescriptionFile().isEmpty()) { 
        Anchor anchor = new Anchor(loan.getLoanDescriptionFile());
        anchor.setReference(buildBasePath(loan.getUuid(), loan.getLoanDescriptionFile()));
        paragraph.add(anchor); 
        addEmptyLine(paragraph, 2);
      }
      document.add(paragraph);
      adminDocument.add(paragraph);
    }
  }

  private String buildBasePath(String uuid, String fileName) {
    StringBuilder sb = new StringBuilder();
    sb.append(externalPath);
    sb.append(uuid);
    sb.append("-");
    sb.append(fileName.trim());
    return sb.toString();
  }
 
  private void addContact(Loan loan) throws DocumentException {
    paragraph = new Paragraph(CommonString.getContactDetails(isSwedish), SUB_TITLE_FONT);
    addEmptyLine(paragraph, 2);
    document.add(paragraph);
    adminDocument.add(paragraph);

    User primaryUser = loan.getPrimaryUser();
    User secondaryUser = loan.getSecondaryUser();
    if (secondaryUser != null) {
      paragraph = new Paragraph(CommonString.getPrimaryContact(isSwedish), SECTION_TITLE_FONT);
    } else {
      paragraph = new Paragraph(CommonString.getBowrrow(isSwedish), SECTION_TITLE_FONT);
    }
    document.add(paragraph);
    adminDocument.add(paragraph);

    paragraph = new Paragraph(primaryUser.getName() + NEW_LINE, TEXT_FONT);
    paragraph.add(new Chunk(primaryUser.getEmail() + NEW_LINE, TEXT_FONT));
    paragraph.add(new Chunk(primaryUser.getPhone() + NEW_LINE, TEXT_FONT));
    paragraph.add(new Chunk(primaryUser.getInstitution() + NEW_LINE, TEXT_FONT));
    if (primaryUser.getDepartment() != null && !primaryUser.getDepartment().isEmpty()) {
      paragraph.add(new Chunk(primaryUser.getDepartment() + NEW_LINE, TEXT_FONT));
    }
    paragraph.add(new Chunk(primaryUser.getAddress().getCompleteAddress() + NEW_LINE, TEXT_FONT));
    addEmptyLine(paragraph, 1);
    document.add(paragraph);
    adminDocument.add(paragraph);

    if (secondaryUser != null) {
      paragraph = new Paragraph(CommonString.getSecondaryContact(isSwedish), SECTION_TITLE_FONT);
      document.add(paragraph);
      adminDocument.add(paragraph);

      paragraph = new Paragraph(secondaryUser.getName() + NEW_LINE, TEXT_FONT);
      paragraph.add(new Chunk(secondaryUser.getEmail() + NEW_LINE, TEXT_FONT));
      paragraph.add(new Chunk(secondaryUser.getPhone() + NEW_LINE, TEXT_FONT));
      paragraph.add(new Chunk(secondaryUser.getInstitution() + NEW_LINE, TEXT_FONT));
      if (secondaryUser.getDepartment() != null && !secondaryUser.getDepartment().isEmpty()) {
        paragraph.add(new Chunk(secondaryUser.getDepartment() + NEW_LINE, TEXT_FONT));
      }
      paragraph.add(new Chunk(secondaryUser.getAddress().getCompleteAddress() + NEW_LINE, TEXT_FONT));
      addEmptyLine(paragraph, 2);
      document.add(paragraph);
      adminDocument.add(paragraph);
    }
  }

  private void addTitle(Loan loan) throws DocumentException {
    paragraph = new Paragraph(CommonString.getLoanRequest(isSwedish), TITLE_FONT);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    addEmptyLine(paragraph, 1);
    paragraph.add(new Paragraph(loan.getSubmitDate(), TEXT_FONT));
    paragraph.add(new Paragraph(loan.getId(), SUB_TITLE_FONT));

    addEmptyLine(paragraph, 1);
    document.add(paragraph);
    adminDocument.add(paragraph);
  }

  private void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
}
