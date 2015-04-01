package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 配资信息表单
 * 
 * @author wangli@flf77.com
 * @date 2015年2月1日 下午5:28:22
 */
public class WithFundingInfoForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long gearing; // 杠杆比例

	private String mode; // 配资类型

	private String status; // 状态

	private BigDecimal deposit; // 保证金

	private BigDecimal brokerage; // 管理费（佣金）

	private BigDecimal funding; // 配资金额

	private BigDecimal warningLineOne; // 预警线一

	private BigDecimal warningLineTwo; // 预警线二

	private BigDecimal positionspreadingLine; // 平仓线

	private String description; // 描述

	private Integer terms; // 期限

	public Long getGearing() {
		return gearing;
	}

	public void setGearing(Long gearing) {
		this.gearing = gearing;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(BigDecimal brokerage) {
		this.brokerage = brokerage;
	}

	public BigDecimal getFunding() {
		return funding;
	}

	public void setFunding(BigDecimal funding) {
		this.funding = funding;
	}

	public BigDecimal getWarningLineOne() {
		return warningLineOne;
	}

	public void setWarningLineOne(BigDecimal warningLineOne) {
		this.warningLineOne = warningLineOne;
	}

	public BigDecimal getWarningLineTwo() {
		return warningLineTwo;
	}

	public void setWarningLineTwo(BigDecimal warningLineTwo) {
		this.warningLineTwo = warningLineTwo;
	}

	public BigDecimal getPositionspreadingLine() {
		return positionspreadingLine;
	}

	public void setPositionspreadingLine(BigDecimal positionspreadingLine) {
		this.positionspreadingLine = positionspreadingLine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTerms() {
		return terms;
	}

	public void setTerms(Integer terms) {
		this.terms = terms;
	}

	@Override
	public String toString() {
		return "WithFundingInfoForm [gearing=" + gearing + ", mode=" + mode
				+ ", status=" + status + ", deposit=" + deposit
				+ ", brokerage=" + brokerage + ", funding=" + funding
				+ ", warningLineOne=" + warningLineOne + ", warningLineTwo="
				+ warningLineTwo + ", positionspreadingLine="
				+ positionspreadingLine + ", description=" + description
				+ ", terms=" + terms + "]";
	}

}
