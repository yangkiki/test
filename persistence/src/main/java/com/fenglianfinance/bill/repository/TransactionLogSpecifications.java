package com.fenglianfinance.bill.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fenglianfinance.bill.domain.TransactionLog;
import com.fenglianfinance.bill.domain.TransactionLog_;
import com.fenglianfinance.bill.domain.TransactionType;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.domain.UserAccount_;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.criteria.JoinType;

/**
 *
 * @author hantsy
 *
 */
public class TransactionLogSpecifications {

    public static Specification<TransactionLog> filterByCriteria(
            final Long id,//
            final LocalDateTime fromDateTime//
    ) {

        return (Root<TransactionLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                Join<TransactionLog, UserAccount> fromJoin = root.join(TransactionLog_.from, JoinType.LEFT);
                Join<TransactionLog, UserAccount> toJoin = root.join(TransactionLog_.to, JoinType.LEFT);

                predicates.add(
                        cb.or(
                                cb.equal(fromJoin.get(UserAccount_.id), id),
                                cb.equal(toJoin.get(UserAccount_.id), id)
                        ));
            }

            if (fromDateTime != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(TransactionLog_.createdDate), fromDateTime));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<TransactionLog> filterByTypeAndDateRange(
            final String status,//
            final String type,//
            final LocalDate checkDate
    ) {

        return (Root<TransactionLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(status)) {
                predicates.add(cb.equal(root.get(TransactionLog_.queryResult), TransactionLog.QueryResult.valueOf(status)));
            }

            if (StringUtils.hasText(type)) {
                predicates.add(cb.equal(root.get(TransactionLog_.type), TransactionType.valueOf(type)));
            }

            if (checkDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(TransactionLog_.checkDate), checkDate));
            }


            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<TransactionLog> filterTransferLogsByTypeAndDateRange(
            final String fromAcctId,//
            final TransactionType type,//
            final LocalDate beginDate,//
            final LocalDate endDate//
    ) {

        return (Root<TransactionLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(fromAcctId)) {
                predicates.add(cb.equal(root.get(TransactionLog_.fromAcctId), fromAcctId));
            }

            if (type != null) {
                predicates.add(cb.equal(root.get(TransactionLog_.type), type));
            }

            if (beginDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(TransactionLog_.transactedDate), beginDate));
            }

            if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get(TransactionLog_.transactedDate), endDate));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
