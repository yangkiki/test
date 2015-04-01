/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.repository;

import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.model.OrderStatisticsItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hansy
 */
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Map<String, BigDecimal> sumOrdersByUser(Long id) {
        List<Map> result = em
                .createQuery("select new map( o.product.type as type, sum(o.amount) as amount ) from PurchaseOrder o where o.user.id=:userid  and o.status in ('PAID', 'INTEREST_ACCRUED', 'IN_REPAYMENT', 'REPAYMENT_COMPLETED') group by o.product.type")
                .setParameter("userid", id)
                .getResultList();

        Map<String, BigDecimal> items = new HashMap<>();

        for (Map map : result) {
            items.put(((Product.Type) map.get("type")).name(), (BigDecimal) map.get("amount"));
        }

        return items;
    }

    @Override
    public Map<String, BigDecimal> sumActiveOrdersByUser(Long id) {
        List<Map> result = em
                .createQuery("select new map( o.product.type as type, sum(o.amount) as amount ) from PurchaseOrder o where  o.user.id=:userid  and o.status in ('PAID', 'INTEREST_ACCRUED') group by o.product.type")
                .setParameter("userid", id)
                .getResultList();

        Map<String, BigDecimal> items = new HashMap<>();

        for (Map map : result) {
            items.put(((Product.Type) map.get("type")).name(), (BigDecimal) map.get("amount"));
        }

        return items;
    }

    @Override
    public Map<String, BigDecimal> sumOrderInterestsByUser(Long id) {
        List<Map> result = em
                .createQuery("select new map( o.product.type as type, sum(o.accruedInterestAmount) as amount ) from PurchaseOrder o where o.user.id=:userid  and o.status in ('PAID', 'INTEREST_ACCRUED', 'IN_REPAYMENT', 'REPAYMENT_COMPLETED') group by o.product.type")
                .setParameter("userid", id)
                .getResultList();

        Map<String, BigDecimal> items = new HashMap<>();

        for (Map map : result) {
            items.put(((Product.Type) map.get("type")).name(), (BigDecimal) map.get("amount"));
        }

        return items;
    }

    @Override
    public Map<String, BigDecimal> sumActiveOrderInterestsByUser(Long id) {
        List<Map> result = em
                .createQuery("select new map( o.product.type as type, sum(o.accruedInterestAmount) as amount ) from PurchaseOrder o where  o.user.id=:userid  and o.status in ('PAID', 'INTEREST_ACCRUED') group by o.product.type")
                .setParameter("userid", id)
                .getResultList();

        Map<String, BigDecimal> items = new HashMap<>();

        for (Map map : result) {
            items.put(((Product.Type) map.get("type")).name(), (BigDecimal) map.get("amount"));
        }

        return items;
    }

}
