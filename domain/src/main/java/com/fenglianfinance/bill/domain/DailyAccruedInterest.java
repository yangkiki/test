/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import com.fenglianfinance.bill.domain.support.PersistableEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hantsy
 */
//@Embeddable
@Entity
@Table(name = "daily_acrrue_interest")
public class DailyAccruedInterest extends PersistableEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Column(name = "amount")
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "current_rate")
    private Float currentRate;

    @Column(name = "accrued_date")
    private LocalDateTime accruedDate;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private PurchaseOrder order;

    public DailyAccruedInterest(BigDecimal amount, Float currentRate) {
        this();
        this.amount = amount;
        this.currentRate = currentRate;
    }

    public DailyAccruedInterest() {
        this.accruedDate = LocalDateTime.now();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Float getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(Float currentRate) {
        this.currentRate = currentRate;
    }

    public LocalDateTime getAccruedDate() {
        return accruedDate;
    }

    public void setAccruedDate(LocalDateTime accruedDate) {
        this.accruedDate = accruedDate;
    }

    public PurchaseOrder getOrder() {
        return order;
    }

    public void setOrder(PurchaseOrder order) {
        this.order = order;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.amount);
        hash = 79 * hash + Objects.hashCode(this.currentRate);
        hash = 79 * hash + Objects.hashCode(this.accruedDate);
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
        final DailyAccruedInterest other = (DailyAccruedInterest) obj;
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        if (!Objects.equals(this.currentRate, other.currentRate)) {
            return false;
        }
        if (!Objects.equals(this.accruedDate, other.accruedDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DailyAccruedInterest{" + "amount=" + amount + ", currentRate=" + currentRate + ", accruedDate=" + accruedDate + '}';
    }

}
