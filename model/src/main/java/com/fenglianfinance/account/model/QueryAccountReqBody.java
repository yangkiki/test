package com.moxian.ng.account.model;

/**
 * 渠道往丰联配资系统进行账户信息查询，消息发送body
 * 
 * @author wangli@flf77.com
 * @date 2015年3月18日 下午2:47:48
 */
public class QueryAccountReqBody {
	private AgreementInfoReq agreementInfoReq;

	private QueryAccountReq queryAccountReq;

	public AgreementInfoReq getAgreementInfoReq() {
		return agreementInfoReq;
	}

	public void setAgreementInfoReq(AgreementInfoReq agreementInfoReq) {
		this.agreementInfoReq = agreementInfoReq;
	}

	public QueryAccountReq getQueryAccountReq() {
		return queryAccountReq;
	}

	public void setQueryAccountReq(QueryAccountReq queryAccountReq) {
		this.queryAccountReq = queryAccountReq;
	}

	@Override
	public String toString() {
		return "QueryAccountReqBody [agreementInfoReq=" + agreementInfoReq
				+ ", queryAccountReq=" + queryAccountReq + "]";
	}

}
