/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.model;

import java.io.Serializable;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
public class GlobalConfigDetails implements Serializable {

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

}
