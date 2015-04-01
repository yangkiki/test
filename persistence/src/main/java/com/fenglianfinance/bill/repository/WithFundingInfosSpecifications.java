package com.fenglianfinance.bill.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.domain.WithFundingInfos;
import com.fenglianfinance.bill.domain.WithFundingInfos_;
import com.fenglianfinance.bill.model.WithFundingInfoSearchCriteria;

/**
 * @author wangli@flf77.com
 * @date 2015年1月29日 下午7:42:13
 */
public class WithFundingInfosSpecifications {

	public static Specification<WithFundingInfos> filterByCriteria(
			final WithFundingInfos.Mode mode, final Long gearing,
			final WithFundingInfos.Status status) {

		return (Root<WithFundingInfos> root, CriteriaQuery<?> query,
				CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (mode != null) {
				predicates
						.add(cb.equal(root.get(WithFundingInfos_.mode), mode));
			}

			if (gearing != null) {
				predicates.add(cb.equal(root.get(WithFundingInfos_.gearing),
						gearing));
			}

			if (status != null) {
				predicates.add(cb.equal(root.get(WithFundingInfos_.status),
						status));
			}
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

	public static Specification<WithFundingInfos> searchWithFundingInfos(
			WithFundingInfoSearchCriteria criteria) {

		return (Root<WithFundingInfos> root, CriteriaQuery<?> query,
				CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			predicates.add(cb.equal(root.get(WithFundingInfos_.active),
					criteria.isActive()));

			if (null != criteria.getGearing()) {
				predicates.add(cb.equal(root.get(WithFundingInfos_.gearing),
						criteria.getGearing()));
			}

			if (StringUtils.hasText(criteria.getMode())) {
				predicates.add(cb.equal(root.get(WithFundingInfos_.mode),
						WithFundingInfos.Mode.valueOf(criteria.getMode())));
			}

			Predicate p = cb.and(predicates.toArray(new Predicate[predicates
					.size()]));

			if (null != criteria.getStatusArr()
					&& criteria.getStatusArr().length > 0) {
				predicates = new ArrayList<>();
				for (int i = 0; i < criteria.getStatusArr().length; i++) {
					predicates.add(cb.equal(root.get(WithFundingInfos_.status),
							WithFundingInfos.Status.valueOf(criteria
									.getStatusArr()[i])));

				}
				p = cb.and(p, cb.or(predicates.toArray(new Predicate[predicates
						.size()])));
			}
			return p;
		};
	}

	public static Specification<WithFundingInfos> searchWithFundingInfos(
			UserAccount user, WithFundingInfoSearchCriteria criteria) {

		return (Root<WithFundingInfos> root, CriteriaQuery<?> query,
				CriteriaBuilder cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			predicates.add(cb.equal(root.get(WithFundingInfos_.active),
					criteria.isActive()));

			predicates.add(cb.equal(root.get(WithFundingInfos_.user), user));

			if (null != criteria.getGearing()) {
				predicates.add(cb.equal(root.get(WithFundingInfos_.gearing),
						criteria.getGearing()));
			}

			if (StringUtils.hasText(criteria.getMode())) {
				predicates.add(cb.equal(root.get(WithFundingInfos_.mode),
						WithFundingInfos.Mode.valueOf(criteria.getMode())));
			}

			Predicate p = cb.and(predicates.toArray(new Predicate[predicates
					.size()]));

			if (null != criteria.getStatusArr()
					&& criteria.getStatusArr().length > 0) {
				predicates = new ArrayList<>();
				for (int i = 0; i < criteria.getStatusArr().length; i++) {
					predicates.add(cb.equal(root.get(WithFundingInfos_.status),
							WithFundingInfos.Status.valueOf(criteria
									.getStatusArr()[i])));

				}
				p = cb.and(p, cb.or(predicates.toArray(new Predicate[predicates
						.size()])));
			}
			return p;
		};
	}
}
