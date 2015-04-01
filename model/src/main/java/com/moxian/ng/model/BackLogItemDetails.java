package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BackLogItemDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private BillDetails bill;

    private String status;

    private String type;

    private Float annualInterestRate;

    private LocalDate completedDate;

    private BigDecimal financingAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BillDetails getBill() {
        return bill;
    }

    public void setBill(BillDetails bill) {
        this.bill = bill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "BackLogItemDetails [id=" + id + ", bill=" + bill + ", status=" + status + ", type=" + type
                + ", annualInterestRate=" + annualInterestRate + ", completedDate=" + completedDate
                + ", financingAmount=" + financingAmount + "]";
    }

}
