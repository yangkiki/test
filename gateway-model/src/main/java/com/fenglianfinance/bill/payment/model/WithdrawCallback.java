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
public class WithdrawCallback implements Serializable {

    private String cmdId;//" Cash",
    private String respCode;//"000",
    private String respType;// "Cash"
    private String respDesc;//"成功",
    private String usrCustId;//	"usrCustId":"6000060000751360",
    private String ordId;//	"ordId":"1234567890",
    private String ordDate;//
    private String transAmt;//	"transAmt":"2.00",
    private String trxId;//	"trxId":"447846261970917029",
    private String openAcctId; //openAcctId:" 4367424512101212 ",
    private String openBankId;//openBankId:"CCB",
    private String feeAmt;//	"feeAmt":"0.01",
    private String feeCustId;//	"feeCustId":"6000060000736315",
    private String feeAcctId;//	"feeAcctId":"MT00000212"
    private String servFee; //servFee:"",
    private String servFeeAcctId; //servFeeAcctId:"",

    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType;
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

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
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

    public String getOpenAcctId() {
        return openAcctId;
    }

    public void setOpenAcctId(String openAcctId) {
        this.openAcctId = openAcctId;
    }

    public String getOpenBankId() {
        return openBankId;
    }

    public void setOpenBankId(String openBankId) {
        this.openBankId = openBankId;
    }

    public String getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
    }

    public String getServFee() {
        return servFee;
    }

    public void setServFee(String servFee) {
        this.servFee = servFee;
    }

    public String getServFeeAcctId() {
        return servFeeAcctId;
    }

    public void setServFeeAcctId(String servFeeAcctId) {
        this.servFeeAcctId = servFeeAcctId;
    }
    
    @Override
    public String toString() {
        return "WithdrawInfo{"
                + "cmdId=" + cmdId
                + ", respCode=" + respCode
                + ", respDesc=" + respDesc
                + ", usrCustId=" + usrCustId
                + ", ordId=" + ordId
                + ", transAmt=" + transAmt
                + ", trxId=" + trxId
                + ", openAcctId=" + openAcctId
                + ", openBankId=" + openBankId
                + ", feeAmt=" + feeAmt
                + ", feeCustId=" + feeCustId
                + ", feeAcctId=" + feeAcctId + '}';
    }

}
