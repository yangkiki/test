package com.moxian.ng.model;

import java.io.Serializable;


public class UserProfileDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String secondaryEmail;

    private String secondaryMobileNumber;

    private String instantMessager;

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getSecondaryMobileNumber() {
        return secondaryMobileNumber;
    }

    public void setSecondaryMobileNumber(String secondaryMobileNumber) {
        this.secondaryMobileNumber = secondaryMobileNumber;
    }

    public String getInstantMessager() {
        return instantMessager;
    }

    public void setInstantMessager(String instantMessager) {
        this.instantMessager = instantMessager;
    }



}
