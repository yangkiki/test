/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import com.fenglianfinance.bill.domain.support.AuditableEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hansy
 */
//@Entity
//@Table(name = "product_stocks")
public class ProductStock extends AuditableEntity<UserAccount, Long> {

//    @ManyToOne()
//    @JoinColumn(name = "product_id")
    private Product product;

//    @ManyToMany()
//    @JoinTable(
//            name = "instrument_in_stock", 
//            joinColumns = {
//                @JoinColumn(name = "instrument_id")
//            }, 
//            inverseJoinColumns = {
//                @JoinColumn(name = "stock_id")
//            }
//    )
    private List<Bill> intruments = new ArrayList<>();

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Bill> getIntruments() {
        return intruments;
    }

    public void setIntruments(List<Bill> intruments) {
        this.intruments = intruments;
    }

}
