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
public class TransactionQueryResult implements Serializable{

    private String cmdId;//:" Cash",
    
    private String respCode;//:"000",
    
    private String respDesc;//:"成功",
    
    private String ordId; // :"637362872983729723",
    
    private String ordDate;//:"20150118",
    
    private String queryTransType;//:" CASH",
    
    private String transStat;//汇付交易状态     S-成功  F-失败  I-初始  P-部分成功  H-经办  R-拒绝。

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

    public String getQueryTransType() {
        return queryTransType;
    }

    public void setQueryTransType(String queryTransType) {
        this.queryTransType = queryTransType;
    }

    public String getTransStat() {
        return transStat;
    }

    public void setTransStat(String transStat) {
        this.transStat = transStat;
    }
    
}
