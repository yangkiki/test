package com.fenglianfinance.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.fenglianfinance.bill.domain.WithFundingAccount;

/**
 * @author wangli@flf77.com
 * @date 2015年3月6日 下午5:02:45
 */
public interface WithFundingAccountRepository extends
		JpaRepository<WithFundingAccount, Long>,
		JpaSpecificationExecutor<WithFundingAccount> {

	/**
	 * @param channelOrderNo
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月19日 下午1:49:20
	 */
	WithFundingAccount findByChannelOrderNoAndActiveIsFalse(
			String channelOrderNo);

	/**
	 * @param serialNumber
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月19日 下午2:16:11
	 */
	@Query(" SELECT w FROM WithFundingAccount w WHERE w.active=true AND w.withFundingInfos.active=true AND w.withFundingInfos.serialNumber=?1 ")
	WithFundingAccount findByWithFundingInfosSerialNumber(String serialNumber);

}
