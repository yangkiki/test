/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import com.fenglianfinance.bill.domain.support.PersistableEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author hantsy
 */
@Entity
@Table(name = "transaction_reconciliations")
public class TransactionReconciliation extends PersistableEntity<Long> {

    public enum Status {

        FAILED,
        SUCCESS
    }

    @Column(name = "serial_number")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "t_type")
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "result_status")
    private Status status=Status.SUCCESS;

    @Column(name = "total_items")
    private Long totalItems = 0L;

    @Column(name = "total_amount")
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "mismatched_items")
    private Long mismatchedItems = 0L;

    @Column(name = "mismatched_amount")
    private BigDecimal mismatchedAmount = BigDecimal.ZERO;

    @Column(name = "check_date")
    private LocalDate checkDate;

    @CreatedDate
    @Column(name = "created_date")
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status resultStatus) {
        this.status = resultStatus;
    }

    @Override
    public String toString() {
        return "TransactionQueryResult{"
                + "serialNumber=" + serialNumber
                + ", type=" + type
                + ", resultStatus=" + status
                + ", totalItems=" + totalItems
                + ", totalAmount=" + totalAmount
                + ", mismatchedItems=" + mismatchedItems
                + ", mismatchedAmount=" + mismatchedAmount
                + ", checkDate=" + checkDate
                + ", createdDate=" + createdDate + '}';
    }

}
