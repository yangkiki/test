package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
//import java.util.ArrayList;
import java.util.List;

public class UserAccountDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

//    private String password;
    private String name;

    private String gender;

    private String mobileNumber;

    private String email;

    private boolean active = true;

    private boolean locked = false;

    private LocalDateTime createdDate;

    private String idCardVerificationCardNumber;

    private LocalDateTime idCardVerificationVerifiedDate;

    private LocalDateTime mobileNumberVerificationVerifiedDate;

    private List<String> roles;

    private BigDecimal totalOrderAmount;
    private BigDecimal totalPaymentAmount;

    private AccountingInfoDetails accountingInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountingInfoDetails getAccountingInfo() {
        return accountingInfo;
    }

    public void setAccountingInfo(AccountingInfoDetails accountingInfo) {
        this.accountingInfo = accountingInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public LocalDateTime getIdCardVerificationVerifiedDate() {
        return idCardVerificationVerifiedDate;
    }

    public void setIdCardVerificationVerifiedDate(LocalDateTime idCardVerificationVerifiedDate) {
        this.idCardVerificationVerifiedDate = idCardVerificationVerifiedDate;
    }

    public LocalDateTime getMobileNumberVerificationVerifiedDate() {
        return mobileNumberVerificationVerifiedDate;
    }

    public void setMobileNumberVerificationVerifiedDate(LocalDateTime mobileNumberVerificationVerifiedDate) {
        this.mobileNumberVerificationVerifiedDate = mobileNumberVerificationVerifiedDate;
    }

    public String getIdCardVerificationCardNumber() {
        return idCardVerificationCardNumber;
    }

    public void setIdCardVerificationCardNumber(String idCardVerificationCardNumber) {
        this.idCardVerificationCardNumber = idCardVerificationCardNumber;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public BigDecimal getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    @Override
    public String toString() {
        return "UserAccountDetails [id=" + id + ", username=" + username + ", name=" + name
                + ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", email=" + email + ", active=" + active
                + ", createdDate=" + createdDate + ", roles=" + roles + "]";
    }

}
