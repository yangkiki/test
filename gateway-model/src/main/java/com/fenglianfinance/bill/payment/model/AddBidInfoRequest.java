/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;

/**
 * @author hansy
 */
public class AddBidInfoRequest implements Serializable {


  private String proId;
  private String borrCustId;
  private String borrTotAmt;
  private String yearRate;
  private String retType;
  private String bidStartDate;
  private String bidEndDate;
  private String retAmt;
  private String retDate;
  private String guarCompId;
  private String guarAmt;
  private String proArea;
  private String reqExt;
  private  String redirectUrl;

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

  public String getYearRate() {
    return yearRate;
  }

  public void setYearRate(String yearRate) {
    this.yearRate = yearRate;
  }

  public String getRetType() {
    return retType;
  }

  public void setRetType(String retType) {
    this.retType = retType;
  }

  public String getBidStartDate() {
    return bidStartDate;
  }

  public void setBidStartDate(String bidStartDate) {
    this.bidStartDate = bidStartDate;
  }

  public String getBidEndDate() {
    return bidEndDate;
  }

  public void setBidEndDate(String bidEndDate) {
    this.bidEndDate = bidEndDate;
  }

  public String getRetAmt() {
    return retAmt;
  }

  public void setRetAmt(String retAmt) {
    this.retAmt = retAmt;
  }

  public String getRetDate() {
    return retDate;
  }

  public void setRetDate(String retDate) {
    this.retDate = retDate;
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

  public String getReqExt() {
    return reqExt;
  }

  public void setReqExt(String reqExt) {
    this.reqExt = reqExt;
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }

  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }

  @Override
  public String toString() {
    return "AddBidInfoRequest{" +
           "proId='" + proId + '\'' +
           ", borrCustId='" + borrCustId + '\'' +
           ", borrTotAmt='" + borrTotAmt + '\'' +
           ", yearRate='" + yearRate + '\'' +
           ", retType='" + retType + '\'' +
           ", bidStartDate='" + bidStartDate + '\'' +
           ", bidEndDate='" + bidEndDate + '\'' +
           ", retAmt='" + retAmt + '\'' +
           ", retDate='" + retDate + '\'' +
           ", guarCompId='" + guarCompId + '\'' +
           ", guarAmt='" + guarAmt + '\'' +
           ", proArea='" + proArea + '\'' +
           ", reqExt='" + reqExt + '\'' +
           ", redirectUrl='" + redirectUrl + '\'' +
           '}';
  }
}
