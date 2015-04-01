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
 * @author hansy
 */
public class ImgCaptchaRequest implements Serializable {

    @NotNull
    @NotEmpty
    private String key;
    
    @NotNull
    @NotEmpty
    private String responseValue;

    public String getKey() {
        return key;
    }

    public String getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(String responseValue) {
        this.responseValue = responseValue;
    }

    @Override
    public String toString() {
        return "ImgCaptchaResponse{" + "key=" + key + ", responseValue=" + responseValue + '}';
    }

}
