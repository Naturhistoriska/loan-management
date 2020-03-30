/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.nrm.dina.loan.web.util;



/**
 *
 * @author idali
 */
public enum BreadCrumbElement {
    
    Home,
    OnlineForm,
    Department,
    Enhet,
    Botany,
    ESB,
    Geology,
    Palaeobiology, 
    Zoology,  
    Page1,
    Page1b,
    Page2,
    Page3,
    Page4,
    Page5,
    Page6,
    Page6b,
    Page7,
    Page8,
    Page9;
      
    public String getText() {
        return this.name().toLowerCase();
    }  
    
    public String getMainMenuTextByLocale(boolean isSwedish) {
      return isSwedish ? NameMapping.getMainMenuSvByKey(this) : NameMapping.getMainMenuEnByKey(this);
    }
    
    public String getEduMenuTextByLocale(boolean isSwedish) {
      return isSwedish ? NameMapping.getEduMenuSvByKey(this) : NameMapping.getEduMenuEnByKey(this);
    }
    
    public String getArtMenuTextByLocale(boolean isSwedish) {
      return isSwedish ? NameMapping.getArtMenuSvByKey(this) : NameMapping.getArtMenuEnByKey(this);
    }
}
