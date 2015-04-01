package com.fenglianfinance.bill.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

@Entity
@Table(name = "user_profiles")
@Access(AccessType.FIELD)
public class UserProfile extends AuditableEntity<UserProfile, Long> {

    private static final long serialVersionUID = 1L;

    private String secondaryEmail;

    private String secondaryMobileNumber;

    private String instantMessager;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAccount account;

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

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "UserProfile{" + "secondaryEmail=" + secondaryEmail + ", secondaryMobileNumber=" + secondaryMobileNumber + ", instantMessager=" + instantMessager + ", account=" + account + '}';
    }

}
