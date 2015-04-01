/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.repository;

import com.fenglianfinance.bill.model.LabelValueBean;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hantsy
 */
public class CityRepositoryImpl implements CityRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<LabelValueBean> filterByKeyword(String q) {
        return em.createQuery("select new com.fenglianfinance.bill.model.LabelValueBean(c.name, CONCAT(c.name, ', ', c.province.name, ', ', c.province.country.name)) from City c where c.name like :name or c.province.name like :name or c.province.country.name like :name group by c.name", LabelValueBean.class)
            .setParameter("name", "%" + q + "%")
            .setMaxResults(10)
            .getResultList();
    }

}
