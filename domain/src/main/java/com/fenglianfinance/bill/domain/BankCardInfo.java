/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import com.fenglianfinance.bill.domain.support.PersistableEntity;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 *
 * @author hansy
 */
@Entity
@Table(name = "user_bank_cards")
public class BankCardInfo extends PersistableEntity<Long> {

    @Column(name = "bound_bank_id")
    private String bankId;

    @Column(name = "bound_card_id")
    private String cardId;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    public BankCardInfo(String openAcctId, String openBankId) {
        this.bankId = openBankId;
        this.cardId = openAcctId;
    }

    public BankCardInfo() {
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

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.bankId);
        hash = 79 * hash + Objects.hashCode(this.cardId);
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
        final BankCardInfo other = (BankCardInfo) obj;
        if (!Objects.equals(this.bankId, other.bankId)) {
            return false;
        }
        if (!Objects.equals(this.cardId, other.cardId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BankCardInfo{" + "bankId=" + bankId + ", cardId=" + cardId + '}';
    }

}
