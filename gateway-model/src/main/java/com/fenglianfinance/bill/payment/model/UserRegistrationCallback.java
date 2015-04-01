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
public class UserRegistrationCallback implements Serializable {

    private String cmdId;//" UserRegister",
    private String respCode;//"000",
    private String respDesc;//"成功",
    private String sysId;//"1234567890",
    private String usrId;//"ycy_jacktan1",
    private String usrCustId;//"6000060000751360",
    private String trxId;//"447846261970917029",
    private String idType;//"00",
    private String idNo;//"123456789",
    private String usrMp;//"12345678901",
    private String usrEmail;//"test@qq.com",
    private String usrName;//"测试",
//    private String instuCode;//"12345678",
//    private String busiCode;//"123456",
//    private String taxCode;//"1234"


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

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getUsrMp() {
        return usrMp;
    }

    public void setUsrMp(String usrMp) {
        this.usrMp = usrMp;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

//    public String getInstuCode() {
//        return instuCode;
//    }
//
//    public void setInstuCode(String instuCode) {
//        this.instuCode = instuCode;
//    }
//
//    public String getBusiCode() {
//        return busiCode;
//    }
//
//    public void setBusiCode(String busiCode) {
//        this.busiCode = busiCode;
//    }
//
//    public String getTaxCode() {
//        return taxCode;
//    }
//
//    public void setTaxCode(String taxCode) {
//        this.taxCode = taxCode;
//    }

    @Override
    public String toString() {
        return "GWResgiteration{" + "cmdId=" + cmdId 
                + ", respCode=" + respCode 
                + ", respDesc=" + respDesc
                + ", sysId=" + sysId 
                + ", usrId=" + usrId 
                + ", usrCustId=" + usrCustId 
                + ", trxId=" + trxId 
                + ", idType=" + idType 
                + ", idNo=" + idNo 
                + ", usrMp=" + usrMp 
                + ", usrEmail=" + usrEmail 
                + ", usrName=" + usrName 
//                + ", instuCode=" + instuCode
//                + ", busiCode=" + busiCode 
//                + ", taxCode=" + taxCode 
                + '}';
    }

}
