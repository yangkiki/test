package com.moxian.ng.model;

import java.util.List;

/**
 * 配资申请相关参数
 * 
 * @author wangli@flf77.com
 * @date 2015年1月30日 下午2:27:34
 */
public class WithFundingConfs {
	private List<WithFundingBrokerageConfModel> withFundingBrokerageConfModelList;
	private List<WithFundingLinesConfModel> withFundingLinesConfModelList;

	public List<WithFundingBrokerageConfModel> getWithFundingBrokerageConfModelList() {
		return withFundingBrokerageConfModelList;
	}

	public void setWithFundingBrokerageConfModelList(
			List<WithFundingBrokerageConfModel> withFundingBrokerageConfModelList) {
		this.withFundingBrokerageConfModelList = withFundingBrokerageConfModelList;
	}

	public List<WithFundingLinesConfModel> getWithFundingLinesConfModelList() {
		return withFundingLinesConfModelList;
	}

	public void setWithFundingLinesConfModelList(
			List<WithFundingLinesConfModel> withFundingLinesConfModelList) {
		this.withFundingLinesConfModelList = withFundingLinesConfModelList;
	}

}
