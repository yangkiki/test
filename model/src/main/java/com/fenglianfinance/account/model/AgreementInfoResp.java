package com.fenglianfinance.account.model;

import javax.validation.constraints.NotNull;

/**
 * 响应的协议参数
 * 
 * @author wangli@flf77.com
 * @date 2015年3月6日 下午4:19:33
 */
public class AgreementInfoResp {
	/**
	 * 业务名称
	 */
	@NotNull
	private String service;
	/**
	 * 签名方式
	 */
	@NotNull
	private String sign_type;
	/**
	 * 签名
	 */
	@NotNull
	private String sign;
	/**
	 * 版本号
	 */
	@NotNull
	private String version;
	/**
	 * 渠道号
	 */
	@NotNull
	private String channel_no;
	/**
	 * 渠道系统订单号
	 */
	@NotNull
	private String channel_order_no;
	/**
	 * 返回码
	 */
	@NotNull
	private String ret_code;
	/**
	 * 返回信息
	 */
	@NotNull
	private String ret_msg;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
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

	public String getChannel_no() {
		return channel_no;
	}

	public void setChannel_no(String channel_no) {
		this.channel_no = channel_no;
	}

	public String getChannel_order_no() {
		return channel_order_no;
	}

	public void setChannel_order_no(String channel_order_no) {
		this.channel_order_no = channel_order_no;
	}

	public String getRet_code() {
		return ret_code;
	}

	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}

	public String getRet_msg() {
		return ret_msg;
	}

	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}

	@Override
	public String toString() {
		return "AgreementInfoResp [service=" + service + ", sign_type="
				+ sign_type + ", sign=" + sign + ", version=" + version
				+ ", channel_no=" + channel_no + ", channel_order_no="
				+ channel_order_no + ", ret_code=" + ret_code + ", ret_msg="
				+ ret_msg + "]";
	}

	public String toLinkString() {
		StringBuilder sb = new StringBuilder("service=").append(service)
				.append("&version=").append(version).append("&channel_no=")
				.append(channel_no).append("&channel_order_no=")
				.append(channel_order_no).append("&ret_code=").append(ret_code)
				.append("&ret_msg=").append(ret_msg);
		return sb.toString();
	}

}
