package com.fenglianfinance.bill.report.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class UserRepayMentDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserRepayMentDetails() {
        super();
    }

    public UserRepayMentDetails(String username, String name, String mobileNumber, BigDecimal amount, BigDecimal fee,
            LocalDate transactedDate, String cardNumber) {
        super();
        this.username = username;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.amount = amount;
        this.fee = fee;
        this.transactedDate = transactedDate;
        this.cardNumber = cardNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public LocalDate getTransactedDate() {
        return transactedDate;
    }

    public void setTransactedDate(LocalDate transactedDate) {
        this.transactedDate = transactedDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private String username;

    private String name;

    private String mobileNumber;

    private BigDecimal amount;

    private BigDecimal fee;

    private LocalDate transactedDate;

    private String cardNumber;
}
