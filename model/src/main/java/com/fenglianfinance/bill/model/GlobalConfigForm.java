/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author hansy
 */
public class GlobalConfigForm implements Serializable {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[\\d]*(\\.[\\d]{2})$", message = "数字格式错误，小数点后面应该保留两位，如 1.00 等")
    private String fee;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[\\d]*(\\.[\\d]{2})$", message = "数字格式错误，小数点后面应该保留两位，如 1.00 等")
    private String maxTenderRate;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[\\d]*(\\.[\\d]{2})$", message = "数字格式错误，小数点后面应该保留两位，如 1.00 等")
    private String borrowerRate;

    @NotNull
    @NotEmpty
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
        return "GlobalConfigForm{" + "fee=" + fee + ", maxTenderRate=" + maxTenderRate + ", borrowerRate=" + borrowerRate + ", merchantCustId=" + merchantCustId + '}';
    }

}
