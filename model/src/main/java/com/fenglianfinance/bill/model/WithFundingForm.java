package com.fenglianfinance.bill.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author wangli@flf77.com
 * @date 2015年2月13日 下午5:13:33
 */
public class WithFundingForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
    @NotEmpty
	private String serialNumber; // 唯一键

	@NotNull
    @NotEmpty
	private String status; // 状态

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "WithFundingForm [serialNumber=" + serialNumber + ", status="
				+ status + "]";
	}

}
