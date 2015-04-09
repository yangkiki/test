package com.moxian.ng.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.moxian.ng.domain.support.PersistableEntity;

@Entity
@Table(name = "fans")
public class Fans extends PersistableEntity<Long> {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "send_id")
    private UserAccount followingUser;

    @ManyToOne
    @JoinColumn(name = "recept_id")
    private UserAccount memberUser;

    @Column(name = "create_on")
    private LocalDateTime createOn;

    @Column(name = "active")
    private boolean active = true;

    public boolean isActive() {
        return active;
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

    public UserAccount getMemberUser() {
        return memberUser;
    }

    public void setMemberUser(UserAccount memberUser) {
        this.memberUser = memberUser;
    }

    public UserAccount getFollowingUser() {
        return followingUser;
    }

    public void setFollowingUser(UserAccount followingUser) {
        this.followingUser = followingUser;
    }

    // public UserAccount getSend() {
    // return send;
    // }
    //
    // public void setSend(UserAccount send) {
    // this.send = send;
    // }
    //
    // public UserAccount getRecept() {
    // return recept;
    // }
    //
    // public void setRecept(UserAccount recept) {
    // this.recept = recept;
    // }
    //
    // @Override
    // public String toString() {
    // return "Fans [send=" + send + ", recept=" + recept + ", createOn=" + createOn + ", active=" +
    // active + "]";
    // }

}
