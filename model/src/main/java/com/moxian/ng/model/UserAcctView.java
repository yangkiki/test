package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class UserAcctView implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserAccountDetails userAccountDetails;
	private List<PurchaseOrderModel> recentPurchasedOrders;// 最新爆款交易
	private List<PurchaseOrderModel> maturePurchasedOrders;// 最近到期爆款交易

	private BigDecimal leftMoney = BigDecimal.ZERO;// 账户余额
	private BigDecimal earningsFromFixed = BigDecimal.ZERO;// 爆款,定期累计收益
	private BigDecimal earningsFromDemand = BigDecimal.ZERO;// 活期累计收益

	public UserAccountDetails getUserAccountDetails() {
		return userAccountDetails;
	}

	public void setUserAccountDetails(UserAccountDetails userAccountDetails) {
		this.userAccountDetails = userAccountDetails;
	}

	public List<PurchaseOrderModel> getRecentPurchasedOrders() {
		return recentPurchasedOrders;
	}

	public void setRecentPurchasedOrders(
			List<PurchaseOrderModel> recentPurchasedOrders) {
		this.recentPurchasedOrders = recentPurchasedOrders;
	}

	public List<PurchaseOrderModel> getMaturePurchasedOrders() {
		return maturePurchasedOrders;
	}

	public void setMaturePurchasedOrders(
			List<PurchaseOrderModel> maturePurchasedOrders) {
		this.maturePurchasedOrders = maturePurchasedOrders;
	}

	public BigDecimal getLeftMoney() {
		return leftMoney;
	}

	public void setLeftMoney(BigDecimal leftMoney) {
		this.leftMoney = leftMoney;
	}

	public BigDecimal getEarningsFromFixed() {
		return earningsFromFixed;
	}

	public void setEarningsFromFixed(BigDecimal earningsFromFixed) {
		this.earningsFromFixed = earningsFromFixed;
	}

	public BigDecimal getEarningsFromDemand() {
		return earningsFromDemand;
	}

	public void setEarningsFromDemand(BigDecimal earningsFromDemand) {
		this.earningsFromDemand = earningsFromDemand;
	}

	@Override
	public String toString() {
		return "UserAcctView [userAccountDetails=" + userAccountDetails
				+ ", recentPurchasedOrders=" + recentPurchasedOrders
				+ ", maturePurchasedOrders=" + maturePurchasedOrders
				+ ", leftMoney=" + leftMoney + ", earningsFromFixed="
				+ earningsFromFixed + ", earningsFromDemand="
				+ earningsFromDemand + "]";
	}
}