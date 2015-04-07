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
@Table(name = "groups")
public class Group extends PersistableEntity<Long> {


  @ManyToOne
  @JoinColumn(name = "member_id")
  private UserAccount memberUser;

  @Column(name = "name")
  private String name;


  @Column(name = "create_on")
  private LocalDateTime createOn = LocalDateTime.now();

  public UserAccount getMemberUser() {
    return memberUser;
  }

  public void setMemberUser(UserAccount memberUser) {
    this.memberUser = memberUser;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getCreateOn() {
    return createOn;
  }

  public void setCreateOn(LocalDateTime createOn) {
    this.createOn = createOn;
  }

  @Override
  public String toString() {
    return "Group{" +
           "memberUser=" + memberUser +
           ", name='" + name + '\'' +
           ", createOn=" + createOn +
           '}';
  }
}
