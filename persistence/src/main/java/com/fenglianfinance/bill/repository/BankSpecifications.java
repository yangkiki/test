package com.fenglianfinance.bill.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fenglianfinance.bill.domain.Bank;
import com.fenglianfinance.bill.domain.Bank_;

/**
 *
 * @author hantsy
 *
 */
public class BankSpecifications {

    public static Specification<Bank> filterBanksByKeywordAndStatus(final String keyword, final boolean status) {
        return (Root<Bank> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                predicates.add(cb.like(root.get(Bank_.name), "%" + keyword + "%"));
            }

            predicates.add(cb.equal(root.get(Bank_.active), status));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
