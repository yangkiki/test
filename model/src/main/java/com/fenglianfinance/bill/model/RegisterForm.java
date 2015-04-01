package com.fenglianfinance.bill.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * website注册form
 * 
 * @author wangli@flf77.com
 * @date 2015年2月4日 下午4:28:49
 */
public class RegisterForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Size(min = 11, max = 11, message = "手机号码必须是11位数字")
	private String mobileNumber;

	@NotNull
	@NotEmpty
	@Size(min = 6, message = "密码必须由 6 到 20 位字符组成")
	private String password;
	
	@NotNull
	@NotEmpty
	@Size(min = 6, max = 6, message = "验证码必须由 6位数字组成")
	private String smsCode;

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegisterForm [mobileNumber=" + mobileNumber + ", password="
				+ password + "]";
	}

}
