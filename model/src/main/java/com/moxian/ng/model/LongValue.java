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
public class LongValue implements Serializable {

    private Long result;

    public LongValue() {
    }

    public LongValue(Long result) {
        this.result = result;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LongValue{" + "result=" + result + '}';
    }

}
