package com.moxian.ng.model;

import java.io.Serializable;
import java.time.LocalDateTime;


public class GroupDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private UserAccountDetails memberUser;

  private String name;


  private LocalDateTime createOn;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserAccountDetails getMemberUser() {
    return memberUser;
  }

  public void setMemberUser(UserAccountDetails memberUser) {
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
    return "GroupDetails{" +
           "id=" + id +
           ", memberUser=" + memberUser +
           ", name='" + name + '\'' +
           ", createOn=" + createOn +
           '}';
  }
}
