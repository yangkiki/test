package com.moxian.ng.model;

import java.io.Serializable;
import java.util.List;

public class UserForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String name;

    private List<String> roles;

    private String mobileNumber;

    private String email;

    private boolean active;

    private String gender;

    private Long id;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserForm [username=" + username + ", password=" + password + ", name=" + name + ", roles=" + roles
                + ", mobileNumber=" + mobileNumber + ", email=" + email + ", active=" + active + ", gender=" + gender
                + ", id=" + id + "]";
    }

}
