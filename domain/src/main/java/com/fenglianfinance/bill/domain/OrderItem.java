/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import com.fenglianfinance.bill.domain.support.PersistableEntity;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hantsy
 */
@Entity
@Table(name = "order_items")
public class OrderItem extends PersistableEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal amount = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private PurchaseOrder order;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PurchaseOrder getOrder() {
        return order;
    }

    public void setOrder(PurchaseOrder order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "OrderItem{" + "product=" + product + ", amount=" + amount + ", order=" + order + '}';
    }

}
