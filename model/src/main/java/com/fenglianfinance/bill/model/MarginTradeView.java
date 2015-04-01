/**
 * 
 */
package com.fenglianfinance.bill.model;

import java.io.Serializable;

/**
 * @author zimao.jiang
 * @date 2015年2月13日 上午11:28:14
 */
public class MarginTradeView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Long id;
	/**
	 * 投标ID
	 */
	private String bidOrdId;
	
	/**
	 * 付款ID
	 */
	private String repaymentOrdId;
	
	/**
	 * 子订单号
	 */
	private String subOrdId;

	/**
	 * 子订单时间
	 */
	private String subOrdDate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
	public void setSubOrdId(String subOrdId) {
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

	@Override
	public String toString() {
		return "MarginTradeView [id=" + id + ", bidOrdId=" + bidOrdId
				+ ", repaymentOrdId=" + repaymentOrdId + ", subOrdId="
				+ subOrdId + ", subOrdDate=" + subOrdDate + "]";
	}
}
