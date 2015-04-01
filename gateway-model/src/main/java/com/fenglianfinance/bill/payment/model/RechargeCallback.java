/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;

/**
 *
 * @author hansy
 */
public class RechargeCallback implements Serializable {

    private String cmdId;// " NetSave",
    private String respCode;// "000",
    private String respDesc;// "成功",
    private String usrCustId;// "usrCustId":"6000060000751360",
    private String ordId;// "ordId":"1234567890",
    private String ordDate;// "ordDate":"20150120 ",
    private String transAmt;// "transAmt":"2.00",
    private String trxId;// "trxId":"447846261970917029",
    private String gateBusiId;// "gateBusiId":"B2C",
    private String gateBankId;// "gateBankId":"CIB",
    private String feeAmt;// "feeAmt":"0.01",
    private String feeCustId;// "feeCustId":"6000060000736315",
    private String feeAcctId;// "feeAcctId":"MT00000212"

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

    public String getUsrCustId() {
        return usrCustId;
    }

    public void setUsrCustId(String usrCustId) {
        this.usrCustId = usrCustId;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
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

    public String getGateBusiId() {
        return gateBusiId;
    }

    public void setGateBusiId(String gateBusiId) {
        this.gateBusiId = gateBusiId;
    }

    public String getGateBankId() {
        return gateBankId;
    }

    public void setGateBankId(String gateBankId) {
        this.gateBankId = gateBankId;
    }

    public String getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(String feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getFeeCustId() {
        return feeCustId;
    }

    public void setFeeCustId(String feeCustId) {
        this.feeCustId = feeCustId;
    }

    public String getFeeAcctId() {
        return feeAcctId;
    }

    public void setFeeAcctId(String feeAcctId) {
        this.feeAcctId = feeAcctId;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    @Override
    public String toString() {
        return "RechargeCallback [cmdId=" + cmdId + ", respCode=" + respCode + ", respDesc=" + respDesc
                + ", usrCustId=" + usrCustId + ", ordId=" + ordId + ", ordDate=" + ordDate + ", transAmt=" + transAmt
                + ", trxId=" + trxId + ", gateBusiId=" + gateBusiId + ", gateBankId=" + gateBankId + ", feeAmt="
                + feeAmt + ", feeCustId=" + feeCustId + ", feeAcctId=" + feeAcctId + "]";
    }

}
