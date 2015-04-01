/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

/**
 *
 * @author hansy
 */
//@Entity
//@Table(name = "back_log_item_amount")
public class BackLogItemAmount extends AuditableEntity<UserAccount, Long> {

    private static final long serialVersionUID = 1L;

    public BackLogItemAmount() {}

    public enum Type {
        DEMAND, //
        FIXED, //
        SINGLE, //
    }

    @Enumerated(EnumType.STRING)
    private Type type;

    private BigDecimal amount;

    private LocalDateTime updateDateTime;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Override
    public String toString() {
        return "BackLogItemAmount [type=" + type + ", amount=" + amount + ", updateDateTime=" + updateDateTime + "]";
    }

}
