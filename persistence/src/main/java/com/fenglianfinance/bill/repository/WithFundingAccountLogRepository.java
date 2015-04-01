package com.fenglianfinance.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fenglianfinance.bill.domain.WithFundingAccountLog;

/**
 * @author wangli@flf77.com
 * @date 2015年3月6日 下午5:02:34
 */
public interface WithFundingAccountLogRepository extends
		JpaRepository<WithFundingAccountLog, Long>,
		JpaSpecificationExecutor<WithFundingAccountLog> {

}
