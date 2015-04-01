/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hantsy<hantsy@gmail.com><hantsy<hantsy@gmail.com>@gmail.com>
 */
public class OrderStatistics implements Serializable {

    private BigDecimal totalAmount;
    private BigDecimal totalActiveAmount;

    private BigDecimal totalInterestAmount;
    private BigDecimal totalActiveInterestAmount;

    private Map<String, BigDecimal> items = new HashMap<>();

    private Map<String, BigDecimal> activeItems = new HashMap<>();

    private Map<String, BigDecimal> interestItems = new HashMap<>();

    private Map<String, BigDecimal> activeInterestItems = new HashMap<>();

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalActiveAmount() {
        return totalActiveAmount;
    }

    public void setTotalActiveAmount(BigDecimal totalActiveAmount) {
        this.totalActiveAmount = totalActiveAmount;
    }

    public BigDecimal getTotalInterestAmount() {
        return totalInterestAmount;
    }

    public void setTotalInterestAmount(BigDecimal totalInterestAmount) {
        this.totalInterestAmount = totalInterestAmount;
    }

    public BigDecimal getTotalActiveInterestAmount() {
        return totalActiveInterestAmount;
    }

    public void setTotalActiveInterestAmount(BigDecimal totalActiveInterestAmount) {
        this.totalActiveInterestAmount = totalActiveInterestAmount;
    }

    public Map<String, BigDecimal> getItems() {
        return items;
    }

    public void setItems(Map<String, BigDecimal> items) {
        this.items = items;
    }

    public Map<String, BigDecimal> getActiveItems() {
        return activeItems;
    }

    public void setActiveItems(Map<String, BigDecimal> activeItems) {
        this.activeItems = activeItems;
    }

    public Map<String, BigDecimal> getInterestItems() {
        return interestItems;
    }

    public void setInterestItems(Map<String, BigDecimal> interestItems) {
        this.interestItems = interestItems;
    }

    public Map<String, BigDecimal> getActiveInterestItems() {
        return activeInterestItems;
    }

    public void setActiveInterestItems(Map<String, BigDecimal> activeInterestItems) {
        this.activeInterestItems = activeInterestItems;
    }

    @Override
    public String toString() {
        return "OrderStatistics{"
                + "totalAmount=" + totalAmount
                + ", totalActiveAmount=" + totalActiveAmount 
                + ", totalInterestAmount=" + totalInterestAmount 
                + ", totalActiveInterestAmount=" + totalActiveInterestAmount 
                + ", items=" + items 
                + ", activeItems=" + activeItems 
                + ", interestItems=" + interestItems 
                + ", activeInterestItems=" + activeInterestItems + '}';
    }

}
