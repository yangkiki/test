/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.gateway.api;

/**
 *
 * @author hansy
 */
public class ResourceNotFoundException extends RuntimeException {

    Long resoruceId;

    public ResourceNotFoundException(Long id) {
        super();
        this.resoruceId = id;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public Long getResoruceId() {
        return resoruceId;
    }

    public void setResoruceId(Long resoruceId) {
        this.resoruceId = resoruceId;
    }
}
