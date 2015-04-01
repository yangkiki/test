package com.fenglianfinance.bill.exception;

/**
 * @author wangli@flf77.com
 * @date 2015年2月4日 下午4:34:24
 */
public class MobileNumberExistedException extends RuntimeException {
	private String mobileNumber;

	public MobileNumberExistedException(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
