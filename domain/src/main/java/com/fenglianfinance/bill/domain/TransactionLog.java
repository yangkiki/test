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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author hansy
 */
@Entity
@Table(name = "transaction_logs",//
    uniqueConstraints = {//
    @UniqueConstraint(columnNames = {"serial_number"})//
    })
public class TransactionLog extends PersistableEntity<Long> {

    public enum Status {

        SUCCESS,//
        FAILED, //
        IN_PROGRESS//
    }

    public enum QueryResult {

        STATUS_MATCHED,//
        LOCAL_DATA_MISSING, //
        LOCAL_FAILED_BUT_REMOTE_SUCCESS,//
        LOCAL_SUCCESS_BUT_REMOTE_FAILED,//
        STATUS_MISMATCHED//
    }

    @Column(name = "serial_number")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "t_type")
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "t_status")
    private Status status = Status.SUCCESS;

    @JoinColumn(name = "order_id")
    @ManyToOne()
    private PurchaseOrder order;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "fee")
    private BigDecimal fee = BigDecimal.ZERO;

    @JoinColumn(name = "from_user_id")
    @ManyToOne()
    private UserAccount from;

    @Column(name = "from_acct_id")
    private String fromAcctId;

    @Column(name = "from_cust_id")
    private String fromCustId;

    @JoinColumn(name = "to_user_id")
    @ManyToOne()
    private UserAccount to;

    @Column(name = "to_acct_id")
    private String toAcctId;

    @Column(name = "to_cust_id")
    private String toCustId;

    @Column(name = "transacted_date")
    private LocalDate transactedDate;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "acct_status")
    private Status acctStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "query_result")
    private QueryResult queryResult;

    @JoinColumn(name = "fixed_by_id")
    @ManyToOne()
    private UserAccount fixedBy;

    @Column(name = "fixedDate")
    private LocalDateTime fixedDate;

    @Column(name = "check_date")
    private LocalDate checkDate;

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PurchaseOrder getOrder() {
        return order;
    }

    public void setOrder(PurchaseOrder order) {
        this.order = order;
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

    public UserAccount getFrom() {
        return from;
    }

    public void setFrom(UserAccount from) {
        this.from = from;
    }

    public UserAccount getTo() {
        return to;
    }

    public void setTo(UserAccount user) {
        this.to = user;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

//    public Boolean getFixed() {
//        return fixed;
//    }
//
//    public void setFixed(Boolean fixed) {
//        this.fixed = fixed;
//    }

    
    public LocalDateTime getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(LocalDateTime fixedDate) {
        this.fixedDate = fixedDate;
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

    public Status getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(Status acctStatus) {
        this.acctStatus = acctStatus;
    }

    public QueryResult getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(QueryResult queryStatus) {
        this.queryResult = queryStatus;
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

    public UserAccount getFixedBy() {
        return fixedBy;
    }

    public void setFixedBy(UserAccount fixedBy) {
        this.fixedBy = fixedBy;
    }
    
    

    @Override
    public String toString() {
        return "TransactionLog{"
                + "serialNumber=" + serialNumber
                + ", type=" + type
                + ", status=" + status
                + ", order=" + order
                + ", amount=" + amount
                + ", fee=" + fee
                + ", from=" + from
                + ", to=" + to
                + ", fromAcctId=" + fromAcctId
                + ", toAcctId=" + toAcctId
                + ", fromCustId=" + fromCustId
                + ", toCustId=" + toCustId
                + ", transactedDate=" + transactedDate
                + ", createdDate=" + createdDate
                + ", fixedBy=" + fixedBy
                + ", acctStatus=" + acctStatus
                + ", queryStatus=" + queryResult
                + ", fixedDate=" + fixedDate
                + '}';
    }

}
