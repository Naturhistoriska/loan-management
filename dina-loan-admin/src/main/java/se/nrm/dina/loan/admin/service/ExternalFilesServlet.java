package se.nrm.dina.loan.admin.service;

import java.io.File;
import java.io.IOException;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author idali
 */
@WebServlet("/pdf")
@Slf4j
public class ExternalFilesServlet extends HttpServlet  {
  private final String LOCAL_POLICY_FILE = "/Users/idali/Documents/dina_project/onlineloans/policydocuments/";
  private final String REMOTE_POLICY_FILE_LOAN = "/home/admin/wildfly-8.0.0-2/policydocuments/";
  private final String REMOTE_POLICY_FILE_AS = "/home/admin/wildfly-8.1.0-0/policydocuments/";

  private final String LOCAL_LOAN_PDF = "/Users/idali/Documents/onlineloans/loan_files/";
  private final String REMOTE_LOAN_PDF_LOAN = "/home/admin/wildfly-8.0.0-2/loans/";
  private final String REMOTE_LOAN_PDF_AS = "/home/admin/wildfly-8.1.0-0/loans/";
   
  private String baseDirectory;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    log.info("ExternalFilesServlet.doGet : {} -- {}", request.getParameter("pdf"), request.getParameter("id"));

    String host = request.getServerName();
    File file;
    String mimetype = "application/pdf";
    String fileName = "";
    if (request.getParameter("pdf") != null && !request.getParameter("pdf").isEmpty()) {
      fileName = request.getParameter("pdf");
      if (host.toLowerCase().contains("dina-loans")) {
        baseDirectory = REMOTE_POLICY_FILE_LOAN;
      } else if (host.toLowerCase().contains("local")) {
        baseDirectory = LOCAL_POLICY_FILE;
      } else {
        baseDirectory = REMOTE_POLICY_FILE_AS;
      }
    } else {
      if (host.contains("dina-loans")) {
        baseDirectory = REMOTE_LOAN_PDF_LOAN;
      } else if (host.contains("local")) {
        baseDirectory = LOCAL_LOAN_PDF;
      } else {
        baseDirectory = REMOTE_LOAN_PDF_AS;
      }
      String loanFile;
      if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
        loanFile = request.getParameter("id");
      } else {
        loanFile = request.getParameter("att");
      }
      if (loanFile != null) {
        fileName = loanFile.replace("-", "/");
      }
    }
    file = new File(buildFilePath(fileName));

    if (request.getParameter("att") != null && !request.getParameter("att").isEmpty()) {
      mimetype = getFileType(file.getPath());
    }

    byte[] content = FileUtils.readFileToByteArray(file);

    response.setContentType(mimetype);
    response.setContentLength(content.length);
    response.getOutputStream().write(content); 
  }

  private String getFileType(String filePath) {
    MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

    File file = new File(filePath);
    if (file.exists()) {
      return mimeTypesMap.getContentType(file);
    }
    return null;
  }

  private String buildFilePath(String fileName) {
    return baseDirectory + fileName;
  }
}
