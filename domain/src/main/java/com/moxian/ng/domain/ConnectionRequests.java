/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.domain;

import com.moxian.ng.domain.support.PersistableEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "connection_requests")
public class ConnectionRequests extends PersistableEntity<Long> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public enum Status {

        UNREAD, //
        ACCEPT, //
        REJECT;//
    }

    @Column(name = "source")
    private String source;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.UNREAD;

    @Column(name = "create_on")
    private LocalDateTime createOn;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private UserAccount memberUser;

    @ManyToOne
    @JoinColumn(name = "connected_to")
    private UserAccount connectedUser;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public UserAccount getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(UserAccount connectedUser) {
        this.connectedUser = connectedUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConnectionRequests{" + "source='" + source + '\'' + ", status='" + status + '\'' + ", createOn="
                + createOn + ", memberUser=" + memberUser + ", connectedUser=" + connectedUser + '}';
    }
}
