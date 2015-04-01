package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;

/**
 * 客户资金冻结回调
 * 
 * @author wangli@flf77.com
 * @date 2015年2月2日 下午3:05:37
 */
public class FreezeFundsCallback implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cmdId;// "cmdId":"InitiativeTender",

	private String respCode;// "respCode":"000",

	private String respDesc;// "respDesc":"成功",

	private String ordId;// "ordId":"6000060000751360",

	private String ordDate;// "ordDate":"1234567890",

	private String usrCustId;// "usrCustId":"6000060000751360",

	private String transAmt;// "transAmt":"2.00",

	private String trxId;// "trxId":"6000060000751360",

	private String isFreeze;// "isFreeze":"Y",

	private String freezeOrdId;// "freezeOrdId":"6000060000736315",

	private String freezeTrxId;// "freezeTrxId":"MT00000212",

	public String getCmdId() {
		return cmdId;
	}

	public void setCmdId(String cmdId) {
		this.cmdId = cmdId;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}

	public String getUsrCustId() {
		return usrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		this.usrCustId = usrCustId;
	}

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	public String getTrxId() {
		return trxId;
	}

	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}

	public String getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}

	public String getFreezeOrdId() {
		return freezeOrdId;
	}

	public void setFreezeOrdId(String freezeOrdId) {
		this.freezeOrdId = freezeOrdId;
	}

	public String getFreezeTrxId() {
		return freezeTrxId;
	}

	public void setFreezeTrxId(String freezeTrxId) {
		this.freezeTrxId = freezeTrxId;
	}

}
