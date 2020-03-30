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
public enum Department {

    Botany,
    EnvionmentSpecimenBank,
    Geology,
    Palaeobiology, 
    Zoology;

    public String getText() {
        return this.name().toLowerCase();
    }
    
    public boolean isZoology(String department) {
        return this.name().equalsIgnoreCase(department);
    }
    
    public static String getDepartName(String name) { 
        switch (name) {
            case "botany" :   
                return Botany.name();  
            case "envionmentspecimenbank" : 
                return EnvionmentSpecimenBank.name();  
            case "geology":   
                return Geology.name(); 
            case "palaeobiology":   
                return Palaeobiology.name(); 
                default: return Zoology.name();
        }
    }
}
