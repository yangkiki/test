/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
public class BankCardInfoDetails implements Serializable {

    private String bankId;
    private String cardId;
    private LocalDateTime createdDate;

    public BankCardInfoDetails(String openAccId, String openBankId) {
        this.bankId = openBankId;
        this.bankId = openAccId;
    }

    public BankCardInfoDetails() {
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.bankId);
        hash = 11 * hash + Objects.hashCode(this.cardId);
        hash = 11 * hash + Objects.hashCode(this.createdDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BankCardInfoDetails other = (BankCardInfoDetails) obj;
        if (!Objects.equals(this.bankId, other.bankId)) {
            return false;
        }
        if (!Objects.equals(this.cardId, other.cardId)) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BankCardInfo{" + "bankId=" + bankId + ", cardId=" + cardId + '}';
    }

}
