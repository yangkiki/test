package com.fenglianfinance.bill.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.fenglianfinance.bill.domain.WithFundingBrokerageConfs;

public interface WithFundingBrokerageConfsRepository extends
		JpaRepository<WithFundingBrokerageConfs, Long>,
		JpaSpecificationExecutor<WithFundingBrokerageConfs> {

	public List<WithFundingBrokerageConfs> findByActiveIsTrue();

	@Query("select x from WithFundingBrokerageConfs x where x.active = true and x.gearing = ?2 and x.fundingFloor <= ?1 and fundingCeil > ?1 ")
	public WithFundingBrokerageConfs find4WithFundingApply(BigDecimal funding,
			Long gearing);

}
