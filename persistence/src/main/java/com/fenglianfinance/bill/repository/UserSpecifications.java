package com.fenglianfinance.bill.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fenglianfinance.bill.domain.Role;
import com.fenglianfinance.bill.domain.Role_;
import com.fenglianfinance.bill.domain.StaffProfile;
import com.fenglianfinance.bill.domain.StaffProfile_;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.domain.UserAccount_;
import static java.lang.Long.max;
import static java.lang.Long.min;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 *
 * @author hantsy
 *
 */
public class UserSpecifications {

    public static Specification<StaffProfile> filterUserAccountsByKeywordAndRole(
            final String keyword,//
            final String role, //
            final String activeStatus,//
            final String locked//
    ) {

        return (Root<StaffProfile> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            Join<StaffProfile, UserAccount> userJoin = root.join(StaffProfile_.userAccount);

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(keyword)) {
                predicates.add(
                        cb.or(
                                cb.like(userJoin.get(UserAccount_.name), "%" + keyword + "%"),
                                cb.like(userJoin.get(UserAccount_.username), "%" + keyword + "%")
                        ));
            }

            ListJoin<UserAccount, String> roleJoin = userJoin.join(UserAccount_.roles);

            if (StringUtils.hasText(role) && !"ALL".equals(role)) {
                predicates.add(cb.equal(roleJoin, role));
            }

            predicates.add(cb.notEqual(roleJoin, "USER"));

            if (StringUtils.hasText(locked)) {
                predicates.add(cb.equal(userJoin.get(UserAccount_.locked), Boolean.valueOf(locked)));
            }

            if (StringUtils.hasText(activeStatus)) {
                predicates.add(cb.equal(userJoin.get(UserAccount_.active), Boolean.valueOf(activeStatus)));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<UserAccount> filterUserAccountsByKeyword(
            final String keyword,//
            final UserAccount.Type type,//
            final String role, //
            final String activeStatus,//
            final String locked,//
            final BigDecimal min, //
            final BigDecimal max //
    ) {

        return (Root<UserAccount> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(keyword)) {
                predicates.add(
                        cb.or(
                                cb.like(root.get(UserAccount_.name), "%" + keyword + "%"),
                                cb.like(root.get(UserAccount_.username), "%" + keyword + "%")
                        ));
            }

            if (type != null) {
                predicates.add(cb.equal(root.get(UserAccount_.type), type));
            }

            if (StringUtils.hasText(role) && !"ALL".equals(role)) {

                ListJoin<UserAccount, String> roleJoin = root.join(UserAccount_.roles);
                predicates.add(cb.equal(roleJoin, role));

            }

            if (StringUtils.hasText(locked)) {
                predicates.add(cb.equal(root.get(UserAccount_.locked), Boolean.valueOf(locked)));
            }

            if (StringUtils.hasText(activeStatus)) {
                predicates.add(cb.equal(root.get(UserAccount_.active), Boolean.valueOf(activeStatus)));
            }

            if (min != null) {
                predicates.add(cb.ge(root.get(UserAccount_.totalPaymentAmount), min));
            }

            if (max != null) {
                predicates.add(cb.le(root.get(UserAccount_.totalPaymentAmount), max));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<UserAccount> filterUserAccountsByDatetimeRange(
            final LocalDateTime start, //
            final LocalDateTime end //
    ) {

        return (Root<UserAccount> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get(UserAccount_.type), UserAccount.Type.USER));

            if (start != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(UserAccount_.createdDate), start));
            }

            if (end != null) {
                predicates.add(cb.lessThan(root.get(UserAccount_.createdDate), end));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Role> filterRoleByRoleName(final String name, final boolean active) {

        return new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.hasText(name)) {
                    predicates.add(cb.like(root.get(Role_.name), "%" + name + "%"));
                }

                predicates.add(cb.equal(root.get(Role_.active), active));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
