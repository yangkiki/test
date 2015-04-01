/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author hansy
 */
public class AcctTransactionDetails implements Serializable {

    private String ordId;
    private LocalDate ordDate;
    private AcctTransactionType type;
    private AcctTransactionStatus status;

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

    public AcctTransactionStatus getStatus() {
        return status;
    }

    public void setStatus(AcctTransactionStatus status) {
        this.status = status;
    }
}
