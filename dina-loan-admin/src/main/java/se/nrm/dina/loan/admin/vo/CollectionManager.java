/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.nrm.dina.loan.admin.vo;

/**
 *
 * @author idali
 */
public class CollectionManager {
    
    private String group;
    private String newGroupName;
    private String manager;
    
    
    
    public CollectionManager(String group, String newGroupName, String manager) {
        this.group = group;
        this.newGroupName = newGroupName;
        this.manager = manager;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getNewGroupName() {
        return newGroupName;
    }

    public void setNewGroupName(String newGroupName) {
        this.newGroupName = newGroupName;
    }
 

    
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    } 
}
