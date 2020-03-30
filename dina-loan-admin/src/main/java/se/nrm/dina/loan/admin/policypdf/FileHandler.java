package se.nrm.dina.loan.admin.policypdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.ejb.Stateless;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author idali
 */
@Stateless
@Slf4j
public class FileHandler implements Serializable {

  private final String LOCAL_POLICY_FILES = "/Users/idali/Documents/dina_project/onlineloans/policydocuments/"; 
  private final String REMOTE_POLICY_FILES_AS = "/home/admin/wildfly-8.1.0-0/policydocuments/";
  private static final String forDockerWildfly = "/opt/jboss/wildfly/loan_policy/";
  
  private final String FILE_PATH;

  private File file; 
  public FileHandler() {
    String host = "localhost";
    InetAddress inetAddress = null;
    try {
      inetAddress = InetAddress.getLocalHost();
    } catch (UnknownHostException une) {
      log.warn(une.getMessage());
    }
    if (inetAddress != null) {
      host = inetAddress.getHostName();
    }

    log.info("host : {}", host); 
    if (host.toLowerCase().contains("local")) {
      FILE_PATH = LOCAL_POLICY_FILES; 
    } else if (host.contains("dina-loans")) {
      FILE_PATH = REMOTE_POLICY_FILES_AS;
    } else {
      FILE_PATH = forDockerWildfly;
    }
    
//    if (host.contains("local")) {
//      FILE_PATH = LOCAL_POLICY_FILES; 
//    } else {
//      FILE_PATH = REMOTE_POLICY_FILES_AS;
//    }

    if (file == null) {
      file = new File(FILE_PATH);
    }
  }

  public void saveFile(UploadedFile uploadFile, boolean isScPolicy) {
    log.info("saveFile : {}", uploadFile.getFileName());

    removeOldFile(isScPolicy);
    InputStream initialStream = null;
    try {
      initialStream = uploadFile.getInputstream();

      byte[] buffer = new byte[initialStream.available()];
      initialStream.read(buffer);

      if (!file.exists()) {
        file = new File(FILE_PATH);
      }
      File targetFile = new File(file, getPolicyFileName(isScPolicy));
      OutputStream outStream = new FileOutputStream(targetFile);
      outStream.write(buffer);
    } catch (IOException ex) {
      log.info(ex.getMessage());
    } finally {
      try {
        if (initialStream != null) {
          initialStream.close();
        }
      } catch (IOException ex) {
        log.warn(ex.getMessage());
      }
    }
  }

  private String getPolicyFileName(boolean isScPlicy) {
    return isScPlicy ? "Loanpolicyforscientificpurposes.pdf" : "Loanpolicycommon.pdf";
  }

  private void removeOldFile(boolean isScPolicy) {
    log.info("removeOldFile");

    File thisFile = new File(file, getPolicyFileName(isScPolicy));
    if (thisFile.exists()) {
      thisFile.delete();
    }
  }

  public String getFilePath() {
    return file.getPath();
  }
}
