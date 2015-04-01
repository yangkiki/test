package com.fenglianfinance.bill.report.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RechargeDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String acctCustId;

    private String name;

    private BigDecimal amount;

    private BigDecimal fee;

    public RechargeDetails() {
    }

    public RechargeDetails(String acctCustId, String name, BigDecimal amount, BigDecimal fee, LocalDate transactedDate) {
        this.acctCustId = acctCustId;
        this.name = name;
        this.amount = amount;
        this.fee = fee;
        this.transactedDate = transactedDate;

    }

    public String getAcctCustId() {
        return acctCustId;
    }

    public void setAcctCustId(String acctCustId) {
        this.acctCustId = acctCustId;
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

    public void setTransactedDate(LocalDate transactedDate) {
        this.transactedDate = transactedDate;
    }

    private LocalDate transactedDate;
}
