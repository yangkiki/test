package com.fenglianfinance.bill.report.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CurrentStockDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String enterpriseName;

    public CurrentStockDetails() {
        super();
    }


    public String getEnterpriseName() {
        return enterpriseName;
    }


    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }


    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    private BigDecimal totalAmount;

    public CurrentStockDetails(String enterpriseName, BigDecimal totalAmount) {
        super();
        this.enterpriseName = enterpriseName;
        this.totalAmount = totalAmount;
    }

    

}
