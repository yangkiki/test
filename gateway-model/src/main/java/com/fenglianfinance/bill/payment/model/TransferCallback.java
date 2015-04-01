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
public class TransferCallback implements Serializable {

//{
//	"cmdId":"Transfer",
//	"respCode":"000",
//	"respDesc":"成功",
//	"ordId":"737273898378721",
//	"outCustId":"6000060000736315",
//	"outAcctId":"MDT000002",
//	"transAmt":"10.00",
//	"inCustId":"6000060000775816",
//	"inAcctId":"MDT000001"
//}
    private String cmdId;//"cmdId":"InitiativeTender",

    private String respCode;//"respCode":"000",

    private String respDesc;//"respDesc":"成功",

    private String ordId;
    private String ordDate;

    private String outCustId;

    private String outAcctId;

    private String transAmt;

    private String inCustId;

    private String inAcctId;

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

    public String getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
    }

    @Override
    public String toString() {
        return "TransferCallback{" + "cmdId=" + cmdId + ", respCode=" + respCode + ", respDesc=" + respDesc + ", ordId=" + ordId + ", ordDate=" + ordDate + ", outCustId=" + outCustId + ", outAcctId=" + outAcctId + ", transAmt=" + transAmt + ", inCustId=" + inCustId + ", inAcctId=" + inAcctId + '}';
    }

}
