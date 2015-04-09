package com.moxian.ng.model;

import java.io.Serializable;


public class UserProfileDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserAccountDetails account;

    private String avatar;


    public UserAccountDetails getAccount() {
        return account;
    }

    public void setAccount(UserAccountDetails account) {
        this.account = account;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserProfileDetails{" +
               "account=" + account +
               ", avatar='" + avatar + '\'' +
               '}';
    }
}
