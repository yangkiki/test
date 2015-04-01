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

public class TransactionLogDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String serialNumber;

  private String type;

  private String status;

  private BigDecimal amount;

  private BigDecimal fee;

  private UserAcctValue from;
  private UserAcctValue to;

  private LocalDate transactedDate;

  private LocalDateTime createdDate;

  private NameValue fixedBy;
  private LocalDateTime fixedDate;

  private LocalDate checkDate;

  private String fromAcctId;
  private String toAcctId;

  private String fromCustId;
  private String toCustId;

  private String acctStatus;

  private String queryResult;

  public String getQueryResult() {
    return queryResult;
  }

  public void setQueryResult(String queryResult) {
    this.queryResult = queryResult;
  }

  public String getAcctStatus() {
    return acctStatus;
  }

  public void setAcctStatus(String acctStatus) {
    this.acctStatus = acctStatus;
  }

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

  public UserAcctValue getFrom() {
    return from;
  }

  public void setFrom(UserAcctValue from) {
    this.from = from;
  }

  public UserAcctValue getTo() {
    return to;
  }

  public void setTo(UserAcctValue to) {
    this.to = to;
  }

  public NameValue getFixedBy() {
    return fixedBy;
  }

  public void setFixedBy(NameValue fixedBy) {
    this.fixedBy = fixedBy;
  }


  public LocalDateTime getFixedDate() {
    return fixedDate;
  }

  public void setFixedDate(LocalDateTime fixedDate) {
    this.fixedDate = fixedDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public LocalDate getCheckDate() {
    return checkDate;
  }

  public void setCheckDate(LocalDate checkDate) {
    this.checkDate = checkDate;
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

  @Override
  public String toString() {
    return "TransactionLogDetails{" +
           "id=" + id +
           ", serialNumber='" + serialNumber + '\'' +
           ", type='" + type + '\'' +
           ", status='" + status + '\'' +
           ", amount=" + amount +
           ", fee=" + fee +
           ", from=" + from +
           ", to=" + to +
           ", transactedDate=" + transactedDate +
           ", createdDate=" + createdDate +
           ", fixedBy=" + fixedBy +
           ", fixedDate=" + fixedDate +
           ", checkDate=" + checkDate +
           ", fromAcctId='" + fromAcctId + '\'' +
           ", toAcctId='" + toAcctId + '\'' +
           ", fromCustId='" + fromCustId + '\'' +
           ", toCustId='" + toCustId + '\'' +
           ", acctStatus='" + acctStatus + '\'' +
           ", queryResult='" + queryResult + '\'' +
           '}';
  }
}
