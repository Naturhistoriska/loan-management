package se.nrm.dina.loan.web.util;

/**
 *
 * @author idali
 *
 * helper class to check the operating system this Java VM runs in
 *
 */
public final class OsChecker {

  /**
   * types of Operating Systems
   */
  public enum OSType {
    Windows, MacOS, Linux, Other
  };

  // cached result of OS detection
  protected static OSType detectedOS;

  /**
   * detect the operating system from the os.name System property and cache the
   * result
   *
   * @return - the operating system detected
   */
  public static OSType getOperatingSystemType() {
    if (detectedOS == null) {
      String OS = System.getProperty("os.name", "generic").toLowerCase();
      if ((OS.contains("mac")) || (OS.contains("darwin"))) {
        detectedOS = OSType.MacOS;
      } else if (OS.contains("win")) {
        detectedOS = OSType.Windows;
      } else if (OS.contains("nux")) {
        detectedOS = OSType.Linux;
      } else {
        detectedOS = OSType.Other;
      }
    }
    return detectedOS;
  }
}
