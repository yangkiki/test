package com.fenglianfinance.account.model;

import javax.validation.constraints.NotNull;

/**
 * 请求的协议参数
 * 
 * @author wangli@flf77.com
 * @date 2015年3月5日 下午4:36:54
 */
public class AgreementInfoReq {
	/**
	 * 开户业务
	 */
	public static final String SERVICE_REGISTER_ACCOUNT = "register_account";

	/**
	 * 开户信息查询业务
	 */
	public static final String SERVICE_QUERY_ACCOUNT = "query_account";

	/**
	 * 业务名称
	 */
	@NotNull
	private String service;
	/**
	 * 签名方式
	 */
	@NotNull
	private String signType;
	/**
	 * 签名
	 */
	@NotNull
	private String sign;
	/**
	 * 版本号
	 */
	@NotNull
	private String version = "1.0";
	/**
	 * 渠道号
	 */
	@NotNull
	private String channelNo;
	/**
	 * 交易日期 格式：YYYYMMDD
	 */
	@NotNull
	private String transDate;
	/**
	 * 交易时间 格式：HH24MISS
	 */
	@NotNull
	private String transTime;
	/**
	 * 渠道系统订单号
	 */
	@NotNull
	private String channelOrdersNo;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getChannelOrdersNo() {
		return channelOrdersNo;
	}

	public void setChannelOrdersNo(String channelOrdersNo) {
		this.channelOrdersNo = channelOrdersNo;
	}

	@Override
	public String toString() {
		return "AgreementInfoReq [service=" + service + ", signType="
				+ signType + ", sign=" + sign + ", version=" + version
				+ ", channelNo=" + channelNo + ", transDate=" + transDate
				+ ", transTime=" + transTime + ", channelOrdersNo="
				+ channelOrdersNo + "]";
	}

	public String toLinkString() {
		StringBuilder sb = new StringBuilder("service=").append(service)
				.append("&version=").append(version).append("&channel_no=")
				.append(channelNo).append("&trans_date=").append(transDate)
				.append("&trans_time=").append(transTime)
				.append("&channel_orders_no=").append(channelOrdersNo);
		return sb.toString();
	}
}
