package com.moxian.ng.account.model;

/**
 * 由丰联配资系统提供的签名方式、公钥、私钥以及渠道号信息
 * 
 * @author wangli@flf77.com
 * @date 2015年3月4日 下午2:43:33
 */
public class SignatureInfo {

	/**
	 * 加密算法
	 */
	private String algorthm;
	/**
	 * 公钥
	 */
	private String pubKey;
	/**
	 * 私钥
	 */
	private String priKey;
	/**
	 * 渠道号
	 */
	private String channelNo;
	/**
	 * 丰联配资账户系统请求地址
	 */
	private String reqUrl;
	/**
	 * 丰联配资账户系统响应地址前缀
	 */
	private String prefixRetUrl;

	public String getAlgorthm() {
		return algorthm;
	}

	public void setAlgorthm(String algorthm) {
		this.algorthm = algorthm;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public String getPriKey() {
		return priKey;
	}

	public void setPriKey(String priKey) {
		this.priKey = priKey;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getPrefixRetUrl() {
		return prefixRetUrl;
	}

	public void setPrefixRetUrl(String prefixRetUrl) {
		this.prefixRetUrl = prefixRetUrl;
	}

	@Override
	public String toString() {
		return "SignatureInfo [algorthm=" + algorthm + ", pubKey=" + pubKey
				+ ", priKey=" + priKey + ", channelNo=" + channelNo
				+ ", reqUrl=" + reqUrl + ", prefixRetUrl=" + prefixRetUrl + "]";
	}

}
