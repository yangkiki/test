package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;

/**
 * 投标撤销
 * 
 * @author wangli@flf77.com
 * @date 2015年2月12日 上午10:56:27
 */
public class TenderCancleCallback implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** 消息类型，此处为：TenderCancle */
	private String cmdId;// "cmdId":"TenderCancle",
	/** 应答返回码，000--调用成功 */
	private String respCode;// "respCode":"000",
	/** 应答描述 */
	private String respDesc;// "respDesc":"成功",
	/** 订单号 */
	private String ordId;// "ordId":"6000060000751360",
	/** 订单日期 */
	private String ordDate;// "ordDate":"1234567890",
	/** 交易金额 */
	private String transAmt;// "transAmt":"2.00",
	/** 用户客户号 */
	private String usrCustId;// "usrCustId":"6000060000757435",
	/** 是否解冻 */
	private String isUnFreeze;// "isUnFreeze":"N",
	/** 解冻订单号 */
	private String unFreezeOrdId;// "unFreezeOrdId":"",
	/** 冻结标识 */
	private String freezeTrxId;// "freezeTrxId":"001502111234567890"

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

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	public String getUsrCustId() {
		return usrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		this.usrCustId = usrCustId;
	}

	public String getIsUnFreeze() {
		return isUnFreeze;
	}

	public void setIsUnFreeze(String isUnFreeze) {
		this.isUnFreeze = isUnFreeze;
	}

	public String getUnFreezeOrdId() {
		return unFreezeOrdId;
	}

	public void setUnFreezeOrdId(String unFreezeOrdId) {
		this.unFreezeOrdId = unFreezeOrdId;
	}

	public String getFreezeTrxId() {
		return freezeTrxId;
	}

	public void setFreezeTrxId(String freezeTrxId) {
		this.freezeTrxId = freezeTrxId;
	}

	@Override
	public String toString() {
		return "TenderCancleCallback [cmdId=" + cmdId + ", respCode="
				+ respCode + ", respDesc=" + respDesc + ", ordId=" + ordId
				+ ", ordDate=" + ordDate + ", transAmt=" + transAmt
				+ ", usrCustId=" + usrCustId + ", isUnFreeze=" + isUnFreeze
				+ ", unFreezeOrdId=" + unFreezeOrdId + ", freezeTrxId="
				+ freezeTrxId + "]";
	}

}
