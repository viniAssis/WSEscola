/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escola.model;

/**
 *
 * @author vini
 */
public class Status {
    
    
    public Status(){
        
    }
    
    public Status(String status){
        this.status = status;
    }
    
    
    private String status;

    /**
     * @return the erro
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param erro the erro to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
