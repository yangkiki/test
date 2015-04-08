package com.moxian.ng.model;


import java.io.Serializable;

public class GroupForm implements Serializable {

  private static final long serialVersionUID = 1L;


  private Long memberUserPK;

  private String name;

  public Long getMemberUserPK() {
    return memberUserPK;
  }

  public void setMemberUserPK(Long memberUserPK) {
    this.memberUserPK = memberUserPK;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "GroupForm{" +
           "memberUserPK=" + memberUserPK +
           ", name='" + name + '\'' +
           '}';
  }
}
