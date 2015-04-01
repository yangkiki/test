package com.fenglianfinance.bill.model;

import java.io.Serializable;

public class SystemUserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String department;

    private String occupation;

    private String description;

    private String authentication;

    private UserAccountDetails userAccount;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserAccountDetails getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountDetails userAccount) {
        this.userAccount = userAccount;
    }

}
