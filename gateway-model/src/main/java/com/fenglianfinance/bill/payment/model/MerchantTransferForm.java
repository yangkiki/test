/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hansy
 */
public class MerchantTransferForm implements Serializable {


  private String ordId;
  private String outCustId;
  private String outAcctId;
  private BigDecimal transAmt;
  private String inCustId;
  private String inAcctId;

  public String getOrdId() {
    return ordId;
  }

  public void setOrdId(String ordId) {
    this.ordId = ordId;
  }

  public String getOutCustId() {
    return outCustId;
  }

  public void setOutCustId(String outCustId) {
    this.outCustId = outCustId;
  }

  public String getOutAcctId() {
    return outAcctId;
  }

  public void setOutAcctId(String outAcctId) {
    this.outAcctId = outAcctId;
  }

  public BigDecimal getTransAmt() {
    return transAmt;
  }

  public void setTransAmt(BigDecimal transAmt) {
    this.transAmt = transAmt;
  }

  public String getInCustId() {
    return inCustId;
  }

  public void setInCustId(String inCustId) {
    this.inCustId = inCustId;
  }

  public String getInAcctId() {
    return inAcctId;
  }

  public void setInAcctId(String inAcctId) {
    this.inAcctId = inAcctId;
  }

  @Override
  public String toString() {
    return "MerchantTransferForm{" +
           "ordId='" + ordId + '\'' +
           ", outCustId='" + outCustId + '\'' +
           ", outAcctId='" + outAcctId + '\'' +
           ", transAmt=" + transAmt +
           ", inCustId='" + inCustId + '\'' +
           ", inAcctId='" + inAcctId + '\'' +
           '}';
  }
}
