package com.fenglianfinance.bill.model;

import java.math.BigDecimal;

/**
 * 配资佣金配置
 * 
 * @author wangli@flf77.com
 * @date 2015年2月1日 下午3:59:50
 */
public class WithFundingBrokerageConfModel {

	private Long gearing; // 杠杆比例

	private BigDecimal fundingFloor; // 配资下限（含）

	private BigDecimal fundingCeil; // 配资上限（不含）

	private float annualPercentageRate = 0.0f;

	private float dailyPercentageRate = 0.0f;

	private boolean active = true;

	public Long getGearing() {
		return gearing;
	}

	public void setGearing(Long gearing) {
		this.gearing = gearing;
	}

	public BigDecimal getFundingFloor() {
		return fundingFloor;
	}

	public void setFundingFloor(BigDecimal fundingFloor) {
		this.fundingFloor = fundingFloor;
	}

	public BigDecimal getFundingCeil() {
		return fundingCeil;
	}

	public void setFundingCeil(BigDecimal fundingCeil) {
		this.fundingCeil = fundingCeil;
	}

	public float getAnnualPercentageRate() {
		return annualPercentageRate;
	}

	public void setAnnualPercentageRate(float annualPercentageRate) {
		this.annualPercentageRate = annualPercentageRate;
	}

	public float getDailyPercentageRate() {
		return dailyPercentageRate;
	}

	public void setDailyPercentageRate(float dailyPercentageRate) {
		this.dailyPercentageRate = dailyPercentageRate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
