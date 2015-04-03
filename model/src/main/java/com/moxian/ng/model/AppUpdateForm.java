/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.model;

import java.io.Serializable;

import java.time.LocalDateTime;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
public class AppUpdateForm implements Serializable {

    private String appVersion;

    private String androidUrl;

    private String iosUrl;

    private String changeLog;

    private LocalDateTime createdDate;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "AppUpdateForm{" + "appVersion=" + appVersion + ", androidUrl=" + androidUrl + ", iosUrl=" + iosUrl + ", changeLog=" + changeLog + ", createdDate=" + createdDate + '}';
    }

}
