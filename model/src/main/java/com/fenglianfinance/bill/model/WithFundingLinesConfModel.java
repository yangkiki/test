package com.fenglianfinance.bill.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 配资警戒线、平仓线
 * 
 * @author wangli@flf77.com
 * @date 2015年1月30日 下午2:09:21
 */
public class WithFundingLinesConfModel {

	private Long gearing; // 杠杆比例

	private BigDecimal positionspreadingLineRate = BigDecimal.ZERO; // 平仓线

	private BigDecimal warningLineRateOne = BigDecimal.ZERO; // 预警线一

	private BigDecimal warningLineRateTwo = BigDecimal.ZERO; // 预警线二

	private LocalDateTime effectTime; // 生效时间

	private LocalDateTime aeadTime; // 失效时间

	private boolean active = true;

	public Long getGearing() {
		return gearing;
	}

	public void setGearing(Long gearing) {
		this.gearing = gearing;
	}

	public BigDecimal getPositionspreadingLineRate() {
		return positionspreadingLineRate;
	}

	public void setPositionspreadingLineRate(
			BigDecimal positionspreadingLineRate) {
		this.positionspreadingLineRate = positionspreadingLineRate;
	}

	public BigDecimal getWarningLineRateOne() {
		return warningLineRateOne;
	}

	public void setWarningLineRateOne(BigDecimal warningLineRateOne) {
		this.warningLineRateOne = warningLineRateOne;
	}

	public BigDecimal getWarningLineRateTwo() {
		return warningLineRateTwo;
	}

	public void setWarningLineRateTwo(BigDecimal warningLineRateTwo) {
		this.warningLineRateTwo = warningLineRateTwo;
	}

	public LocalDateTime getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(LocalDateTime effectTime) {
		this.effectTime = effectTime;
	}

	public LocalDateTime getAeadTime() {
		return aeadTime;
	}

	public void setAeadTime(LocalDateTime aeadTime) {
		this.aeadTime = aeadTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
