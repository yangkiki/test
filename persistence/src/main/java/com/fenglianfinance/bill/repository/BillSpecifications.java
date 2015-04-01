package com.fenglianfinance.bill.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fenglianfinance.bill.domain.BackLogItem;
import com.fenglianfinance.bill.domain.BackLogItemSearchCriteria;
import com.fenglianfinance.bill.domain.BackLogItem_;
import com.fenglianfinance.bill.domain.Bank;
import com.fenglianfinance.bill.domain.Bank_;
import com.fenglianfinance.bill.domain.Bill;
import com.fenglianfinance.bill.domain.BillSearchCriteria;
import com.fenglianfinance.bill.domain.Bill_;
import com.fenglianfinance.bill.domain.Enterprise;
import com.fenglianfinance.bill.domain.Enterprise_;

/**
 *
 * @author gao
 *
 */
public class BillSpecifications {

    public static Specification<Bill> filterBillBySerialNumber(BillSearchCriteria criteria) {

        String active = criteria.getActive();
        String status = criteria.getStatus();
        BigDecimal denominationStart = criteria.getDenominationStart();
        BigDecimal denominationEnd = criteria.getDenominationEnd();
        LocalDate start = criteria.getStart();
        LocalDate end = criteria.getEnd();
        String keyword = criteria.getKeyword();
        String poolStatus = criteria.getPoolStatus();

        return new Specification<Bill>() {
            @Override
            public Predicate toPredicate(Root<Bill> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Join<Bill, Bank> bankJoin = root.join(Bill_.acceptingBank);
                Join<Bill, Enterprise> enterpriseJoin = root.join(Bill_.enterprise);

                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.hasText(keyword)) {
                    predicates.add(cb.or(cb.like(bankJoin.get(Bank_.name), "%" + keyword + "%"),
                            cb.like(enterpriseJoin.get(Enterprise_.name), "%" + keyword + "%")));
                }
                if (StringUtils.hasText(active)) {
                    predicates.add(cb.equal(root.get(Bill_.active), Boolean.valueOf(active)));
                }
                if (StringUtils.hasText(status)) {
                    predicates.add(cb.equal(root.get(Bill_.status), Bill.Status.valueOf(status)));
                }

                if (start != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get(Bill_.expirationDate), start));
                }

                if (end != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get(Bill_.expirationDate), end));
                }

                if (denominationStart != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get(Bill_.denomination), denominationStart));
                }

                if (denominationEnd != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get(Bill_.denomination), denominationEnd));
                }
                if (StringUtils.hasText(poolStatus)) {
                    List<Predicate> subQueryPredicates = new ArrayList<Predicate>();
                    Subquery<BackLogItem> subquery = query.subquery(BackLogItem.class);
                    Root<BackLogItem> backLogItemRoot = subquery.from(BackLogItem.class);
                    subquery.select(backLogItemRoot);

                    subQueryPredicates.add(cb.equal(root.get(Bill_.id), backLogItemRoot.get(BackLogItem_.bill)));
                    subquery.where(subQueryPredicates.toArray(new Predicate[] {}));

                    if ("EXIST".equals(poolStatus)) {
                        predicates.add(cb.exists(subquery));
                    } else if ("NOTEXIST".equals(poolStatus)) {
                        predicates.add(cb.not(cb.exists(subquery)));
                    }

                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    @Deprecated
    public static Specification<Bill> filterBillByNotExistInBacklogItem(final String serialNumber,
            final List<Long> billIds) {

        return new Specification<Bill>() {
            @Override
            public Predicate toPredicate(Root<Bill> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.hasText(serialNumber)) {
                    predicates.add(cb.like(root.get(Bill_.serialNumber), "%" + serialNumber + "%"));
                }

                predicates.add(root.get(Bill_.id).in(billIds));

                predicates.add(cb.equal(root.get(Bill_.status), Bill.Status.APPROVED));

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

//    public static Specification<BackLogItem> filterUnassignedBackLogItemByType(final String type,
//            final String serialNumber) {
//
//        return new Specification<BackLogItem>() {
//            @Override
//            public Predicate toPredicate(Root<BackLogItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//
//                Join<BackLogItem, Bill> billJoin = root.join(BackLogItem_.bill);
//
//                List<Predicate> predicates = new ArrayList<>();
//                if (StringUtils.hasText(type)) {
//
//                    predicates.add(cb.equal(root.get(BackLogItem_.type), BackLogItem.Type.valueOf(type)));
//                }
//                if (StringUtils.hasText(serialNumber)) {
//                    predicates.add(cb.like(billJoin.get(Bill_.serialNumber), "%" + serialNumber + "%"));
//                }
//
//                predicates.add(cb.equal(root.get(BackLogItem_.status), BackLogItem.Status.UNASSIGNED));
//
//                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        };
//    }

    public static Specification<BackLogItem> filterBackLogItemByCriteria(BackLogItemSearchCriteria criteria) {

        String serialNumber = criteria.getSerialNumber();

        String type = criteria.getType();

        String status = criteria.getStatus();
        String active = criteria.getActive();

        return new Specification<BackLogItem>() {
            @Override
            public Predicate toPredicate(Root<BackLogItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Join<BackLogItem, Bill> billJoin = root.join(BackLogItem_.bill);

                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.hasText(serialNumber)) {
                    predicates.add(cb.like(billJoin.get(Bill_.serialNumber), "%" + serialNumber + "%"));
                }
                if (StringUtils.hasText(active)) {
                    predicates.add(cb.equal(billJoin.get(Bill_.active), Boolean.valueOf(active)));
                }
                if (StringUtils.hasText(type)) {

                    predicates.add(cb.equal(root.get(BackLogItem_.type), BackLogItem.Type.valueOf(type)));
                }
                if (StringUtils.hasText(status)) {
                    predicates.add(cb.equal(root.get(BackLogItem_.status), BackLogItem.Status.valueOf(status)));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

}
