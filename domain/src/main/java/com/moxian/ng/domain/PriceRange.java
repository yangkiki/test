/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Embeddable;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
@Embeddable
public class PriceRange implements Serializable{
    private BigDecimal lowestPrice;
    private BigDecimal highestPrice;

    public PriceRange(BigDecimal lowestPrice, BigDecimal highestPrice) {
        this.lowestPrice = lowestPrice;
        this.highestPrice = highestPrice;
    }

    public PriceRange() {
    }

    public BigDecimal getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(BigDecimal lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public BigDecimal getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(BigDecimal highestPrice) {
        this.highestPrice = highestPrice;
    }

    @Override
    public String toString() {
        return "PriceRange{" + "lowestPrice=" + lowestPrice + ", highestPrice=" + highestPrice + '}';
    }
   
}
