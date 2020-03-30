package se.nrm.dina.loan.admin.logic;

import java.io.Serializable; 
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException; 
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.manager.dao.AccountDao;
import se.nrm.dina.manager.entities.TblGroups;
import se.nrm.dina.manager.entities.TblUsers;

/**
 *
 * @author idali
 */
@Slf4j
public class UserAccountLogic implements Serializable {

  @EJB
  private AccountDao dao;
  @Inject
  private MailHandler mail; 
  @Inject
  private PasswordEncoder encode;
  
  private final List<String> excludeGroups;
  private final String serverName;

  public UserAccountLogic() {
    excludeGroups = new ArrayList<>();
    excludeGroups.add("inventory");
    excludeGroups.add("superuser");
    excludeGroups.add("vegadare");
    excludeGroups.add("scientist");

    serverName = ((HttpServletRequest) FacesContext.getCurrentInstance()
            .getExternalContext().getRequest()).getServerName();
    log.info("servername : {}", serverName);
  }

 
          
  public List<TblGroups> findLoanAccount() {
    return dao.findGroupByNamedQuery("TblGroups.findNonInventoryGroups", excludeGroups);
  }

  public boolean changePassword(String newPassword, String oldPassword, TblUsers user)
          throws NoSuchAlgorithmException, UnsupportedEncodingException {
    String encodedPasswordDigest = encode.hashAndEncodePassword(oldPassword);
    if (!encodedPasswordDigest.equals(user.getPassword())) {
      return false;
    }
    user.setPassword(encode.hashAndEncodePassword(newPassword));
    dao.mergeAccount(user);
    return true;
  }

  public boolean isValidUserNmae(String userName) {
    return dao.validateUserName(userName);
  }

  public boolean isValidEmail(String email) {
    return dao.validateEmail(email);
  }

  public void createUser(String username, String password, String groupName, String email) 
          throws NoSuchAlgorithmException, UnsupportedEncodingException { 
    log.info("createUser : {}", dao);
    
    log.info("cureate user : {} -- {}", username, password);
    TblUsers user = new TblUsers();
    if (isValidUserNmae(username) && isValidEmail(email)) {
      user.setUsername(username);
      user.setEmail(email);

      String encodedPasswordDigest = encode.hashAndEncodePassword(password);
      user.setPassword(encodedPasswordDigest);

      TblGroups group = new TblGroups();
      group.setGroupname(groupName);
      group.setUsername(user);

      List<TblGroups> groups = new ArrayList<>();
      groups.add(group);
      user.setTblGroupsList(groups);

      dao.createAccount(user);
      mail.sendMail(email, password, serverName);
    }
  } 
  
  public void deleteUser(TblGroups group) { 
    dao.delete(group.getUsername()); 
  }
  
  public void updateUser(TblUsers user) {
    dao.mergeAccount(user);
  }
  
  public void editUser(TblUsers user, TblGroups group) {
    List<TblGroups> groups = new ArrayList<>();
    groups.add(group);

    user.setTblGroupsList(groups);
    group.setUsername(user);
    dao.mergeAccount(user);
  }
}
