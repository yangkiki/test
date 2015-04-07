/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.domain;

import com.moxian.ng.domain.support.PersistableEntity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "connections")
public class Connections extends PersistableEntity<Long> {

  @Column(name = "source")
  private String source;

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

  @Override
  public String toString() {
    return "Connections{" +
           "source='" + source + '\'' +
           ", createOn=" + createOn +
           ", memberUser=" + memberUser +
           ", connectedUser=" + connectedUser +
           '}';
  }
}
