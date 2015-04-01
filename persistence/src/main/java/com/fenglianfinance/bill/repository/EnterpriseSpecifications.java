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
import com.fenglianfinance.bill.domain.AccountingInfo;
import com.fenglianfinance.bill.domain.AccountingInfo_;
import com.fenglianfinance.bill.domain.Enterprise;
import com.fenglianfinance.bill.domain.Enterprise_;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.domain.UserAccount_;
import com.fenglianfinance.bill.domain.support.EnterpriseCriteria;

public class EnterpriseSpecifications {

    public static Specification<Enterprise> findEnterprisesByName(final String name, final boolean status,
            final String vs) {
        return new Specification<Enterprise>() {

            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.hasText(name)) {
                    predicates.add(cb.or(cb.like(root.get(Enterprise_.name), "%" + name + "%")));
                }

                if (StringUtils.hasText(vs)) {
                    predicates.add(cb.or(cb.equal(root.get(Enterprise_.verifyState),
                            Enterprise.VerifyStatus.valueOf(vs))));
                }

                predicates.add(cb.equal(root.get(Enterprise_.active), status));

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }

    public static Specification<Enterprise> findEnterprisesByAccountingInfo() {
        return new Specification<Enterprise>() {

            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                Join<Enterprise, UserAccount> userAccountJoin = root.join(Enterprise_.userAccount);

                Join<UserAccount, AccountingInfo> AccountingJoin = userAccountJoin.join(UserAccount_.accountingInfo);
                predicates.add(cb.isNotNull(root.get(Enterprise_.userAccount)));
//                predicates.add(cb.isNotNull(userAccountJoin.get(UserAccount_.accountingInfo)));
                predicates.add(cb.isNotNull(cb.trim(AccountingJoin.get(AccountingInfo_.acctCustId))));
                
                predicates.add(cb.or(cb.notLike(cb.trim(AccountingJoin.get(AccountingInfo_.acctCustId)), "")));

                predicates.add(cb.equal(root.get(Enterprise_.active), true));

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }

    public static Specification<Enterprise> findEnterprises(final String name, final String verifyState,
            final boolean active) {
        return new Specification<Enterprise>() {

            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                Join<Enterprise, UserAccount> userAccountJoin = root.join(Enterprise_.userAccount);

                Join<UserAccount, AccountingInfo> AccountingJoin = userAccountJoin.join(UserAccount_.accountingInfo);
                predicates.add(cb.isNotNull(root.get(Enterprise_.userAccount)));
//                predicates.add(cb.isNotNull(userAccountJoin.get(UserAccount_.accountingInfo)));
                predicates.add(cb.or(cb.notLike(cb.trim(AccountingJoin.get(AccountingInfo_.acctCustId)), "")));
                predicates.add(cb.isNotNull(cb.trim(AccountingJoin.get(AccountingInfo_.acctCustId))));


                if (StringUtils.hasText(name)) {
                    predicates.add(cb.or(cb.like(root.get(Enterprise_.name), "%" + name + "%")));
                }

                if (StringUtils.hasText(verifyState)) {
                    predicates.add(cb.or(cb.equal(root.get(Enterprise_.verifyState),
                            Enterprise.VerifyStatus.valueOf(verifyState))));
                }

                predicates.add(cb.equal(root.get(Enterprise_.active), active));

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }

    public static Specification<Enterprise> searchEnterprises(final EnterpriseCriteria criteria) {
        return new Specification<Enterprise>() {

            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.hasText(criteria.getName())) {
                    predicates.add(cb.or(cb.like(root.get(Enterprise_.name), "%" + criteria.getName() + "%")));
                }

                if (StringUtils.hasText(criteria.getStatus())) {
                    predicates.add(cb.or(cb.equal(root.get(Enterprise_.verifyState),
                            Enterprise.VerifyStatus.valueOf(criteria.getStatus()))));
                }

                predicates.add(cb.equal(root.get(Enterprise_.active), criteria.isActive()));

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }

}
