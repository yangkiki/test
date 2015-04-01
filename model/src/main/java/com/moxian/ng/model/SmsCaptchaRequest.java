/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.model;

import java.io.Serializable;

/**
 *
 * @author hansy
 */
public class SmsCaptchaRequest implements Serializable {

    private String mobileNumber;
    private String captchaValue;
    private Long userId;
    private Boolean removeSmsCode = true;

    public SmsCaptchaRequest(String mobile, String result) {
        this.mobileNumber = mobile;
        this.captchaValue = result;
    }
    
    public SmsCaptchaRequest(String mobile, String result, Boolean removeSmsCode){
    	this(mobile, result);
    	this.removeSmsCode = removeSmsCode;
    }

    public Long isUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public SmsCaptchaRequest() {
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobile) {
        this.mobileNumber = mobile;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    public void setRemoveSmsCode(Boolean removeSmsCode) {
		this.removeSmsCode = removeSmsCode;
	}
    
    public Boolean getRemoveSmsCode() {
		return removeSmsCode;
	}

	@Override
	public String toString() {
		return "SmsCaptchaRequest [mobileNumber=" + mobileNumber
				+ ", captchaValue=" + captchaValue + ", userId=" + userId
				+ ", removeSmsCode=" + removeSmsCode + "]";
	}

}
