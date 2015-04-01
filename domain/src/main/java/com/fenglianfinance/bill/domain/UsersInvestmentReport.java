package com.fenglianfinance.bill.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fenglianfinance.bill.domain.Product.Type;

public class UsersInvestmentReport implements Serializable {

  private static final long serialVersionUID = 1L;

  private String productName;
  private Product.Type type;
  private int duration = 0;
  private String userName;
  private String cardNumber;
  private String mobileNumber;
  private Long userId;
  private BigDecimal amount = BigDecimal.ZERO;
  private LocalDateTime paidDate;

  public UsersInvestmentReport() {
  }


public UsersInvestmentReport(String productName, Type type, int duration, String userName, String cardNumber,
        String mobileNumber, Long userId, BigDecimal amount, LocalDateTime paidDate) {
    super();
    this.productName = productName;
    this.type = type;
    this.duration = duration;
    this.userName = userName;
    this.cardNumber = cardNumber;
    this.mobileNumber = mobileNumber;
    this.userId = userId;
    this.amount = amount;
    this.paidDate = paidDate;
}

    public UsersInvestmentReport(String productName, Product.Type type, int duration, String userName,
            String cardNumber,Long userId
  ,BigDecimal amount,LocalDateTime paidDate) {
    this.productName = productName;
    this.type = type;
    this.duration = duration;
    this.userName=userName;
    this.cardNumber=cardNumber;
    this.userId=userId;
    this.amount=amount;
    this.paidDate=paidDate;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Product.Type getType() {
    return type;
  }

  public void setType(Product.Type type) {
    this.type = type;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public LocalDateTime getPaidDate() {
    return paidDate;
  }

  public void setPaidDate(LocalDateTime paidDate) {
    this.paidDate = paidDate;
  }

  @Override
  public String toString() {
    return "UsersInvestmentReport{" +
           "productName='" + productName + '\'' +
           ", type=" + type +
           ", duration=" + duration +
           ", userName='" + userName + '\'' +
           ", cardNumber='" + cardNumber + '\'' +
           ", mobileNumber='" + mobileNumber + '\'' +
           ", userId='" + userId + '\'' +
           ", amount=" + amount +
           ", paidDate=" + paidDate +
           '}';
  }
}
