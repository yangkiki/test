package com.moxian.ng.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.moxian.ng.domain.support.AuditableEntity;

@Entity
@Table(name = "user_profiles")
@Access(AccessType.FIELD)
public class UserProfile extends AuditableEntity<UserProfile, Long> {



    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAccount account;

    @Column(name = "avatar")
    private String avatar;

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
               "account=" + account +
               '}';
    }
}
