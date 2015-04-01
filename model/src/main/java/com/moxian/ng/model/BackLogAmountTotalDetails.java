package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

public class BackLogAmountTotalDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal totalBillAmount = BigDecimal.ZERO;
    private BigDecimal totalFinancingAmount = BigDecimal.ZERO;

    private HashMap<String, BackLogAmountDetails> backLogAmountMap = new HashMap<String, BackLogAmountDetails>();

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

    public HashMap<String, BackLogAmountDetails> getBackLogAmountMap() {
        return backLogAmountMap;
    }

    public void setBackLogAmountMap(HashMap<String, BackLogAmountDetails> backLogAmountMap) {
        this.backLogAmountMap = backLogAmountMap;
    }

    @Override
    public String toString() {
        return "BackLogAmountTotalDetails [totalBillAmount=" + totalBillAmount + ", totalFinancingAmount="
                + totalFinancingAmount + ", backLogAmountMap=" + backLogAmountMap + ", orderAmount=" + orderAmount
                + ", completedOrderAmount=" + completedOrderAmount + "]";
    }

}
