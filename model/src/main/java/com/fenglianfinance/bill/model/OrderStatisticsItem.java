/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author hansy
 */
public class OrderStatisticsItem implements Serializable {

    private String type;
    private BigDecimal amount;

    public OrderStatisticsItem() {
    }

    public OrderStatisticsItem(String type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderStatItem{" + "type=" + type + ", amount=" + amount + '}';
    }
}
