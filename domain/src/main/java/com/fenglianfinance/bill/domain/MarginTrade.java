
package com.fenglianfinance.bill.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fenglianfinance.bill.domain.support.PersistableEntity;

/**
 * 保证金交易信息
 * @author zimao.jiang
 * @date 2015年2月12日 下午5:08:32
 *
 */
@Entity
@Table(name = "margin_trade", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class MarginTrade extends PersistableEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 投标ID
	 */
	@Column(name = "bid_ord_id")
	private String bidOrdId;
	
	/**
	 * 投标日期
	 */
	@Column(name = "bid_ord_date")
	private String bidOrdDate;
	
	/**
	 * 付款ID
	 */
	@Column(name = "repayment_ord_id")
	private String repaymentOrdId;
	
	/**
	 * 子订单号
	 */
	@Column(name = "sub_ord_id")
	private String subOrdId;

	/**
	 * 子订单时间
	 */
	@Column(name = "sub_ord_date")
	private String subOrdDate;

	/**
	 * @return the bidOrdId
	 */
	public String getBidOrdId() {
		return bidOrdId;
	}

	/**
	 * @param bidOrdId the bidOrdId to set
	 */
	public void setBidOrdId(String bidOrdId) {
		this.bidOrdId = bidOrdId;
	}
	

	/**
	 * @return the bidOrdDate
	 */
	public String getBidOrdDate() {
		return bidOrdDate;
	}

	/**
	 * @param bidOrdDate the bidOrdDate to set
	 */
	public void setBidOrdDate(String bidOrdDate) {
		this.bidOrdDate = bidOrdDate;
	}

	/**
	 * @return the repaymentOrdId
	 */
	public String getRepaymentOrdId() {
		return repaymentOrdId;
	}

	/**
	 * @param repaymentOrdId the repaymentOrdId to set
	 */
	public void setRepaymentOrdId(String repaymentOrdId) {
		this.repaymentOrdId = repaymentOrdId;
	}

	/**
	 * @return the subOrdId
	 */
	public String getSubOrdId() {
		return subOrdId;
	}

	/**
	 * @param subOrdId the subOrdId to set
	 */
	public void setSubOrdId(String subOrdId){
		this.subOrdId = subOrdId;
	}

	
	/**
	 * @return the subOrdDate
	 */
	public String getSubOrdDate() {
		return subOrdDate;
	}

	/**
	 * @param subOrdDate the subOrdDate to set
	 */
	public void setSubOrdDate(String subOrdDate) {
		this.subOrdDate = subOrdDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MarginTrade [bidOrdId=" + bidOrdId + ", bidOrdDate="
				+ bidOrdDate + ", repaymentOrdId=" + repaymentOrdId
				+ ", subOrdId=" + subOrdId + ", subOrdDate=" + subOrdDate + "]";
	}
	
}
