package com.fenglianfinance.bill.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

/**
 * 配资开户的账户信息
 * 
 * @author wangli@flf77.com
 * @date 2015年3月6日 下午4:52:09
 */
@Entity
@Table(name = "with_funding_account", uniqueConstraints = { @UniqueConstraint(columnNames = { "channel_order_no" }) })
public class WithFundingAccount extends AuditableEntity<UserAccount, Long> {

	private static final long serialVersionUID = 1L;

	/**
	 * 版本号
	 */
	@Column(name = "version")
	private String version;

	/**
	 * 渠道号
	 */
	@Column(name = "channel_no")
	private String channelNo;

	/**
	 * 渠道系统订单号
	 */
	@Column(name = "channel_order_no")
	private String channelOrderNo;

	/**
	 * 配资系统订单号
	 */
	@Column(name = "pz_order_no")
	private String pzOrderNo;

	/**
	 * 开户状态
	 */
	@Column(name = "open_state")
	private String openState;

	/**
	 * 供应商标识
	 */
	@Column(name = "supplier_no")
	private String supplierNo;

	/**
	 * 开户时间 格式：YYYYMMDDHH24MISS 返回码为成功时该字段有值，否则为空
	 */
	@Column(name = "open_time")
	private String openTime;

	/**
	 * 股票子账号名称
	 */
	@Column(name = "account_name")
	private String accountName;

	/**
	 * 股票子账号
	 */
	@Column(name = "account_no")
	private String accountNo;

	/**
	 * 账户操作员帐号
	 */
	@Column(name = "operator_id")
	private String operatorId;

	/**
	 * 账户操作员密码
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 开户信息对应流水号
	 */
	@JoinColumn(name = "with_funding_account_log_id")
	@OneToOne
	private WithFundingAccountLog withFundingAccountLog;

	/**
	 * 关联的配资申请记录
	 */
	@JoinColumn(name = "with_funding_infos_sn")
	@ManyToOne
	private WithFundingInfos withFundingInfos;

	/**
	 * 是否有效
	 */
	@Column(name = "is_active")
	private boolean active = true;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getChannelOrderNo() {
		return channelOrderNo;
	}

	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}

	public String getPzOrderNo() {
		return pzOrderNo;
	}

	public void setPzOrderNo(String pzOrderNo) {
		this.pzOrderNo = pzOrderNo;
	}

	public String getOpenState() {
		return openState;
	}

	public void setOpenState(String openState) {
		this.openState = openState;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public WithFundingAccountLog getWithFundingAccountLog() {
		return withFundingAccountLog;
	}

	public void setWithFundingAccountLog(
			WithFundingAccountLog withFundingAccountLog) {
		this.withFundingAccountLog = withFundingAccountLog;
	}

	public WithFundingInfos getWithFundingInfos() {
		return withFundingInfos;
	}

	public void setWithFundingInfos(WithFundingInfos withFundingInfos) {
		this.withFundingInfos = withFundingInfos;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "WithFundingAccount [version=" + version + ", channelNo="
				+ channelNo + ", channelOrderNo=" + channelOrderNo
				+ ", pzOrderNo=" + pzOrderNo + ", openState=" + openState
				+ ", supplierNo=" + supplierNo + ", openTime=" + openTime
				+ ", accountName=" + accountName + ", accountNo=" + accountNo
				+ ", operatorId=" + operatorId + ", password=" + password
				+ ", withFundingAccountLog=" + withFundingAccountLog
				+ ", withFundingInfos=" + withFundingInfos + ", active="
				+ active + "]";
	}

}
