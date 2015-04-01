package com.moxian.ng.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.moxian.ng.domain.Message;
import com.moxian.ng.domain.Message_;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.domain.UserAccount_;
import javax.persistence.criteria.Join;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 *
 */
public class MessageSpecifications {

    public static Specification<Message> filterReceivedMessages(final Long id, final Boolean read) {
        return (Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (read != null) {
                predicates.add(cb.equal(root.get(Message_.read), read));
            }

            Join<Message, UserAccount> to = root.join(Message_.to);
            predicates.add(cb.equal(to.get(UserAccount_.id), id));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Message> filterSentMessages(final Long id) {
        return (Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Message, UserAccount> from = root.join(Message_.from);
            predicates.add(cb.equal(from.get(UserAccount_.id), id));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
