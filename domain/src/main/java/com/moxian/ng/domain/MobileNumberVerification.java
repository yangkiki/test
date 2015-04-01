/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@Embeddable
public class MobileNumberVerification implements Serializable {

	@Column(name = "verfied_mobile_number")
	private String mobileNumber;
        
	private LocalDateTime verifiedDate;

	public MobileNumberVerification(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		this.verifiedDate = LocalDateTime.now();
	}

	public MobileNumberVerification() {
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDateTime getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(LocalDateTime verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	@Override
	public String toString() {
		return "MobileNumberVerification{" + "mobileNumber=" + mobileNumber
				+ ", verifiedDate=" + verifiedDate + '}';
	}

}
