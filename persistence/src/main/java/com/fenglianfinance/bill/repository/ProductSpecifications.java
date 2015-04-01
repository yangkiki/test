package com.fenglianfinance.bill.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.domain.ProductCriteria;
import com.fenglianfinance.bill.domain.Product_;

public class ProductSpecifications {

    public static Specification<Product> findProductByName(String name, boolean active, Product.Type type,
            Product.Status status) {
        return new Specification<Product>() {

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.hasText(name)) {
                    predicates.add(cb.like(root.get(Product_.name), "%" + name + "%"));
                }

                predicates.add(cb.equal(root.get(Product_.active), active));

                if (type != null) {
                    predicates.add(cb.equal(root.get(Product_.type), type));
                }

                if (status != null) {
                    predicates.add(cb.equal(root.get(Product_.status), status));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static Specification<Product> searchProducts(ProductCriteria criteria) {

        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(criteria.getName())) {
                predicates.add(cb.like(root.get(Product_.name), "%" + criteria.getName() + "%"));
            }

            predicates.add(cb.equal(root.get(Product_.active), criteria.isActive()));

            if (StringUtils.hasText(criteria.getPromoted())) {
                predicates.add(cb.equal(root.get(Product_.promoted), Boolean.valueOf(criteria.getPromoted())));
            }

            if (StringUtils.hasText(criteria.getType())) {
                predicates.add(cb.equal(root.get(Product_.type), Product.Type.valueOf(criteria.getType())));
            }
            if (StringUtils.hasText(criteria.getStatus())) {
                predicates.add(cb.equal(root.get(Product_.status), Product.Status.valueOf(criteria.getStatus())));
            }

            if (criteria.getStart() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(Product_.onSaleDate), criteria.getStart()));
            }

            if (criteria.getEnd() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get(Product_.onSaleDate), criteria.getEnd()));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
