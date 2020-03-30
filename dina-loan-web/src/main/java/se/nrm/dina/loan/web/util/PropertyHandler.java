package se.nrm.dina.loan.web.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author idali
 */
public class PropertyHandler {

  private static final Properties properties = new Properties();
  private static PropertyHandler instance = null;

  private final String LOCAL_PROPERTIES_FILE = "/Users/idali/Documents/loan_emaillist/";
  private final String REMOTE_PROPERTIES_FILE = "/home/admin/wildfly-8.0.0.2/loan_emaillist/";

  private final boolean isLocal = true;

  public static synchronized PropertyHandler getInstance() throws IOException {
    if (instance == null) {
      instance = new PropertyHandler();
    }
    return instance;
  }

  public PropertyHandler() throws IOException {

    InputStream inputStream = new FileInputStream(getPropertyFilePath());
    try {
      properties.load(inputStream);
    } catch (IOException ex) {
      throw new IOException(ex.getMessage());
    } finally {
      try {
        inputStream.close();
      } catch (IOException ex) {
        throw new IOException(ex.getMessage());
      }
    }
  }

  /**
   * Get the value of the property with key.
   *
   * @param key may not be null
   * @return the value of the property
   */
  public String getPropertyeee(String key) {
    if (key == null) {
      return null;
    }
    String value = properties.getProperty(key);
    if (value == null) {
      return null;
    }
    return value;
  }

  private String getPropertyFilePath() {
    return isLocal ? LOCAL_PROPERTIES_FILE : REMOTE_PROPERTIES_FILE;
  }
}
