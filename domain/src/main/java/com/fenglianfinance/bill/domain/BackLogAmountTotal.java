package com.fenglianfinance.bill.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class BackLogAmountTotal implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal totalBillAmount = BigDecimal.ZERO;
    private BigDecimal totalFinancingAmount = BigDecimal.ZERO;

    private HashMap<String, BackLogAmount> backLogAmountMap = new HashMap<String, BackLogAmount>();

    private BigDecimal orderAmount = BigDecimal.ZERO;
    private BigDecimal completedOrderAmount = BigDecimal.ZERO;

    public BigDecimal getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(BigDecimal totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public BigDecimal getTotalFinancingAmount() {
        return totalFinancingAmount;
    }

    public void setTotalFinancingAmount(BigDecimal totalFinancingAmount) {
        this.totalFinancingAmount = totalFinancingAmount;
    }

    public HashMap<String, BackLogAmount> getBackLogAmountMap() {
        return backLogAmountMap;
    }

    public void setBackLogAmountMap(HashMap<String, BackLogAmount> backLogAmountMap) {
        this.backLogAmountMap = backLogAmountMap;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getCompletedOrderAmount() {
        return completedOrderAmount;
    }

    public void setCompletedOrderAmount(BigDecimal completedOrderAmount) {
        this.completedOrderAmount = completedOrderAmount;
    }

}
