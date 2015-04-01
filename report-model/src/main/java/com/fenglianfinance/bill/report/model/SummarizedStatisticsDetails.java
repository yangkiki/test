package com.fenglianfinance.bill.report.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

//import java.util.ArrayList;

public class SummarizedStatisticsDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private LocalDate summarizedDate;

  private int userRegisterationCount;

  private int idCardVerificationCount;

  private int inchargeUserCount;

  private BigDecimal totalInchargeAmount;

  private BigDecimal avgInchargeAmount;

  private int paidUserCount;

  private int paymentLogCount;

  private BigDecimal totalPaymentAmount;

  private BigDecimal avgPaymentAmount;

  private float avgPaymentCount;

  private int withdrawUserCount;

  private BigDecimal totalWithdrawAmount;

  private BigDecimal avgWithdrawAmount;

  private long userViewCount;

  private long pageViewCount;

  public LocalDate getSummarizedDate() {
    return summarizedDate;
  }

  public void setSummarizedDate(LocalDate summarizedDate) {
    this.summarizedDate = summarizedDate;
  }

  public int getUserRegisterationCount() {
    return userRegisterationCount;
  }

  public void setUserRegisterationCount(int userRegisterationCount) {
    this.userRegisterationCount = userRegisterationCount;
  }

  public int getIdCardVerificationCount() {
    return idCardVerificationCount;
  }

  public void setIdCardVerificationCount(int idCardVerificationCount) {
    this.idCardVerificationCount = idCardVerificationCount;
  }

  public int getInchargeUserCount() {
    return inchargeUserCount;
  }

  public void setInchargeUserCount(int inchargeUserCount) {
    this.inchargeUserCount = inchargeUserCount;
  }

  public BigDecimal getTotalInchargeAmount() {
    return totalInchargeAmount;
  }

  public void setTotalInchargeAmount(BigDecimal totalInchargeAmount) {
    this.totalInchargeAmount = totalInchargeAmount;
  }

  public BigDecimal getAvgInchargeAmount() {
    return avgInchargeAmount;
  }

  public void setAvgInchargeAmount(BigDecimal avgInchargeAmount) {
    this.avgInchargeAmount = avgInchargeAmount;
  }

  public int getPaidUserCount() {
    return paidUserCount;
  }

  public void setPaidUserCount(int paidUserCount) {
    this.paidUserCount = paidUserCount;
  }

  public int getPaymentLogCount() {
    return paymentLogCount;
  }

  public void setPaymentLogCount(int paymentLogCount) {
    this.paymentLogCount = paymentLogCount;
  }

  public BigDecimal getTotalPaymentAmount() {
    return totalPaymentAmount;
  }

  public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
    this.totalPaymentAmount = totalPaymentAmount;
  }

  public BigDecimal getAvgPaymentAmount() {
    return avgPaymentAmount;
  }

  public void setAvgPaymentAmount(BigDecimal avgPaymentAmount) {
    this.avgPaymentAmount = avgPaymentAmount;
  }

  public float getAvgPaymentCount() {
    return avgPaymentCount;
  }

  public void setAvgPaymentCount(float avgPaymentCount) {
    this.avgPaymentCount = avgPaymentCount;
  }

  public int getWithdrawUserCount() {
    return withdrawUserCount;
  }

  public void setWithdrawUserCount(int withdrawUserCount) {
    this.withdrawUserCount = withdrawUserCount;
  }

  public BigDecimal getTotalWithdrawAmount() {
    return totalWithdrawAmount;
  }

  public void setTotalWithdrawAmount(BigDecimal totalWithdrawAmount) {
    this.totalWithdrawAmount = totalWithdrawAmount;
  }

  public BigDecimal getAvgWithdrawAmount() {
    return avgWithdrawAmount;
  }

  public void setAvgWithdrawAmount(BigDecimal avgWithdrawAmount) {
    this.avgWithdrawAmount = avgWithdrawAmount;
  }

  public long getUserViewCount() {
    return userViewCount;
  }

  public void setUserViewCount(long userViewCount) {
    this.userViewCount = userViewCount;
  }

  public long getPageViewCount() {
    return pageViewCount;
  }

  public void setPageViewCount(long pageViewCount) {
    this.pageViewCount = pageViewCount;
  }

  @Override
  public String toString() {
    return "SummarizedStatisticsDetails{" +
           "summarizedDate=" + summarizedDate +
           ", userRegisterationCount=" + userRegisterationCount +
           ", idCardVerificationCount=" + idCardVerificationCount +
           ", inchargeUserCount=" + inchargeUserCount +
           ", totalInchargeAmount=" + totalInchargeAmount +
           ", avgInchargeAmount=" + avgInchargeAmount +
           ", paidUserCount=" + paidUserCount +
           ", paymentLogCount=" + paymentLogCount +
           ", totalPaymentAmount=" + totalPaymentAmount +
           ", avgPaymentAmount=" + avgPaymentAmount +
           ", avgPaymentCount=" + avgPaymentCount +
           ", withdrawUserCount=" + withdrawUserCount +
           ", totalWithdrawAmount=" + totalWithdrawAmount +
           ", avgWithdrawAmount=" + avgWithdrawAmount +
           ", userViewCount=" + userViewCount +
           ", pageViewCount=" + pageViewCount +
           '}';
  }
}
