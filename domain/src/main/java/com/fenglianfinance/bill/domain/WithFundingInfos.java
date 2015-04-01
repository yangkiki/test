package com.fenglianfinance.bill.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

/**
 * 配资信息表
 * 
 * @author wangli@flf77.com
 * @date 2015年1月29日 下午3:38:09
 */
@Entity
@Table(name = "with_funding_infos", uniqueConstraints = { @UniqueConstraint(columnNames = { "serial_number" }) })
public class WithFundingInfos extends AuditableEntity<UserAccount, Long> {

	private static final long serialVersionUID = 1L;

	public enum Mode {
		DAY, // 按天配资
		MONTH // 按月配资
	}

	public enum Status {
		/**
		 * 待付款
		 */
		PENDING,
		/**
		 * 已付款待确认
		 */
		CONFIRMING,
		/**
		 * 确认通过未发布
		 */
		UNPUBLISHED,
		/**
		 * 确认通过已发布
		 */
		PUBLISHED,
		/**
		 * 确认通过已过期限
		 */
		EXPIRED,
		/**
		 * 已驳回
		 */
		DISMISSED;
	}

	@Column(name = "serial_number")
	private String serialNumber;

	@Column(name = "gearing")
	private Long gearing; // 杠杆比例

	@Enumerated(EnumType.STRING)
	@Column(name = "mode")
	private Mode mode; // 配资类型

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status; // 状态

	@JoinColumn(name = "user_id")
	@ManyToOne
	private UserAccount user; // 配资申请人

	@JoinColumn(name = "enterprise_id")
	@ManyToOne
	private Enterprise enterprise; // 配资企业

	@Column(name = "deposit")
	private BigDecimal deposit; // 保证金

	@Column(name = "brokerage")
	private BigDecimal brokerage; // 管理费"/天"或"/月"（利息）

	@Column(name = "funding")
	private BigDecimal funding; // 配资金额

	@Column(name = "warning_line_one")
	private BigDecimal warningLineOne; // 预警线一

	@Column(name = "warning_line_two")
	private BigDecimal warningLineTwo; // 预警线二

	@Column(name = "positionspreading_line")
	private BigDecimal positionspreadingLine; // 平仓线

	@Column(name = "description")
	private String description; // 描述

	@Column(name = "terms")
	private Integer terms; // 期限

	@Column(name = "is_active")
	private boolean active = true;

	@Column(name = "confirmed_date")
	private LocalDateTime confirmedDate; // 审核通过时间

	@Column(name = "commission")
	private BigDecimal commission; // 股票开户佣金，单位：万分比

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

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
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

	public BigDecimal getFunding() {
		return funding;
	}

	public void setFunding(BigDecimal funding) {
		this.funding = funding;
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

	public LocalDateTime getConfirmedDate() {
		return confirmedDate;
	}

	public void setConfirmedDate(LocalDateTime confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	@Override
	public String toString() {
		return "WithFundingInfos [serialNumber=" + serialNumber + ", gearing="
				+ gearing + ", mode=" + mode + ", status=" + status + ", user="
				+ user + ", enterprise=" + enterprise + ", deposit=" + deposit
				+ ", brokerage=" + brokerage + ", funding=" + funding
				+ ", warningLineOne=" + warningLineOne + ", warningLineTwo="
				+ warningLineTwo + ", positionspreadingLine="
				+ positionspreadingLine + ", description=" + description
				+ ", terms=" + terms + ", active=" + active
				+ ", confirmedDate=" + confirmedDate + ", commission="
				+ commission + "]";
	}

}
