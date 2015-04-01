package com.moxian.ng.account.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 开户请求的业务参数
 * 
 * @author wangli@flf77.com
 * @date 2015年3月5日 下午2:22:27
 */
public class RegisterAccountReq {

	/**
	 * 是否通过实名制认证 0：否 1：是
	 */
	public static final String REALNAME_STATE_YES = "1";
	/**
	 * 是否通过实名制认证 0：否 1：是
	 */
	public static final String REALNAME_STATE_NO = "0";
	/**
	 * 保证金状态 0：已付 1：未付
	 */
	public static final String MARGIN_STATE_YES = "0";
	/**
	 * 保证金状态 0：已付 1：未付
	 */
	public static final String MARGIN_STATE_NO = "1";
	/**
	 * 计息方式 01：按天 02：按月
	 */
	public static final String PLAN_BREATH_WAY_DAY = "01";
	/**
	 * 计息方式 01：按天 02：按月
	 */
	public static final String PLAN_BREATH_WAY_MONTH = "02";
	/**
	 * 保证金付款方式 01：线下（银行打款）
	 */
	public static final String MARGIN_PAY_TYPE_OFFLINE = "01";
	/**
	 * 保证金付款方式 02：线上（第三方支付）
	 */
	public static final String MARGIN_PAY_TYPE_ONLINE = "02";
	/**
	 * 保证金付款方式 03：使用渠道总保证金付款
	 */
	public static final String MARGIN_PAY_TYPE_DEPOSIT = "03";

	/**
	 * 用户标识
	 */
	private String userId;
	/**
	 * 供应商标识 01：恒生
	 */
	@NotNull
	private String supplierNo = "01";
	/**
	 * 用户姓名
	 */
	@NotNull
	private String userName;
	/**
	 * 证件类型 01：身份证
	 */
	private String identityType = "01";
	/**
	 * 证件号
	 */
	@NotNull
	private String identityCode;
	/**
	 * 是否通过实名制认证 0：否 1：是
	 */
	@NotNull
	private String realNameState;
	/**
	 * 出款银行名称
	 */
	@NotNull
	private String bankName;
	/**
	 * 结算卡号
	 */
	@NotNull
	private String settleCardNo;
	/**
	 * 保证金状态 0：已付 1：未付
	 */
	@NotNull
	private String marginState;
	/**
	 * 保证金付款方式 01：线下（银行打款） 02：线上（第三方支付） 03：使用渠道总保证金付款
	 */
	@NotNull
	private String marginPayType;
	/**
	 * 保证金金额 单位：分；无小数位
	 */
	@NotNull
	private String marginAmt;
	/**
	 * 借款金额 单位：分；无小数位
	 */
	@NotNull
	private String borrowAmt;
	/**
	 * 配资金额=保证金+借款金额 单位：分；无小数位
	 */
	@NotNull
	private String fundAmt;
	/**
	 * 配资开始日期 格式：YYYYMMDD
	 */
	@NotNull
	private String fundStartDate;
	/**
	 * 配资结束日期 格式：YYYYMMDD
	 */
	@NotNull
	private String fundEndDate;
	/**
	 * 计息方式 01：按天 02：按月
	 */
	@NotNull
	private String planBreathWay;
	/**
	 * 利率 单位：万分比；最多两位小数位
	 */
	@NotNull
	private String interestRate;
	/**
	 * 佣金 单位：万分比；最多两位小数位
	 */
	@NotNull
	private String commission;
	/**
	 * 利息 单位：分；无小数位
	 */
	@NotNull
	private String interest;
	/**
	 * 第一警戒线 单位：分；无小数位
	 */
	@NotNull
	private String guardLineOne;
	/**
	 * 第二警戒线 单位：分；无小数位
	 */
	private String guardLineTwo;
	/**
	 * 平仓线 单位：分；无小数位
	 */
	@NotNull
	private String clearLine;
	/**
	 * 手机号
	 */
	@Size(min = 11, max = 11, message = "手机号码必须是11位数字")
	private String mobileId;
	/**
	 * 邮箱 用户真实邮箱
	 */
	private String email;
	/**
	 * 通知地址：开户结果异步通知地址
	 */
	private String retUrl;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public String getRealNameState() {
		return realNameState;
	}

	public void setRealNameState(String realNameState) {
		this.realNameState = realNameState;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSettleCardNo() {
		return settleCardNo;
	}

	public void setSettleCardNo(String settleCardNo) {
		this.settleCardNo = settleCardNo;
	}

	public String getMarginState() {
		return marginState;
	}

	public void setMarginState(String marginState) {
		this.marginState = marginState;
	}

	public String getMarginPayType() {
		return marginPayType;
	}

	public void setMarginPayType(String marginPayType) {
		this.marginPayType = marginPayType;
	}

	public String getMarginAmt() {
		return marginAmt;
	}

	public void setMarginAmt(String marginAmt) {
		this.marginAmt = marginAmt;
	}

	public String getBorrowAmt() {
		return borrowAmt;
	}

	public void setBorrowAmt(String borrowAmt) {
		this.borrowAmt = borrowAmt;
	}

	public String getFundAmt() {
		return fundAmt;
	}

	public void setFundAmt(String fundAmt) {
		this.fundAmt = fundAmt;
	}

	public String getFundStartDate() {
		return fundStartDate;
	}

	public void setFundStartDate(String fundStartDate) {
		this.fundStartDate = fundStartDate;
	}

	public String getFundEndDate() {
		return fundEndDate;
	}

	public void setFundEndDate(String fundEndDate) {
		this.fundEndDate = fundEndDate;
	}

	public String getPlanBreathWay() {
		return planBreathWay;
	}

	public void setPlanBreathWay(String planBreathWay) {
		this.planBreathWay = planBreathWay;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getGuardLineOne() {
		return guardLineOne;
	}

	public void setGuardLineOne(String guardLineOne) {
		this.guardLineOne = guardLineOne;
	}

	public String getGuardLineTwo() {
		return guardLineTwo;
	}

	public void setGuardLineTwo(String guardLineTwo) {
		this.guardLineTwo = guardLineTwo;
	}

	public String getClearLine() {
		return clearLine;
	}

	public void setClearLine(String clearLine) {
		this.clearLine = clearLine;
	}

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRetUrl() {
		return retUrl;
	}

	public void setRetUrl(String retUrl) {
		this.retUrl = retUrl;
	}

	@Override
	public String toString() {
		return "RegisterAccountReq [userId=" + userId + ", supplierNo="
				+ supplierNo + ", userName=" + userName + ", identityType="
				+ identityType + ", identityCode=" + identityCode
				+ ", realNameState=" + realNameState + ", bankName=" + bankName
				+ ", settleCardNo=" + settleCardNo + ", marginState="
				+ marginState + ", marginPayType=" + marginPayType
				+ ", marginAmt=" + marginAmt + ", borrowAmt=" + borrowAmt
				+ ", fundAmt=" + fundAmt + ", fundStartDate=" + fundStartDate
				+ ", fundEndDate=" + fundEndDate + ", planBreathWay="
				+ planBreathWay + ", interestRate=" + interestRate
				+ ", commission=" + commission + ", interest=" + interest
				+ ", guardLineOne=" + guardLineOne + ", guardLineTwo="
				+ guardLineTwo + ", clearLine=" + clearLine + ", mobileId="
				+ mobileId + ", email=" + email + ", retUrl=" + retUrl + "]";
	}

}
