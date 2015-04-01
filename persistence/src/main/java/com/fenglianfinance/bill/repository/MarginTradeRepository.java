/**
 * 
 */
package com.fenglianfinance.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fenglianfinance.bill.domain.MarginTrade;

/**
 * @author zimao.jiang
 * @date 2015年2月12日 下午5:09:55
 */
public interface MarginTradeRepository extends
		JpaRepository<MarginTrade, Long>, JpaSpecificationExecutor<MarginTrade> {

	/**
	 * 根据serialNumber更新subOrdId和subOrdDate
	 * 
	 * @param subOrdId
	 * @param subOrdDate
	 * @param serialNumber
	 * @author wangli@flf77.com
	 * @date 2015年2月13日 下午12:30:36
	 */
	@Query("update MarginTrade set subOrdId=?1, subOrdDate = ?2 where bidOrdId = ?3")
	@Modifying
	void updateSubOrdIdAndSubOrdDate(String subOrdId, String subOrdDate,
			String serialNumber);

	/**
	 * 根据serialNumber更新repaymentOrdId
	 * 
	 * @param repaymentOrdId
	 * @param serialNumber
	 * @author wangli@flf77.com
	 * @date 2015年2月13日 下午12:31:11
	 */
	@Query("update MarginTrade set repaymentOrdId=?1 where bidOrdId = ?2")
	@Modifying
	void updateRepaymentOrdId(String repaymentOrdId, String serialNumber);

	/**
	 * 通过投标ID获取保证金交易信息
	 * 
	 * @param bidOrdId
	 * @author wangli@flf77.com
	 * @date 2015年2月13日 下午2:00:44
	 */
	MarginTrade findByBidOrdId(String bidOrdId);

}
