package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 充值/提现流水交易统计数据
 * 
 * @author wangli@flf77.com
 * @date 2015年1月27日 下午5:45:31
 */
public class TransactionLogStatistics implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal sumAmount = BigDecimal.ZERO;// 累计金额
	private int times = 0;// 累计记录数

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return "TransactionLogStatistics [sumAmount=" + sumAmount + ", times="
				+ times + "]";
	}
}
