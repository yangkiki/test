/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hansy
 */
public class AcctTransactionRequest implements Serializable {

    @NotNull
    private String ordId;
    
    @NotNull
    private LocalDate ordDate;
    
    @NotNull
    private AcctTransactionType type;

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public LocalDate getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(LocalDate ordDate) {
        this.ordDate = ordDate;
    }

    public AcctTransactionType getType() {
        return type;
    }

    public void setType(AcctTransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AcctTransactionRequest{" + "ordId=" + ordId + ", ordDate=" + ordDate + ", type=" + type + '}';
    }

}
