package com.moxian.ng.repository;

import com.moxian.ng.domain.Fans;
import com.moxian.ng.domain.UserAccount;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Fanspecifications {

	
	public static Specification<Fans> filterFansBySendAndRecept(final Long sendId,final Long receptId) {
        return (Root<Fans> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            Join<Fans, UserAccount> sendJoin = root.join(root.getModel().getSingularAttribute("send", UserAccount.class),JoinType.INNER);
            Join<Fans, UserAccount> receptJoin = root.join(root.getModel().getSingularAttribute("recept", UserAccount.class),JoinType.INNER);
            if (sendId != null) {
                predicates.add(cb.equal(sendJoin.get("id"),sendId));
            }
            
            if (receptId != null) {
                predicates.add(cb.equal(receptJoin.get("id"),receptId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
