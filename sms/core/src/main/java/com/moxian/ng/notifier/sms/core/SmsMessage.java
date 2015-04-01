/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.notifier.sms.core;

import java.io.Serializable;

/**
 *
 * @author hantsy<hantsy@gmail.com><hantsy<hantsy@gmail.com>@gmail.com>
 */
public class SmsMessage implements Serializable {

    private String mobileNumber;
    private String content;

    public SmsMessage() {
    }

    public SmsMessage(String mobileNumber, String message) {
        this.mobileNumber = mobileNumber;
        this.content = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "SmsMessage{" + "mobileNumber=" + mobileNumber + ", content=" + content + '}';
    }
    
}
