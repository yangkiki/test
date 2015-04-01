package com.fenglianfinance.bill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fenglianfinance.bill.domain.WithFundingLinesConfs;

/**
 * 配资警戒线、平仓线配置
 * 
 * @author wangli@flf77.com
 * @date 2015年1月30日 下午2:28:24
 */
public interface WithFundingLinesConfsRepository extends
		JpaRepository<WithFundingLinesConfs, Long>,
		JpaSpecificationExecutor<WithFundingLinesConfs> {

	public List<WithFundingLinesConfs> findByActiveIsTrue();

	/**
	 * 查询杠杆比例下有效的配置信息
	 * 
	 * @return
	 */
	public WithFundingLinesConfs findByGearingAndActiveIsTrue(Long gearing);
}
