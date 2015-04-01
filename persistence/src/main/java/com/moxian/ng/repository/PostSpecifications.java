package com.moxian.ng.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.moxian.ng.domain.Post;
import com.moxian.ng.domain.Post_;

/**
 *
 * @author hantsy
 *
 */
public class PostSpecifications {

    public static Specification<Post> filterPostsByKeywordAndStatus(
            final String keyword,//
            final boolean active,//
            final Post.Type type,//
            final Post.Status status) {
        return (Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                predicates.add(cb.like(root.get(Post_.title), "%" + keyword + "%"));
            }

            predicates.add(cb.equal(root.get(Post_.active), active));

            if (type != null) {
                predicates.add(cb.equal(root.get(Post_.type), type));
            }
            
            if (status != null) {
                predicates.add(cb.equal(root.get(Post_.status), status));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
