package com.moxian.ng.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.moxian.ng.domain.support.AuditableEntity;

import java.util.DoubleSummaryStatistics;

@Entity
@Table(name = "user_profiles")
@Access(AccessType.FIELD)
public class UserProfile extends AuditableEntity<UserProfile, Long> {

  public enum Gender {

    NA, //
    MALE, //
    FAMALE;//
  }

  public enum Status {
    DISPLAY,
    NOT_DISPLAY
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "gender")
  private Gender gender = Gender.NA;


  @OneToOne
  @JoinColumn(name = "user_id")
  private UserAccount account;

  @Column(name = "avatar")
  private String avatar;


  @Column(name = "home_town")
  private String homeTown;

  @Column(name = "favorite_place")
  private String favoritePlace;

  @Column(name = "school")
  private String school;

  @Column(name = "major")
  private String major;

  @Column(name = "expertise")
  private String expertise;

  @Column(name = "tags")
  private String tags;

  @Column(name = "last_daily_mood")
  private String lastDailyMood;

  @Column(name = "city")
  private String city;

  @Column(name = "latitude")
  private Double latitude;

  @Column(name = "longitude")
  private Double longitude;

  @Column(name = "qrcode")
  private String qrcode;

  @Column(name = "location_visibility")
  @Enumerated(EnumType.STRING)
  private Status locationVisibility = Status.NOT_DISPLAY;

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
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

  public Status getLocationVisibility() {
    return locationVisibility;
  }

  public void setLocationVisibility(Status locationVisibility) {
    this.locationVisibility = locationVisibility;
  }

  public UserAccount getAccount() {
    return account;
  }

  public void setAccount(UserAccount account) {
    this.account = account;
  }

  @Override
  public String toString() {
    return "UserProfile{" +
           "gender=" + gender +
           ", account=" + account +
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
           ", locationVisibility=" + locationVisibility +
           '}';
  }
}
