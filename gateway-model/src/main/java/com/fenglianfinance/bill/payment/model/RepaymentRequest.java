/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;

/**
 *
 * @author hansy
 */
public class RepaymentRequest implements Serializable {
//    cmdId:" Repayment ",
//	respCode:"000",
//	respDesc:"成功",
//	ordId:"637362872983729723",
//	ordDate:"20150118",
//	outCustId:" 6000060000757435 ",
//	subOrdId:" 637362872983729722",
//	subOrdDate:" 20150118",
//	outAcctId:" 4367424512101212",
//	transAmt:"100.00",
//	fee:"1.00",
//	inCustId:" 60000600007574354",
//	inAcctId:" 4367424512101211",
//	feeObjFlag:"I",

 //   private String cmdId;//"cmdId":"InitiativeTender",

    private String ordId;//"ordId":"6000060000751360",

    private String ordDate;//"ordDate":"1234567890",

    private String outCustId;//"outCustId":"20150120 ",

    private String outAcctId;//"outAcctId":"20150120 ",

    private String transAmt;//"transAmt":"2.00",

    private String fee;//"fee":"0.00",

    private String inCustId;//"inCustId":"447846261970917029",

    private String inAcctId;//"inAcctId":"447846261970917029",

    private String subOrdId;//"subOrdId":"447846261970917029",

    private String subOrdDate;//"subOrdDate":"20150120 ",
    private String feeObjFlag;


    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
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

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
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

    public String getSubOrdId() {
        return subOrdId;
    }

    public void setSubOrdId(String subOrdId) {
        this.subOrdId = subOrdId;
    }

    public String getSubOrdDate() {
        return subOrdDate;
    }

    public void setSubOrdDate(String subOrdDate) {
        this.subOrdDate = subOrdDate;
    }

    public String getFeeObjFlag() {
        return feeObjFlag;
    }

    public void setFeeObjFlag(String feeObjFlag) {
        this.feeObjFlag = feeObjFlag;
    }

    @Override
    public String toString() {
        return "PaymentCallback{"
                + ", ordId=" + ordId
                + ", ordDate=" + ordDate
                + ", outCustId=" + outCustId
                + ", outAcctId=" + outAcctId
                + ", transAmt=" + transAmt
                + ", fee=" + fee
                + ", inCustId=" + inCustId
                + ", inAcctId=" + inAcctId
                + ", subOrdId=" + subOrdId
                + ", subOrdDate=" + subOrdDate
                + '}';
    }

}
