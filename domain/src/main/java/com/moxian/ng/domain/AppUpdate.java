/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.domain;

import com.moxian.ng.domain.support.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
@Entity
@Table(name = "app_updates")
public class AppUpdate extends AuditableEntity<UserAccount, Long> {

    @Column(name = "app_version")
    private String appVersion;

    @Column(name = "android_url")
    private String androidUrl;

    @Column(name = "ios_url")
    private String iosUrl;

    @Column(name = "change_log")
    @Size(max = 2000)
    private String changeLog;

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

    @Override
    public String toString() {
        return "AppUpdate{" + "appVersion=" + appVersion + ", androidUrl=" + androidUrl + ", iosUrl=" + iosUrl + ", changeLog=" + changeLog + '}';
    }
}
