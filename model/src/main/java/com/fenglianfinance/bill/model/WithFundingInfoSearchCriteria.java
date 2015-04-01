package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 配资信息查询条件
 * 
 * @author wangli@flf77.com
 * @date 2015年2月5日 下午7:25:49
 */
public class WithFundingInfoSearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long gearing; // 杠杆比例

	private String mode; // 配资类型

	private String[] statusArr; // 状态数组

	private boolean active = true;

	public Long getGearing() {
		return gearing;
	}

	public void setGearing(Long gearing) {
		this.gearing = gearing;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String[] getStatusArr() {
		return statusArr;
	}

	public void setStatusArr(String[] statusArr) {
		this.statusArr = statusArr;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "WithFundingInfoSearchCriteria [gearing=" + gearing + ", mode="
				+ mode + ", statusArr=" + Arrays.toString(statusArr)
				+ ", active=" + active + "]";
	}

}
