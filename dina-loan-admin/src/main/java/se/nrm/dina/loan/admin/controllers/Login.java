package se.nrm.dina.loan.admin.controllers;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.nrm.dina.manager.dao.AccountDao;
import se.nrm.dina.manager.entities.TblUsers;

/**
 *
 * @author idali
 */
@ViewScoped
@ManagedBean
@SessionScoped
public class Login implements Serializable {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private static final String HOME_PATH = "/secure/home?faces-redirect=true";
  private static final String LOGOUT_PATH = "/secure/start?faces-redirect=true";

  private final HttpSession session;

  private String username;
  private String password;

  @EJB
  private AccountDao dao;

  public Login() {
    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
  }

  public String login() {

    logger.info("login");

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

    try {
      request.login(username, password);

      if (request.isUserInRole("inventory") || request.isUserInRole("scientist")) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication failed!", null));
        try {
          request.logout();
        } catch (ServletException e) {
          logger.warn("Failed to logout user!", e);
        }
        return "";
      }

      TblUsers user = dao.findByUserName(username);
      session.setAttribute("loginuser", user);
    } catch (ServletException e) {
      logger.warn(e.getMessage());
      if (e.getMessage().equals("Attempt to re-login while the user identity already exists")) {
        return "";
      }
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect Username or Password!", null));
      return "";
    } 
    return HOME_PATH;
  }

  public String logout() {
    logger.info("logout");
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

    try {
      request.logout();
    } catch (ServletException e) {
      logger.warn("Failed to logout user!", e);
    }
    session.invalidate();
    return LOGOUT_PATH;
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
}
