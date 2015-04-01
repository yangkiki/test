/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hansy
 */
public class GatewayResponseForm implements Serializable {

    private String respCode;

    private String respDesc;


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

    @Override
    public String toString() {
        return "GatewayResponseForm{" +
                "respCode='" + respCode + '\'' +
                ", respDesc='" + respDesc + '\'' +
                '}';
    }
}
