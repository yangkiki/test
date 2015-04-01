package com.fenglianfinance.bill.report.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class UserReceivablesReport implements Serializable {

  private static final long serialVersionUID = 1L;

  private String productName;
  private LocalDate accruedEndDate;
  private String userName;
  private BigDecimal amount = BigDecimal.ZERO;
  private BigDecimal accruedInterestAmount = BigDecimal.ZERO;
  private Float annualPercentageRate = 0.0F;

  public UserReceivablesReport(String productName, LocalDate accruedEndDate,
                               String userName, BigDecimal amount,
                               BigDecimal accruedInterestAmount, Float annualPercentageRate) {
    this.productName = productName;
    this.accruedEndDate = accruedEndDate;
    this.userName = userName;
    this.amount = amount;
    this.accruedInterestAmount = accruedInterestAmount;
    this.annualPercentageRate = annualPercentageRate;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public LocalDate getAccruedEndDate() {
    return accruedEndDate;
  }

  public void setAccruedEndDate(LocalDate accruedEndDate) {
    this.accruedEndDate = accruedEndDate;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getAccruedInterestAmount() {
    return accruedInterestAmount;
  }

  public void setAccruedInterestAmount(BigDecimal accruedInterestAmount) {
    this.accruedInterestAmount = accruedInterestAmount;
  }

  public float getAnnualPercentageRate() {
    return annualPercentageRate;
  }

  public void setAnnualPercentageRate(float annualPercentageRate) {
    this.annualPercentageRate = annualPercentageRate;
  }

  @Override
  public String toString() {
    return "UserReceivablesView{" +
           "productName='" + productName + '\'' +
           ", accruedEndDate=" + accruedEndDate +
           ", userName='" + userName + '\'' +
           ", amount=" + amount +
           ", accruedInterestAmount=" + accruedInterestAmount +
           ", annualPercentageRate=" + annualPercentageRate +
           '}';
  }
}
