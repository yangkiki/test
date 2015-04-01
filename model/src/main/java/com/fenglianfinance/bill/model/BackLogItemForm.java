package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BackLogItemForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public BackLogItemForm() {}

    private Long billId;

    private Long backLogId;

    private String type;

    private Float annualInterestRate;

    private LocalDate completedDate;

    private BigDecimal financingAmount;

    public Long getBackLogId() {
        return backLogId;
    }

    public void setBackLogId(Long backLogId) {
        this.backLogId = backLogId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    public Float getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(Float annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    @Override
    public String toString() {
        return "BackLogItemForm [billId=" + billId + ", backLogId=" + backLogId + ", type=" + type
                + ", annualInterestRate=" + annualInterestRate + ", completedDate=" + completedDate
                + ", financingAmount=" + financingAmount + "]";
    }

}
