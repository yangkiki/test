package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class WithdrawalsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String name;

    public WithdrawalsDetails() {
        super();
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

    public WithdrawalsDetails(String username, String name, BigDecimal amount, BigDecimal fee, LocalDate transactedDate) {
        super();
        this.username = username;
        this.name = name;
        this.amount = amount;
        this.fee = fee;
        this.transactedDate = transactedDate;
    }

    public void setTransactedDate(LocalDate transactedDate) {
        this.transactedDate = transactedDate;
    }

    private BigDecimal amount;

    private BigDecimal fee;

    private LocalDate transactedDate;

}
