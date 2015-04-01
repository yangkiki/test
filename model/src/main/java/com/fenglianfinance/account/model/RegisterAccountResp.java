package com.fenglianfinance.account.model;


/**
 * 开户响应的业务参数
 * 
 * @author wangli@flf77.com
 * @date 2015年3月6日 下午4:20:06
 */
public class RegisterAccountResp {
	/**
	 * 配资系统订单号
	 */
	private String pz_order_no;
	/**
	 * 开户状态
	 */
	private String open_state;
	/**
	 * 供应商标识
	 */
	private String supplier_no;
	/**
	 * 开户时间 格式：YYYYMMDDHH24MISS 返回码为成功时该字段有值，否则为空
	 */
	private String open_time;
	/**
	 * 股票子账号名称
	 */
	private String account_name;

	/**
	 * 股票子账号
	 */
	private String account_no;
	/**
	 * 账户操作员帐号
	 */
	private String operator_id;
	/**
	 * 子账户密码
	 */
	private String password;

	public String getPz_order_no() {
		return pz_order_no;
	}

	public void setPz_order_no(String pz_order_no) {
		this.pz_order_no = pz_order_no;
	}

	public String getOpen_state() {
		return open_state;
	}

	public void setOpen_state(String open_state) {
		this.open_state = open_state;
	}

	public String getSupplier_no() {
		return supplier_no;
	}

	public void setSupplier_no(String supplier_no) {
		this.supplier_no = supplier_no;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
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

	@Override
	public String toString() {
		return "RegisterAccountResp [pz_order_no=" + pz_order_no
				+ ", open_state=" + open_state + ", supplier_no=" + supplier_no
				+ ", open_time=" + open_time + ", account_name=" + account_name
				+ ", account_no=" + account_no + ", operator_id=" + operator_id
				+ ", password=" + password + "]";
	}

}
