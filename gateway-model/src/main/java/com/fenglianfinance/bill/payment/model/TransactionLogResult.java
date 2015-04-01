/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionLogResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String serialNumber;

    private String type;

    private String status;

    private BigDecimal amount;

    private BigDecimal fee;

    private String fromCustId;
    private String toCustId;

    private String fromAcctId;
    private String toAcctId;

    private LocalDate transactedDate;
    
    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getTransactedDate() {
        return transactedDate;
    }

    public void setTransactedDate(LocalDate transactedDate) {
        this.transactedDate = transactedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFromCustId() {
        return fromCustId;
    }

    public void setFromCustId(String fromCustId) {
        this.fromCustId = fromCustId;
    }

    public String getToCustId() {
        return toCustId;
    }

    public void setToCustId(String toCustId) {
        this.toCustId = toCustId;
    }

    public String getFromAcctId() {
        return fromAcctId;
    }

    public void setFromAcctId(String fromAcctId) {
        this.fromAcctId = fromAcctId;
    }

    public String getToAcctId() {
        return toAcctId;
    }

    public void setToAcctId(String toAcctId) {
        this.toAcctId = toAcctId;
    }
    
    

    @Override
    public String toString() {
        return "TransactionLogDetails{"
                + "id=" + id
                + ", serialNumber=" + serialNumber
                + ", type=" + type
                + ", amount=" + amount
                + ", fee=" + fee
                + ", fromCustId=" + fromCustId
                + ", toCustId=" + toCustId
                + ", fromAcctId=" + fromAcctId
                + ", toAcctId=" + toAcctId
                + ", transactedDate=" + transactedDate
                + ", createdDate=" + createdDate
                + '}';
    }

}
