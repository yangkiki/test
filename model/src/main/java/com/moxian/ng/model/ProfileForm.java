package com.moxian.ng.model;

import java.io.Serializable;

public class ProfileForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String mobileNumber;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ProfileForm{" + "name=" + name + ", mobileNumber=" + mobileNumber + ", email=" + email + '}';
    }


}
