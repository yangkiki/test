package com.fenglianfinance.bill.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BackLogAmount implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal billAmount = BigDecimal.ZERO;
    private BigDecimal financingAmount = BigDecimal.ZERO;
    private BackLogItem.Type type;

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

    public BackLogItem.Type getType() {
        return type;
    }

    public void setType(BackLogItem.Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BackLogAmount [billAmount=" + billAmount + ", financingAmount=" + financingAmount + ", type=" + type
                + "]";
    }

}
