package com.fenglianfinance.bill.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

/**
 * 配资开户的流水
 * 
 * @author wangli@flf77.com
 * @date 2015年3月6日 下午4:51:59
 */
@Entity
@Table(name = "with_funding_account_log", uniqueConstraints = { @UniqueConstraint(columnNames = { "channel_order_no" }) })
public class WithFundingAccountLog extends AuditableEntity<UserAccount, Long> {

	private static final long serialVersionUID = 1L;

	/**
	 * 业务名称
	 */
	@Column(name = "service")
	private String service;

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
	 * 返回码
	 */
	@Column(name = "ret_code")
	private String retCode;

	/**
	 * 返回码
	 */
	@Column(name = "ret_msg")
	private String retMsg;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

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

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	@Override
	public String toString() {
		return "WithFundingAccountLog [service=" + service + ", version="
				+ version + ", channelNo=" + channelNo + ", channelOrderNo="
				+ channelOrderNo + ", retCode=" + retCode + ", retMsg="
				+ retMsg + "]";
	}

}
