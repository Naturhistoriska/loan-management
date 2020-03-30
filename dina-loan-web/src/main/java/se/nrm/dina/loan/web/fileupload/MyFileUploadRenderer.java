package se.nrm.dina.loan.web.fileupload;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author idali
 */
public class MyFileUploadRenderer extends FileUploadRenderer {

    @Override
    public void decode(FacesContext context, UIComponent component) {
        if (context.getExternalContext().getRequestContentType().toLowerCase().startsWith("multipart/")) {
            super.decode(context, component);
        }
    } 
}
