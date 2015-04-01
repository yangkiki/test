package com.fenglianfinance.bill.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.fenglianfinance.bill.domain.WithFundingInfos;

/**
 * @author wangli@flf77.com
 * @date 2015年1月29日 下午7:41:55
 */
public interface WithFundingInfosRepository extends
		JpaRepository<WithFundingInfos, Long>,
		JpaSpecificationExecutor<WithFundingInfos> {

	/**
	 * 修改配资信息状态
	 * 
	 * @param serialNumber
	 * @param status
	 * @author wangli@flf77.com
	 * @date 2015年2月2日 下午3:41:55
	 */
	@Query("update WithFundingInfos set status=?2 where serialNumber=?1 ")
	@Modifying
	@Transactional
	public void updateStatus(String serialNumber, WithFundingInfos.Status status);

	/**
	 * 通过serialNumber查询出配资申请信息
	 * 
	 * @param serialNumber
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月11日 下午1:52:59
	 */
	public WithFundingInfos findBySerialNumberAndActiveIsTrue(
			String serialNumber);

	/**
	 * 修改配资信息状态-审核通过未发布
	 * 
	 * @param serialNumber
	 * @param status
	 * @author wangli@flf77.com
	 * @date 2015年2月2日 下午3:41:55
	 */
	@Query("update WithFundingInfos set status='UNPUBLISHED',confirmedDate=?2 where serialNumber=?1 ")
	@Modifying
	public void updateStatus(String serialNumber, LocalDateTime confirmedDate);

	/**
	 * 查询所有审核通过未发布的配资申请
	 * 
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月12日 下午2:14:29
	 */
	@Query(" select p from WithFundingInfos p where p.active=true and p.status='UNPUBLISHED' ")
	public Page<WithFundingInfos> findUnpublishedWithFundingInfos(
			Pageable pageable);
}
