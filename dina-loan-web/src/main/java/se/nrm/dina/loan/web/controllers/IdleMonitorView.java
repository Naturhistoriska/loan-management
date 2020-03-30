/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.nrm.dina.loan.web.controllers;
 
import java.io.IOException;  
import java.util.UUID;  
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.nrm.dina.loan.web.filehander.FileHandler;
import se.nrm.dina.loan.web.util.CommonNames;
import se.nrm.dina.loan.web.util.NameMapping;


/**
 *
 * @author idali
 */
@ManagedBean
public class IdleMonitorView {
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
//    private static final String START_PAGE = "/start";
    
    @Inject
    private OnlineForm form;
    
    @Inject 
    private FileHandler fileHandler;
    
    @Inject
    private Languages languages;

    public void onIdle() {
        logger.info("onIdle"); 
        boolean isSwedish = languages.isIsSwedish(); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        NameMapping.getMsgByKey(CommonNames.Idle, isSwedish), 
                                        NameMapping.getMsgByKey(CommonNames.IdleMsg, isSwedish)));
 
    }

    public void onActive() { 
        
        UUID uuid = form.getUuid();
        if(uuid != null) {
            fileHandler.removeTempDirectory(uuid);
        }
        
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession(false);
            session.invalidate();
            logger.info("Redirect path : {}", FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
            ctx.getExternalContext().redirect("/loan");
        } catch (IOException ex) {
            logger.warn(ex.getMessage());
        }
            
    } 
}
