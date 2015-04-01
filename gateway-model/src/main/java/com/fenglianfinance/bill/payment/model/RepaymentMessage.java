/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author hansy
 */
public class RepaymentMessage implements Serializable {

    private String ordId;
    
    private LocalDate ordDate;

    private BigDecimal amount;
    
    private BigDecimal fee;
    
    private String fromCustId;
    
    private String toCustId;
   
    private String orderSN;

    private String respCode;


    public RepaymentMessage() {
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getFromCustId() {
        return fromCustId;
    }

    public void setFromCustId(String fromCustId) {
        this.fromCustId = fromCustId;
    }

    public String getToCustId() {
        return toCustId;
    }

    public void setToCustId(String toCustId) {
        this.toCustId = toCustId;
    }

    public String getOrderSN() {
        return orderSN;
    }

    public void setOrderSN(String orderSN) {
        this.orderSN = orderSN;
    }



}
