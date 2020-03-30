package se.nrm.dina.loan.admin.controllers;

import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author idali
 */
@Named(value = "sessionBean")
public class SessionBean implements Serializable {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private static final String LOGOUT_PATH = "/start?faces-redirect=true";
  private static final String HOME_PATH = "/secure/home?faces-redirect=true";

  public SessionBean() {
  }

  public String login() {
    logger.info("login");
    return HOME_PATH;
  }

  /**
   * Logs the current user out by invalidating the session
   *
   * @return LOGOUT_PATH
   */
  public String logout() {

    logger.info("logout");
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    HttpSession session = (HttpSession) externalContext.getSession(false);
    session.invalidate();

    return LOGOUT_PATH;
  }
}
