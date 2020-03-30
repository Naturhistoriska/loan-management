package se.nrm.dina.loan.admin.controllers;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.RowEditEvent;
import se.nrm.dina.loan.admin.logic.UserAccountLogic;
import se.nrm.dina.manager.entities.TblGroups;
import se.nrm.dina.manager.entities.TblUsers;

/**
 *
 * @author idali
 */
@Named(value = "user")
@SessionScoped
@Slf4j
public class UserAccountController implements Serializable {

  private String username;
  private String password;
  private String email;
  private boolean isOnVacation;
  private String groupname = "user";

  private List<TblGroups> filteredAccounts;
  private List<TblGroups> accounts;

  private TblUsers loggedinUser;
  private String oldPassword = null;
  private String newPassword = null;
  @Inject
  private Messages message;

  @Inject
  private Navigator navigate;

  @Inject
  private UserAccountLogic logic;

  private final String duplicateUsername = "Duplicate username";
  private final String duplicateEmail = "Duplicate email";
  private final String msgUsername = "Username: ";
  private final String msgEmail = "Email: ";
  private final String msgExist = " is already exist in database.";
  private final String loginuser = "loginuser";
  private final String passwordChanged = "Password changed";
  private final String incorrectPassword = "Incorrect old password";
  private final String userGroupName = "user";
  private final String createUserFailed = "Create user failed";

  private HttpSession session;

  public UserAccountController() {
  }

  @PostConstruct
  public void init() {
    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    loggedinUser = (TblUsers) session.getAttribute(loginuser);
    accounts = accounts == null || accounts.isEmpty() ? logic.findLoanAccount() : accounts;
  }

//  public void changePassword() {
//    log.info("changePassword ");
//    navigate.gotoPasswordRecoveryPage();
//  }
  
  public void changeGroup() {
    log.info("changeGroup : {}", groupname);
  }

  public void updatePassword() {
    log.info("updatePassword");
    try {
      logic.changePassword(newPassword, oldPassword, loggedinUser);
      message.addInfo(passwordChanged, passwordChanged);
    } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
      log.error(ex.getMessage());
      message.addError(createUserFailed, ex.getMessage());
    }
  }

  public void addUserAccount() {
    log.info("addUserAccount : {} -- {}", username, password);

    if (!logic.isValidUserNmae(username)) {
      log.info("user name not valid");
      message.addError(duplicateUsername, msgUsername + username + msgExist);
    } else if (!logic.isValidEmail(email)) {
      log.info("email not valid");
      message.addError(duplicateEmail, msgEmail + email + msgExist);
    } else {
      try {
        logic.createUser(username, password, groupname, email);

        username = null;
        password = null;
        email = null;
        groupname = userGroupName;
        accounts = logic.findLoanAccount();
      } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
        log.error(ex.getMessage());
        message.addError(createUserFailed, ex.getMessage());
      }
    }
  }

  public void removeAccount(TblGroups group) {
    log.info("removeAccount: {}", group);
    logic.deleteUser(group);
  }

  public void updateUser(String user) {
    accounts = logic.findLoanAccount();
  }

  public void updateUserVacation() {
    log.info("checkonvacation : {} -- {}", loggedinUser, loggedinUser.getOnvacation());

    logic.updateUser(loggedinUser);
    accounts = logic.findLoanAccount();
  }

  public void onRowEdit(RowEditEvent event) {
    log.info("onRowEdit: {}", (TblGroups) event.getObject());

    TblGroups selectedGroup = (TblGroups) event.getObject();
    TblUsers user = selectedGroup.getUsername();

    if (user.getUsername().equals(loggedinUser.getUsername())) {
      loggedinUser.setOnvacation(user.getOnvacation());
    }
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGroupname() {
    return groupname;
  }

  public void setGroupname(String groupname) {
    this.groupname = groupname;
  }

  public List<TblGroups> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<TblGroups> accounts) {
    this.accounts = accounts;
  }

  public List<TblGroups> getFilteredAccounts() {
    return filteredAccounts;
  }

  public void setFilteredAccounts(List<TblGroups> filteredAccounts) {
    this.filteredAccounts = filteredAccounts;
  }

  public boolean isIsOnVacation() {
    return isOnVacation;
  }

  public void setIsOnVacation(boolean isOnVacation) {
    this.isOnVacation = isOnVacation;
  }

  public TblUsers getLoggedinUser() {
    return loggedinUser;
  }

  public void setLoggedinUser(TblUsers loggedinUser) {
    this.loggedinUser = loggedinUser;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

//  public boolean isIsChangePasswordSuccessed() {
//    return isChangePasswordSuccessed;
//  }
//  public void setIsChangePasswordSuccessed(boolean isChangePasswordSuccessed) {
//    this.isChangePasswordSuccessed = isChangePasswordSuccessed;
//  }
}
