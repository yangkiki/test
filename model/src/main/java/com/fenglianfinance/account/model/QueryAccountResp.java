package com.fenglianfinance.account.model;

import javax.validation.constraints.NotNull;

/**
 * 查询子账户响应信息
 * 
 * @author wangli@flf77.com
 * @date 2015年3月16日 上午11:34:29
 */
public class QueryAccountResp {

	/**
	 * 用户标识
	 */
	@NotNull
	private String user_id;

	/**
	 * 开户状态
	 */
	@NotNull
	private String open_state;

	/**
	 * 股票子账户名称
	 */
	@NotNull
	private String account_name;

	/**
	 * 股票子账号
	 */
	@NotNull
	private String account_no;

	/**
	 * 账户操作员帐号
	 */
	@NotNull
	private String operator_id;

	/**
	 * 账户操作员密码
	 */
	@NotNull
	private String password;

	/**
	 * 总保证金金额 单位：分；无小数位
	 */
	@NotNull
	private String margin_amt;

	/**
	 * 总借款金额 单位：分；无小数位
	 */
	@NotNull
	private String borrow_amt;

	/**
	 * 总配资金额=总保证金+总借款金额
	 */
	@NotNull
	private String fund_amt;

	/**
	 * 配资开始时间 格式：YYYYMMDD
	 */
	@NotNull
	private String fund_start_date;

	/**
	 * 配资结束时间 格式：YYYYMMDD
	 */
	@NotNull
	private String fund_end_date;

	/**
	 * 第一警戒线 单位：分；无小数位
	 */
	@NotNull
	private String guard_line_one;

	/**
	 * 第二警戒线 单位：分；无小数位
	 */
	@NotNull
	private String guard_line_two;

	/**
	 * 平仓线 单位：分；无小数位
	 */
	@NotNull
	private String clear_line;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getOpen_state() {
		return open_state;
	}

	public void setOpen_state(String open_state) {
		this.open_state = open_state;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMargin_amt() {
		return margin_amt;
	}

	public void setMargin_amt(String margin_amt) {
		this.margin_amt = margin_amt;
	}

	public String getBorrow_amt() {
		return borrow_amt;
	}

	public void setBorrow_amt(String borrow_amt) {
		this.borrow_amt = borrow_amt;
	}

	public String getFund_amt() {
		return fund_amt;
	}

	public void setFund_amt(String fund_amt) {
		this.fund_amt = fund_amt;
	}

	public String getFund_start_date() {
		return fund_start_date;
	}

	public void setFund_start_date(String fund_start_date) {
		this.fund_start_date = fund_start_date;
	}

	public String getFund_end_date() {
		return fund_end_date;
	}

	public void setFund_end_date(String fund_end_date) {
		this.fund_end_date = fund_end_date;
	}

	public String getGuard_line_one() {
		return guard_line_one;
	}

	public void setGuard_line_one(String guard_line_one) {
		this.guard_line_one = guard_line_one;
	}

	public String getGuard_line_two() {
		return guard_line_two;
	}

	public void setGuard_line_two(String guard_line_two) {
		this.guard_line_two = guard_line_two;
	}

	public String getClear_line() {
		return clear_line;
	}

	public void setClear_line(String clear_line) {
		this.clear_line = clear_line;
	}

	@Override
	public String toString() {
		return "QueryAccountResp [user_id=" + user_id + ", open_state="
				+ open_state + ", account_name=" + account_name
				+ ", account_no=" + account_no + ", operator_id=" + operator_id
				+ ", password=" + password + ", margin_amt=" + margin_amt
				+ ", borrow_amt=" + borrow_amt + ", fund_amt=" + fund_amt
				+ ", fund_start_date=" + fund_start_date + ", fund_end_date="
				+ fund_end_date + ", guard_line_one=" + guard_line_one
				+ ", guard_line_two=" + guard_line_two + ", clear_line="
				+ clear_line + "]";
	}

}