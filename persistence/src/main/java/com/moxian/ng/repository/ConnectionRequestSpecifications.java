package com.moxian.ng.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.moxian.ng.domain.ConnectionRequests;
import com.moxian.ng.domain.UserAccount;

public class ConnectionRequestSpecifications {

    public static Specification<ConnectionRequests> filterConnectionRequestsByMemberUser(final Long id) {
        return (Root<ConnectionRequests> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<ConnectionRequests, UserAccount> userAccountJoin =
                    root.join(root.getModel().getSingularAttribute("memberUser", UserAccount.class), JoinType.INNER);
            if (id != null) {
                predicates.add(cb.equal(userAccountJoin.get("id"), id));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
