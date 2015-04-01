package com.fenglianfinance.bill.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import com.fenglianfinance.bill.domain.TransactionReconciliation_;
import com.fenglianfinance.bill.domain.TransactionReconciliation;
import com.fenglianfinance.bill.domain.TransactionType;
import java.time.LocalDate;
import org.springframework.util.StringUtils;

/**
 *
 * @author hantsy
 *
 */
public class TransactionReconciliationSpecifications {

    public static Specification<TransactionReconciliation> filterByCriteria(
            final String type,//
            final String status,//
            final LocalDate beginDate,//
            final LocalDate endDate//
    ) {

        return (Root<TransactionReconciliation> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(type)) {
                predicates.add(cb.equal(root.get(TransactionReconciliation_.type), TransactionType.valueOf(type)));
            }

            if (StringUtils.hasText(status)) {
                predicates.add(cb.equal(root.get(TransactionReconciliation_.status), TransactionReconciliation.Status.valueOf(status)));
            }

            if (beginDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(TransactionReconciliation_.checkDate), beginDate));
            }

            if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get(TransactionReconciliation_.checkDate), endDate));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
