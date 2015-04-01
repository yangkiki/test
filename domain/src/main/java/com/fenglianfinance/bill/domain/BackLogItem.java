/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

/**
 *
 * @author hansy
 */
@Entity
@Table(name = "back_log_item", uniqueConstraints = {@UniqueConstraint(columnNames = {"bill_id"})//
        })
public class BackLogItem extends AuditableEntity<UserAccount, Long> {

    private static final long serialVersionUID = 1L;

    public enum Type {

        DEMAND, //
        FIXED, //
        SINGLE, //
    }

    public enum Status {

        UNASSIGNED, //
        ASSIGNING, //
        ASSIGNED, // for hot and newbie product, it is assgined when the product is created.
        // For fixed and demand type, it is changed when all of amount is sold out.
    }

    public enum Valid {
        VALID, EXPIRATION,
    }

    public BackLogItem() {}

    @OneToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "back_log_id")
    private BackLog backLog;

    @Enumerated(EnumType.STRING)
    private Status status = Status.UNASSIGNED;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "annual_interest_rate")
    private Float annualInterestRate;

    @Column(name = "completed_date")
    private LocalDate completedDate;

    @Column(name = "financing_amount")
    private BigDecimal financingAmount;

    @Enumerated(EnumType.STRING)
    private Valid valid;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BackLog getBackLog() {
        return backLog;
    }

    public void setBackLog(BackLog backLog) {
        this.backLog = backLog;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Float getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(Float annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    public Valid getValid() {
        return valid;
    }

    public void setValid(Valid valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "BackLogItem [bill=" + bill + ", backLog=" + backLog + ", status=" + status + ", type=" + type
                + ", annualInterestRate=" + annualInterestRate + ", completedDate=" + completedDate
                + ", financingAmount=" + financingAmount + ", valid=" + valid + "]";
    }

}
