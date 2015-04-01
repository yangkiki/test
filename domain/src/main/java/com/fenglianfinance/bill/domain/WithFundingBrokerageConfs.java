package com.fenglianfinance.bill.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

/**
 * 配资佣金参数表
 * 
 * @author wangli@flf77.com
 * @date 2015年1月29日 下午4:00:25
 */
@Entity
@Table(name = "with_funding_brokerage_confs")
public class WithFundingBrokerageConfs extends
		AuditableEntity<UserAccount, Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "gearing")
	private Long gearing; // 杠杆比例

	@Column(name = "funding_floor")
	private BigDecimal fundingFloor; // 配资下限（含）

	@Column(name = "funding_ceil")
	private BigDecimal fundingCeil; // 配资上限（不含）

	@Column(name = "annual_percentage_rate")
	private BigDecimal annualPercentageRate;

	@Column(name = "daily_percentage_rate")
	private BigDecimal dailyPercentageRate;

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

	public BigDecimal getAnnualPercentageRate() {
		return annualPercentageRate;
	}

	public void setAnnualPercentageRate(BigDecimal annualPercentageRate) {
		this.annualPercentageRate = annualPercentageRate;
	}

	public BigDecimal getDailyPercentageRate() {
		return dailyPercentageRate;
	}

	public void setDailyPercentageRate(BigDecimal dailyPercentageRate) {
		this.dailyPercentageRate = dailyPercentageRate;
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
		return "WithFundingBrokerageConfs [gearing=" + gearing
				+ ", fundingFloor=" + fundingFloor + ", fundingCeil="
				+ fundingCeil + ", annualPercentageRate="
				+ annualPercentageRate + ", dailyPercentageRate="
				+ dailyPercentageRate + ", enterprise=" + enterprise
				+ ", active=" + active + "]";
	}

}
