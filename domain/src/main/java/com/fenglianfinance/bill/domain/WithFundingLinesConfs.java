package com.fenglianfinance.bill.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

/**
 * 配资警戒平仓线配置表
 * 
 * @author wangli@flf77.com
 * @date 2015年1月30日 下午1:36:49
 */
@Entity
@Table(name = "with_funding_lines_confs")
public class WithFundingLinesConfs extends AuditableEntity<UserAccount, Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "gearing")
	private Long gearing; // 杠杆比例

	@Column(name = "positionspreading_line_rate")
	private BigDecimal positionspreadingLineRate; // 平仓线

	@Column(name = "warning_line_rate_one")
	private BigDecimal warningLineRateOne; // 预警线一

	@Column(name = "warning_line_rate_two")
	private BigDecimal warningLineRateTwo; // 预警线二

	@Column(name = "effect_time")
	private LocalDateTime effectTime; // 生效时间

	@Column(name = "aead_time")
	private LocalDateTime aeadTime; // 失效时间

	@JoinColumn(name = "enterprise_id")
	@ManyToOne
	private Enterprise enterprise; // 配资企业

	@Column(name = "is_active")
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

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "WithFundingLinesConfs [gearing=" + gearing
				+ ", positionspreadingLineRate=" + positionspreadingLineRate
				+ ", warningLineRateOne=" + warningLineRateOne
				+ ", warningLineRateTwo=" + warningLineRateTwo
				+ ", effectTime=" + effectTime + ", aeadTime=" + aeadTime
				+ ", enterprise=" + enterprise + ", active=" + active + "]";
	}

}
