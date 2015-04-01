/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.domain;

import java.io.Serializable;

/**
 *
 * @author hansy
 */
public class GlobalConfig implements Serializable {

    private String fee;
    private String maxTenderRate;
    private String borrowerRate;
    private String merchantCustId;

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getMaxTenderRate() {
        return maxTenderRate;
    }

    public void setMaxTenderRate(String maxTenderRate) {
        this.maxTenderRate = maxTenderRate;
    }

    public String getBorrowerRate() {
        return borrowerRate;
    }

    public void setBorrowerRate(String borrowerRate) {
        this.borrowerRate = borrowerRate;
    }

    public String getMerchantCustId() {
        return merchantCustId;
    }

    public void setMerchantCustId(String merchantCustId) {
        this.merchantCustId = merchantCustId;
    }

    @Override
    public String toString() {
        return "GlobalConfig{" + "fee=" + fee + ", maxTenderRate=" + maxTenderRate + ", borrowerRate=" + borrowerRate + ", merchantCustId=" + merchantCustId + '}';
    }

}
