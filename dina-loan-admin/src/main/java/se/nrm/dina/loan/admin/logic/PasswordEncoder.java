package se.nrm.dina.loan.admin.logic;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

/**
 *
 * @author idali
 */
@Slf4j
public class PasswordEncoder implements Serializable {
   
  public PasswordEncoder() {
    
  }

  public String hashAndEncodePassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    log.info("hashAndEncodePassword : {}", password);
//    md = MessageDigest.getInstance("SHA-256");
//    md.update(password.getBytes("UTF-8"));
//    byte[] passwordDigest = md.digest();
//    return (new BASE64Encoder()).encode(passwordDigest);
    
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(password.getBytes("UTF-8"));
    byte[] passwordDigest = md.digest();
    log.info("hashAndEncodePassword : {}", passwordDigest);
    return (new BASE64Encoder()).encode(passwordDigest);
  }
}



     
    
    