package com.moxian.ng.account.model;

/**
 * 渠道往丰联配资系统进行子账户开户，消息发送body
 * 
 * @author wangli@flf77.com
 * @date 2015年3月5日 下午4:33:36
 */
public class RegisterAccountReqBody {

	private AgreementInfoReq agreementInfoReq;

	private RegisterAccountReq registerAccountReq;

	public AgreementInfoReq getAgreementInfoReq() {
		return agreementInfoReq;
	}

	public void setAgreementInfoReq(AgreementInfoReq agreementInfoReq) {
		this.agreementInfoReq = agreementInfoReq;
	}

	public RegisterAccountReq getRegisterAccountReq() {
		return registerAccountReq;
	}

	public void setRegisterAccountReq(RegisterAccountReq registerAccountReq) {
		this.registerAccountReq = registerAccountReq;
	}

	@Override
	public String toString() {
		return "RegisterAccountReqBody [agreementInfoReq=" + agreementInfoReq
				+ ", registerAccountReq=" + registerAccountReq + "]";
	}

}
