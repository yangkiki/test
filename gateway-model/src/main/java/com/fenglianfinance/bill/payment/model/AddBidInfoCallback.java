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
public class AddBidInfoCallback implements Serializable {

//    {
//	"cmdId":"AddBidInfo",
//	"respCode":"000",
//	"respDesc":"成功",
//	"proId":"10000088223",
//	"borrCustId":"6000060000775816",
//	"borrTotAmt":"10.00",
//	"guarCompId":"null",
//	"guarAmt":"null",
//	"proArea":"1100",
//	"respExt":"null"
//}
    private String cmdId;//"cmdId":"InitiativeTender",

    private String respCode;//"respCode":"000",

    private String respDesc;//"respDesc":"成功",

    private String proId;

    private String borrCustId;

    private String borrTotAmt;

    private String guarCompId;

    private String guarAmt;

    private String proArea;

    private String respExt;

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

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getBorrCustId() {
        return borrCustId;
    }

    public void setBorrCustId(String borrCustId) {
        this.borrCustId = borrCustId;
    }

    public String getBorrTotAmt() {
        return borrTotAmt;
    }

    public void setBorrTotAmt(String borrTotAmt) {
        this.borrTotAmt = borrTotAmt;
    }

    public String getGuarCompId() {
        return guarCompId;
    }

    public void setGuarCompId(String guarCompId) {
        this.guarCompId = guarCompId;
    }

    public String getGuarAmt() {
        return guarAmt;
    }

    public void setGuarAmt(String guarAmt) {
        this.guarAmt = guarAmt;
    }

    public String getProArea() {
        return proArea;
    }

    public void setProArea(String proArea) {
        this.proArea = proArea;
    }

    public String getRespExt() {
        return respExt;
    }

    public void setRespExt(String respExt) {
        this.respExt = respExt;
    }

    @Override
    public String toString() {
        return "AddBidInfoCallback{" + "cmdId=" + cmdId + ", respCode=" + respCode + ", respDesc=" + respDesc + ", proId=" + proId + ", borrCustId=" + borrCustId + ", borrTotAmt=" + borrTotAmt + ", guarCompId=" + guarCompId + ", guarAmt=" + guarAmt + ", proArea=" + proArea + ", respExt=" + respExt + '}';
    }

}
