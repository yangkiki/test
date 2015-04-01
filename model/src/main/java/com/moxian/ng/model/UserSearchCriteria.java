/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
public class UserSearchCriteria implements Serializable {

    private BigDecimal min;
    private BigDecimal max;
    private String q;
    private String locked;
    private String active;

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }



    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserSearchCriteria{" + "min=" + min + ", max=" + max + ", q=" + q + ", locked=" + locked + ", active=" + active + '}';
    }



}
