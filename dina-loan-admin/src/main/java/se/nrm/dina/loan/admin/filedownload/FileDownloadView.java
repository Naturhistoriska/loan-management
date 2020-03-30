package se.nrm.dina.loan.admin.filedownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.activation.MimetypesFileTypeMap;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent; 
import se.nrm.dina.mongodb.loan.vo.Loan;

/**
 *
 * @author idali
 */
@Named(value = "download")
@SessionScoped
@Slf4j
public class FileDownloadView implements Serializable {
 
  private final String LOCAL_FILES = "/Users/idali/Documents/dina_project/onlineloans/loan_files/";
  private final String REMOTE_FILES_LOAN = "/home/admin/wildfly-8.0.0-2/loans/";
  private final String REMOTE_FILES_AS = "/home/admin/wildfly-8.1.0-0/loans/";

  private StreamedContent file;
  private final String FILE_PATH;

  public FileDownloadView() {

    String host = "local";
    InetAddress inetAddress = null;
    try {
      inetAddress = InetAddress.getLocalHost();
    } catch (UnknownHostException une) {
      log.warn(une.getMessage());
    }
    if (inetAddress != null) {
      host = inetAddress.getHostName();
    }

    if (host.toLowerCase().contains("local")) {
      FILE_PATH = LOCAL_FILES;
    } else if (host.contains("dina-loans")) {
      FILE_PATH = REMOTE_FILES_LOAN;
    } else {
      FILE_PATH = REMOTE_FILES_AS;
    }
  }

  public void downloadDestructivefile(Loan loan) {
    log.info("downloadDestructivefile : {}", loan);

    String filePath = buildFilePath(loan.getUuid(), loan.getDestructiveFile());
    download(filePath, loan.getDestructiveFile());
  }

  public void downloadDescriptionfile(Loan loan) {
    log.info("downloadDescriptionfile : {}", loan);

    String filePath = buildFilePath(loan.getUuid(), loan.getLoanDescriptionFile());
    download(filePath, loan.getLoanDescriptionFile());
  }

  public void downloadPhotoInstractionFile(Loan loan) {
    log.info("photoInstractionFile : {}", loan);

    String filePath = buildFilePath(loan.getUuid(), loan.getPhotoInstractionFile());
    download(filePath, loan.getPhotoInstractionFile());
  }

  public void downloadEdPurposeFile(Loan loan) {
    log.info("downloadEdPurposeFile : {}", loan);

    String filePath = buildFilePath(loan.getUuid(), loan.getEdPurposeFile());
    download(filePath, loan.getEdPurposeFile());
  }

  private void download(String filePath, String filename) {
    String mimetype = getFileType(filePath);
    log.info("mimetype : {}", mimetype);

    InputStream stream;
    try {
      stream = new FileInputStream(filePath);
      file = new DefaultStreamedContent(stream, mimetype, filename);
    } catch (FileNotFoundException ex) {
      log.warn(ex.getMessage());
    }
  }

  private String getFileType(String filePath) {
    MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

    File f = new File(filePath);
    if (f.exists()) {
      return mimeTypesMap.getContentType(f);
    }
    return null;
  }

  private String buildFilePath(String uuid, String fileName) {
    StringBuilder sb = new StringBuilder();
    sb.append(FILE_PATH);
    sb.append(uuid.replace("-", "/"));
    sb.append("/");
    sb.append(fileName);
    return sb.toString();
  }

  public StreamedContent getFile() {
    return file;
  }

  public void setFile(StreamedContent file) {
    this.file = file;
  }
}
