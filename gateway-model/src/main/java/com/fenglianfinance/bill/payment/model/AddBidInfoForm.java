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
public class AddBidInfoForm implements Serializable {


  private Long productId;

  private String retType;

  private String proArea="3100";

  private  String  redirectUrl;


  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getRetType() {
    return retType;
  }

  public void setRetType(String retType) {
    this.retType = retType;
  }

  public String getProArea() {
    return proArea;
  }

  public void setProArea(String proArea) {
    this.proArea = proArea;
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }

  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }

  @Override
  public String toString() {
    return "AddBidInfoForm{" +
           "productId=" + productId +
           ", retType='" + retType + '\'' +
           ", proArea='" + proArea + '\'' +
           ", redirectUrl='" + redirectUrl + '\'' +
           '}';
  }
}
