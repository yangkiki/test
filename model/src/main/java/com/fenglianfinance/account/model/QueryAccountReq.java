package com.moxian.ng.account.model;

/**
 * 子账户查询请求业务参数
 * 
 * @author wangli@flf77.com
 * @date 2015年3月16日 上午11:41:01
 */
public class QueryAccountReq {
	/**
	 * 渠道系统订单号
	 */
	private String channelOrderNo;

	public String getChannelOrderNo() {
		return channelOrderNo;
	}

	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}

	@Override
	public String toString() {
		return "QueryAccountReq [channelOrderNo=" + channelOrderNo + "]";
	}

}
