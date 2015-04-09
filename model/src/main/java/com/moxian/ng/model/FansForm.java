package com.moxian.ng.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FansForm implements Serializable {

    private static final long serialVersionUID = -9069730988536092598L;

    // private UserAccountDetails send;
    //
    // private UserAccountDetails recept;

    private Long memberUserId;

    private Long followingUserId;

    private LocalDateTime createOn;

    // public Long getSendId() {
    // return sendId;
    // }
    //
    // public void setSendId(Long sendId) {
    // this.sendId = sendId;
    // }
    //
    // public Long getReceptId() {
    // return receptId;
    // }
    //
    // public void setReceptId(Long receptId) {
    // this.receptId = receptId;
    // }

    private boolean active;

    public Long getMemberUserId() {
        return memberUserId;
    }

    public void setMemberUserId(Long memberUserId) {
        this.memberUserId = memberUserId;
    }

    public Long getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(Long followingUserId) {
        this.followingUserId = followingUserId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public LocalDateTime getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDateTime createOn) {
        this.createOn = createOn;
    }

}
