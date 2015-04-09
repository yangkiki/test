package com.moxian.ng.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FansDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private UserAccountDetails memberUser;

    private UserAccountDetails followingUser;

    private boolean active;

    private LocalDateTime createOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // public UserAccountDetails getSend() {
    // return send;
    // }
    //
    // public void setSend(UserAccountDetails send) {
    // this.send = send;
    // }
    //
    // public UserAccountDetails getRecept() {
    // return recept;
    // }
    //
    // public void setRecept(UserAccountDetails recept) {
    // this.recept = recept;
    // }

    public boolean isActive() {
        return active;
    }

    public UserAccountDetails getMemberUser() {
        return memberUser;
    }

    public void setMemberUser(UserAccountDetails memberUser) {
        this.memberUser = memberUser;
    }

    public UserAccountDetails getFollowingUser() {
        return followingUser;
    }

    public void setFollowingUser(UserAccountDetails followingUser) {
        this.followingUser = followingUser;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDateTime createOn) {
        this.createOn = createOn;
    }

}
