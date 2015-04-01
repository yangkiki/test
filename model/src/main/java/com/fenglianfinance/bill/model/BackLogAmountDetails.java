package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BackLogAmountDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal billAmount = BigDecimal.ZERO;
    private BigDecimal financingAmount = BigDecimal.ZERO;
    private String type;

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BackLogAmountDetails [billAmount=" + billAmount + ", financingAmount=" + financingAmount + ", type="
                + type + "]";
    }

}
