package com.moxian.ng.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductForm {

    private String name;

    private String type;

    private String discription;

    private float annualPercentageRate = 0.0f;

    private int duration = 1;

    private LocalDateTime dueOnSale;

    private BigDecimal totalAmount = BigDecimal.ZERO;

    private IdValue licensefk;

    private LocalDateTime repaymentDeadline;

    private String status;

    private BigDecimal unitPrice;

    private boolean active = true;

    private IntRangeInfo purchaseLimit;

    private DateRangeInfo issuedDateRange;

    private DateRangeInfo interestAccruedDateRange;

    private String interestAccrualType;

    private LocalDateTime interestSettledDate;

    private LocalDateTime onSaleDate;

    private String rewardMsg;

    private IdValue billfk;

    private Long backLogItemId;

    private Long enterpriseId;

	private Long withFundingInfosId;

	public Long getWithFundingInfosId() {
		return withFundingInfosId;
	}

	public void setWithFundingInfosId(Long withFundingInfosId) {
		this.withFundingInfosId = withFundingInfosId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    private boolean promoted = false;

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public Long getBackLogItemId() {
        return backLogItemId;
    }

    public void setBackLogItemId(Long backLogItemId) {
        this.backLogItemId = backLogItemId;
    }

    public IdValue getBillfk() {
        return billfk;
    }

    public void setBillfk(IdValue billfk) {
        this.billfk = billfk;
    }

    public String getRewardMsg() {
        return rewardMsg;
    }

    public void setRewardMsg(String rewardMsg) {
        this.rewardMsg = rewardMsg;
    }

    public LocalDateTime getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(LocalDateTime onSaleDate) {
        this.onSaleDate = onSaleDate;
    }

    public String getInterestAccrualType() {
        return interestAccrualType;
    }

    public void setInterestAccrualType(String interestAccrualType) {
        this.interestAccrualType = interestAccrualType;
    }

    public LocalDateTime getInterestSettledDate() {
        return interestSettledDate;
    }

    public void setInterestSettledDate(LocalDateTime interestSettledDate) {
        this.interestSettledDate = interestSettledDate;
    }

    public float getAnnualPercentageRate() {
        return annualPercentageRate;
    }

    public void setAnnualPercentageRate(float annualPercentageRate) {
        this.annualPercentageRate = annualPercentageRate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getDueOnSale() {
        return dueOnSale;
    }

    public void setDueOnSale(LocalDateTime dueOnSale) {
        this.dueOnSale = dueOnSale;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getRepaymentDeadline() {
        return repaymentDeadline;
    }

    public void setRepaymentDeadline(LocalDateTime repaymentDeadline) {
        this.repaymentDeadline = repaymentDeadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public IntRangeInfo getPurchaseLimit() {
        return purchaseLimit;
    }

    public void setPurchaseLimit(IntRangeInfo purchaseLimit) {
        this.purchaseLimit = purchaseLimit;
    }

    public DateRangeInfo getIssuedDateRange() {
        return issuedDateRange;
    }

    public void setIssuedDateRange(DateRangeInfo issuedDateRange) {
        this.issuedDateRange = issuedDateRange;
    }

    public DateRangeInfo getInterestAccruedDateRange() {
        return interestAccruedDateRange;
    }

    public void setInterestAccruedDateRange(DateRangeInfo interestAccruedDateRange) {
        this.interestAccruedDateRange = interestAccruedDateRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public IdValue getLicensefk() {
        return licensefk;
    }

    public void setLicensefk(IdValue licensefk) {
        this.licensefk = licensefk;
    }
}
