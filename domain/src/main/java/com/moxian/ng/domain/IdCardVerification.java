/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
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
public class IdCardVerification implements Serializable {

	@Column(name = "card_name")
	private String cardName;
        
	@Column(name = "card_number")
	private String cardNumber;
        
	private LocalDateTime verifiedDate;

	public IdCardVerification(String cardName, String cardNumber) {
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		 this.verifiedDate = LocalDateTime.now();
	}

    public IdCardVerification() {
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDateTime getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(LocalDateTime verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	@Override
	public String toString() {
		return "IdCardVerification{" + "cardName=" + cardName + ", cardNumber="
				+ cardNumber + ", verifiedDate=" + verifiedDate + '}';
	}

}
