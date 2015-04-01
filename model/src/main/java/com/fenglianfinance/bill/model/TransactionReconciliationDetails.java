/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author hantsy
 */
public class TransactionReconciliationDetails implements Serializable {

    private Long id;

    private String serialNumber;

    private String type;

    private String status;

    private Long totalItems;

    private BigDecimal totalAmount;

    private Long mismatchedItems;

    private BigDecimal mismatchedAmount;

    private LocalDate checkDate;

    private LocalDateTime createdDate;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getMismatchedItems() {
        return mismatchedItems;
    }

    public void setMismatchedItems(Long mismatchedItems) {
        this.mismatchedItems = mismatchedItems;
    }

    public BigDecimal getMismatchedAmount() {
        return mismatchedAmount;
    }

    public void setMismatchedAmount(BigDecimal mismatchedAmount) {
        this.mismatchedAmount = mismatchedAmount;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransactionReconciliationDetails{" 
                + "id=" + id 
                + ", serialNumber=" + serialNumber 
                + ", type=" + type 
                + ", status=" + status 
                + ", totalItems=" + totalItems 
                + ", totalAmount=" + totalAmount 
                + ", mismatchedItems=" + mismatchedItems 
                + ", mismatchedAmount=" + mismatchedAmount 
                + ", checkDate=" + checkDate 
                + ", createdDate=" + createdDate + '}';
    }

}
