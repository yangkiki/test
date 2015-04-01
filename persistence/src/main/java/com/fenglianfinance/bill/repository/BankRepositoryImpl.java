/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.repository;

import com.fenglianfinance.bill.domain.Bank;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hantsy
 */
public class BankRepositoryImpl implements BankRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Bank> filterByKeyword(String q) {
        return em.createQuery("select b from Bank b where b.name like :name", Bank.class)
            .setParameter("name", "%" + q + "%")
            .setMaxResults(10)
            .getResultList();
    }

}
