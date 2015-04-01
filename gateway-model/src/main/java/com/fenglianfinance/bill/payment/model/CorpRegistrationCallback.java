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
public class CorpRegistrationCallback implements Serializable {


    private String cmdId;//" UserRegister",
    private String respCode;//"000",
    private String respDesc;//"成功",
    private String sysId;//"1234567890",
    private String usrId;//"ycy_jacktan1",
    private String usrCustId;//"6000060000751360",
    private String trxId;//"447846261970917029",
    private String idType;//"00",
    private String auditStat;//":"T",
    private String auditDesc;//":"",
    private String openBankId;//":"CMB",
    private String cardId;//":"1234567890",
    private String usrName;//"测试",
    private String respExt;//

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

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getAuditStat() {
        return auditStat;
    }

    public void setAuditStat(String auditStat) {
        this.auditStat = auditStat;
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc;
    }

    public String getOpenBankId() {
        return openBankId;
    }

    public void setOpenBankId(String openBankId) {
        this.openBankId = openBankId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getRespExt() {
        return respExt;
    }

    public void setRespExt(String respExt) {
        this.respExt = respExt;
    }
  

    @Override
    public String toString() {
        return "CorpRegistrationInfo{"
                + "cmdId=" + cmdId 
                + ", respCode=" + respCode 
                + ", respDesc=" + respDesc
                + ", sysId=" + sysId 
                + ", usrId=" + usrId 
                + ", usrCustId=" + usrCustId 
                + ", trxId=" + trxId 
                + ", idType=" + idType 
                + ", auditStat=" + auditStat 
                + ", auditDesc=" + auditDesc 
                + ", openBankId=" + openBankId 
                + ", cardId=" + cardId 
                + ", usrName=" + usrName 
                + ", respExt=" + respExt + '}';
    }

}
