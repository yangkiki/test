package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author admin
 *
 */
public class LargePayableAmountForm implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private BigDecimal total;
    private BigDecimal eightyPercent;
    private LocalDate eightyPercentDue;
    private BigDecimal twentyPercent;
    private LocalDate twentyPercentDue;

    public LargePayableAmountForm() {}

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getEightyPercent() {
        return eightyPercent;
    }

    public void setEightyPercent(BigDecimal eightyPercent) {
        this.eightyPercent = eightyPercent;
    }

    public BigDecimal getTwentyPercent() {
        return twentyPercent;
    }

    public void setTwentyPercent(BigDecimal twentyPercent) {
        this.twentyPercent = twentyPercent;
    }

    public LocalDate getEightyPercentDue() {
        return eightyPercentDue;
    }

    public void setEightyPercentDue(LocalDate eightyPercentDue) {
        this.eightyPercentDue = eightyPercentDue;
    }

    public LocalDate getTwentyPercentDue() {
        return twentyPercentDue;
    }

    public void setTwentyPercentDue(LocalDate twentyPercentDue) {
        this.twentyPercentDue = twentyPercentDue;
    }

}
