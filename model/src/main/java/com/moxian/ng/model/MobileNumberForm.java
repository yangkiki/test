/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author hantsy<hantsy@gmail.com><hantsy<hantsy@gmail.com>@gmail.com>
 */
public class MobileNumberForm implements Serializable {

    @NotNull
    @NotEmpty
    private String mobileNumber;

    @NotNull
    @NotEmpty
    private String smsCode;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "MobileNumberForm{" + "mobileNumber=" + mobileNumber + ", smsCode=" + smsCode + '}';
    }

}
