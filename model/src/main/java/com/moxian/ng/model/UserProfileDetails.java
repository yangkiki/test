package com.moxian.ng.model;

import java.io.Serializable;


public class UserProfileDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private UserAccountDetails account;

  private String avatar;

  private String homeTown;

  private String favoritePlace;

  private String school;

  private String major;

  private String expertise;

  private String tags;

  private String lastDailyMood;

  private String city;

  private Double latitude;

  private Double longitude;

  private String qrcode;

  private String locationVisibility;


  private String gender;


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

  public String getHomeTown() {
    return homeTown;
  }

  public void setHomeTown(String homeTown) {
    this.homeTown = homeTown;
  }

  public String getFavoritePlace() {
    return favoritePlace;
  }

  public void setFavoritePlace(String favoritePlace) {
    this.favoritePlace = favoritePlace;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  public String getExpertise() {
    return expertise;
  }

  public void setExpertise(String expertise) {
    this.expertise = expertise;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getLastDailyMood() {
    return lastDailyMood;
  }

  public void setLastDailyMood(String lastDailyMood) {
    this.lastDailyMood = lastDailyMood;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getQrcode() {
    return qrcode;
  }

  public void setQrcode(String qrcode) {
    this.qrcode = qrcode;
  }

  public String getLocationVisibility() {
    return locationVisibility;
  }

  public void setLocationVisibility(String locationVisibility) {
    this.locationVisibility = locationVisibility;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "UserProfileDetails{" +
           "account=" + account +
           ", avatar='" + avatar + '\'' +
           ", homeTown='" + homeTown + '\'' +
           ", favoritePlace='" + favoritePlace + '\'' +
           ", school='" + school + '\'' +
           ", major='" + major + '\'' +
           ", expertise='" + expertise + '\'' +
           ", tags='" + tags + '\'' +
           ", lastDailyMood='" + lastDailyMood + '\'' +
           ", city='" + city + '\'' +
           ", latitude=" + latitude +
           ", longitude=" + longitude +
           ", qrcode='" + qrcode + '\'' +
           ", locationVisibility='" + locationVisibility + '\'' +
           ", gender='" + gender + '\'' +
           '}';
  }
}
