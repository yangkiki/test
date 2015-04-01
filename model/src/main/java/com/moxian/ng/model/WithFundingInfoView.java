package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithFundingInfoView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String serialNumber;

	private Long gearing; // 杠杆比例

	private String mode; // 配资类型

	private String status; // 状态

	private NameValue user; // 配资申请人

	private BigDecimal deposit; // 保证金

	private BigDecimal brokerage; // 管理费（佣金）

	private BigDecimal funding; // 配资金额

	private BigDecimal warningLineOne; // 预警线一

	private BigDecimal warningLineTwo; // 预警线二

	private BigDecimal positionspreadingLine; // 平仓线

	private String description; // 描述

	private Integer terms; // 期限

	private boolean active = true;

	private LocalDateTime createdDate; // 申请时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

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

	public NameValue getUser() {
		return user;
	}

	public void setUser(NameValue user) {
		this.user = user;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "WithFundingInfoView [id=" + id + ", serialNumber="
				+ serialNumber + ", gearing=" + gearing + ", mode=" + mode
				+ ", status=" + status + ", user=" + user + ", deposit="
				+ deposit + ", brokerage=" + brokerage + ", funding=" + funding
				+ ", warningLineOne=" + warningLineOne + ", warningLineTwo="
				+ warningLineTwo + ", positionspreadingLine="
				+ positionspreadingLine + ", description=" + description
				+ ", terms=" + terms + ", active=" + active + ", createdDate="
				+ createdDate + "]";
	}

}
