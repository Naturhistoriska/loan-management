package se.nrm.dina.loan.web.filehander;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author idali
 */
@Stateless
@Slf4j
public class FileHandler implements Serializable {

  private static final String FILE_LOCAL = "/Users/idali/Documents/dina_project/onlineloans/loan_files/";
  private static final String FILE_REMOTE_LOAN = "/home/admin/wildfly-8.0.0-2/loans/";
  private static final String FILE_REMOTE_AS = "/home/admin/wildfly-8.1.0-0/loans/";
  private static final String forDockerWildfly = "/opt/jboss/wildfly/loan/";
  
  
  private String host;
  private File file;
  private final String basePath;

  private File tempDirectory;

  public FileHandler() {
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
      basePath = FILE_LOCAL;
    } else if (host.contains("dina-loans")) {
      basePath = FILE_REMOTE_LOAN;
    } else {
      basePath = forDockerWildfly;
    }
  }

  private void buildTempDirectory(UUID uuid) {
    tempDirectory = new File(basePath + uuid.toString());
    if (!tempDirectory.exists()) {
      tempDirectory.mkdir();
    }
  }

  public void saveTempFile(UploadedFile uploadFile, UUID uuid) throws IOException {
    log.info("saveTempFile : {} -- {}", uploadFile.getFileName(), uuid);

    buildTempDirectory(uuid);
    try (InputStream initialStream = uploadFile.getInputstream()) {
      byte[] buffer = new byte[initialStream.available()];
      initialStream.read(buffer);

      File targetFile = new File(tempDirectory, uploadFile.getFileName());
      OutputStream outStream = new FileOutputStream(targetFile);
      outStream.write(buffer);
    }
  }

  public void removeFileFromTempDirectory(String fileName, UUID uuid) {
    log.info("removeFileFromTempDirectory : {}", fileName);

    buildTempDirectory(uuid);
    File thisFile = new File(tempDirectory, fileName);
    if (thisFile.exists()) {
      thisFile.delete();
    }
  }

  public String transferFiles(UUID uuid) { 
    log.info("transferFiles");

    makeLoanDirectory(uuid);
    buildTempDirectory(uuid); 
    File orgFile;
    File targetFile;
    for (File tempFile : tempDirectory.listFiles()) {
      targetFile = new File(file, tempFile.getName());
      orgFile = tempFile;
      orgFile.renameTo(targetFile);
    }

    removeTempDirectory(uuid);
    return file.getPath();
  }

  public void removeTempDirectory(UUID uuid) {
    log.info("removeTempDirectory {}", tempDirectory);

    if (tempDirectory == null || !tempDirectory.exists()) {
      buildTempDirectory(uuid);
    }
    try {
      if(tempDirectory.exists()) {
        FileUtils.cleanDirectory(tempDirectory); 
        tempDirectory.delete();
      } 
    } catch (IOException ex) {
      log.warn(ex.getMessage());
    }
  }

  private void makeLoanDirectory(UUID uuid) {

    StringBuilder sb = new StringBuilder(basePath);
    file = new File(basePath);
    List<String> subDirs = new ArrayList<>(Arrays.asList(uuid.toString().split("-")));

    subDirs.stream().forEach((subDir) -> {
      makeSubDir(subDir, sb);
    }); 
  }

  private void makeSubDir(String subDir, StringBuilder sb) {
    sb.append(subDir);
    sb.append("/");
    file = new File(sb.toString());
    if (!file.exists()) {
      file.mkdir();
    }
  }
}
