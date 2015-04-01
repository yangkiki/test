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
public class PaymentCallback implements Serializable {

    private String cmdId;//"cmdId":"InitiativeTender",

    private String respCode;//"respCode":"000",

    private String respDesc;//"respDesc":"成功",

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

    private String feeObjFlag;//"feeObjFlag":"I",

    private String unFreezeOrdId;//"unFreezeOrdId":"6000060000736315",

    private String freezeTrxId;//"freezeTrxId":"MT00000212",

    private String respExt;//"respExt":""

    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

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

    public String getUnFreezeOrdId() {
        return unFreezeOrdId;
    }

    public void setUnFreezeOrdId(String unFreezeOrdId) {
        this.unFreezeOrdId = unFreezeOrdId;
    }

    public String getFreezeTrxId() {
        return freezeTrxId;
    }

    public void setFreezeTrxId(String freezeTrxId) {
        this.freezeTrxId = freezeTrxId;
    }

    public String getRespExt() {
        return respExt;
    }

    public void setRespExt(String respExt) {
        this.respExt = respExt;
    }

    @Override
    public String toString() {
        return "PaymentCallback{" 
                + "cmdId=" + cmdId 
                + ", respCode=" + respCode 
                + ", respDesc=" + respDesc 
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
                + ", feeObjFlag=" + feeObjFlag 
                + ", unFreezeOrdId=" + unFreezeOrdId 
                + ", freezeTrxId=" + freezeTrxId 
                + ", respExt=" + respExt 
                + '}';
    }

}
