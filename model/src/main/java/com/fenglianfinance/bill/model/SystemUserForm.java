package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.util.List;

public class SystemUserForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String department;

    private String occupation;

    private String description;

    private String authentication;

    private UserForm userAccount;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public UserForm getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserForm userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "SystemUserForm [department=" + department + ", occupation=" + occupation + ", description="
                + description + ", authentication=" + authentication + ", userAccount=" + userAccount + "]";
    }

}
