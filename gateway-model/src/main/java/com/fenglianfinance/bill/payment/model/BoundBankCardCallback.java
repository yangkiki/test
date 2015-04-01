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
public class BoundBankCardCallback implements Serializable {

    private String cmdId;// UserBindCard",
    private String respCode;//"000",
    private String respDesc;//"成功",
    private String usrCustId;//" 6000060000756891",
    private String trxId;//"447846261970917029",
    private String openAcctId;//"637362872983729723",
    private String openBankId;//"12345678",

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

    public String getOpenAcctId() {
        return openAcctId;
    }

    public void setOpenAcctId(String OpenAcctId) {
        this.openAcctId = OpenAcctId;
    }

    public String getOpenBankId() {
        return openBankId;
    }

    public void setOpenBankId(String OpenBankId) {
        this.openBankId = OpenBankId;
    }

    @Override
    public String toString() {
        return "BoundBankCardInfo{"
                + "cmdId=" + cmdId
                + ", respCode=" + respCode
                + ", respDesc=" + respDesc
                + ", usrCustId=" + usrCustId
                + ", trxId=" + trxId
                + ", OpenAccId=" + openAcctId
                + ", OpenBankId=" + openBankId + '}';
    }

}
