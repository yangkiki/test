/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.model;

import java.io.Serializable;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
public class TokenValue implements Serializable {

    private String token;

    public TokenValue(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenValue{" + "token=" + token + '}';
    }

}
