package com.fenglianfinance.bill.repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fenglianfinance.bill.domain.Product;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> findProductOrderByEnterpriseAndType(LocalDate d) {

        return em.createQuery(
                        "from Product p where p.createdDate BETWEEN :startDate and :endDate order by p.enterprise.name,p.type desc",
                        Product.class)
                        .setParameter("startDate", d.atStartOfDay())
                        .setParameter("endDate", d.plus(1, ChronoUnit.DAYS).atStartOfDay())
                        .getResultList();
    }
}
