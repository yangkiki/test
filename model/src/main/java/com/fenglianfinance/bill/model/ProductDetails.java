package com.fenglianfinance.bill.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductDetails {

    private Long id;

    private String name;

    private String type;

    private String discription;

    private float annualPercentageRate = 0.0f;

    private int duration = 1;

    private LocalDateTime dueOnSale;

    private BigDecimal totalAmount = BigDecimal.ZERO;

    private PostDetails license;

    private BillDetails bill;

    private LocalDateTime repaymentDeadline;

    private String status;

    private BigDecimal unitPrice;

    private int soldCount;

    private boolean active = true;

    private IntRangeInfo purchaseLimit;

    private DateRangeInfo issuedDateRange;

    private DateRangeInfo interestAccruedDateRange;

    private String interestAccrualType;

    private LocalDateTime interestSettledDate;

    private LocalDateTime onSaleDate;

    //private BigDecimal residusCount;

    private String rewardMsg;

    private boolean promoted = false;

    //private int soldPercent;

    private int leftCount;

    private int soldPercentage;

    private LocalDate completedDate;

    private Long enterpriseId;

    private String userAccountId;
    
    private EnterpriseDetails  enterprise; 

    public EnterpriseDetails getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseDetails enterprise) {
        this.enterprise = enterprise;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    private String billAcceptingBankName; //billAcceptingBankName 

	private String withFundingInfosUserName;
	private String withFundingInfosUserMobileNumber;
	private String withFundingInfosUserIdCardVerificationCardName;
	private String withFundingInfosUserCreatedDate;

	public LocalDate getCompletedDate() {
		return completedDate;
	}

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public int getLeftCount() {
        return this.leftCount;
    }

    public int getSoldPercentage() {
        return this.soldPercentage;
    }

    public void setSoldPercentage(int soldPercentage) {
        this.soldPercentage = soldPercentage;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    //    public int getSoldPercent() {
    //        this.soldPercent = getSoldPercentage();
    //        return this.soldPercent;
    //    }
    //
    //    public void setSoldPercent(int soldPercent) {
    //        this.soldPercent = soldPercent;
    //    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public String getRewardMsg() {
        return rewardMsg;
    }

    public String getBillAcceptingBankName() {
        return billAcceptingBankName;
    }

    public void setBillAcceptingBankName(String billAcceptingBankName) {
        this.billAcceptingBankName = billAcceptingBankName;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public void setRewardMsg(String rewardMsg) {
        this.rewardMsg = rewardMsg;
    }

    //    public BigDecimal getResidusCount() {
    //        this.residusCount = new BigDecimal(getLeftCount()); //getLeftCount()
    //        return this.residusCount;
    //    }

    public PostDetails getLicense() {
        return license;
    }

    public void setLicense(PostDetails license) {
        this.license = license;
    }

    public BillDetails getBill() {
        return bill;
    }

    public void setBill(BillDetails bill) {
        this.bill = bill;
    }

    //    public void setResidusCount(BigDecimal residusCount) {
    //        this.residusCount = residusCount;
    //    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public LocalDateTime getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(LocalDateTime onSaleDate) {
        this.onSaleDate = onSaleDate;
    }

	public String getWithFundingInfosUserName() {
		return withFundingInfosUserName;
	}

	public void setWithFundingInfosUserName(String withFundingInfosUserName) {
		if (null != withFundingInfosUserName) {
			this.withFundingInfosUserName = withFundingInfosUserName.replace(
					withFundingInfosUserName.substring(1,
							withFundingInfosUserName.length()), "**");
		} else {
			this.withFundingInfosUserName = withFundingInfosUserName;
		}
	}

	public String getWithFundingInfosUserMobileNumber() {
		return withFundingInfosUserMobileNumber;
	}

	public void setWithFundingInfosUserMobileNumber(
			String withFundingInfosUserMobileNumber) {
		if (null != withFundingInfosUserMobileNumber) {
			String headStr = withFundingInfosUserMobileNumber.substring(0, 3);
			String tailStr = withFundingInfosUserMobileNumber
					.substring(withFundingInfosUserMobileNumber.length() - 3);
			this.withFundingInfosUserMobileNumber = headStr + "*****" + tailStr;
		} else {
			this.withFundingInfosUserMobileNumber = withFundingInfosUserMobileNumber;
		}
	}

	public String getWithFundingInfosUserIdCardVerificationCardName() {
		return withFundingInfosUserIdCardVerificationCardName;
	}

	public void setWithFundingInfosUserIdCardVerificationCardName(
			String withFundingInfosUserIdCardVerificationCardName) {
		this.withFundingInfosUserIdCardVerificationCardName = withFundingInfosUserIdCardVerificationCardName;
	}

	public String getWithFundingInfosUserCreatedDate() {
		return withFundingInfosUserCreatedDate;
	}

	public void setWithFundingInfosUserCreatedDate(
			String withFundingInfosUserCreatedDate) {
		this.withFundingInfosUserCreatedDate = withFundingInfosUserCreatedDate;
	}

	@Override
	public String toString() {
		return "ProductDetails [id=" + id + ", name=" + name + ", type=" + type
				+ ", discription=" + discription + ", annualPercentageRate="
				+ annualPercentageRate + ", duration=" + duration
				+ ", dueOnSale=" + dueOnSale + ", totalAmount=" + totalAmount
				+ ", license=" + license + ", bill=" + bill
				+ ", repaymentDeadline=" + repaymentDeadline + ", status="
				+ status + ", unitPrice=" + unitPrice + ", soldCount="
				+ soldCount + ", active=" + active + ", purchaseLimit="
				+ purchaseLimit + ", issuedDateRange=" + issuedDateRange
				+ ", interestAccruedDateRange=" + interestAccruedDateRange
				+ ", interestAccrualType=" + interestAccrualType
				+ ", interestSettledDate=" + interestSettledDate
				+ ", onSaleDate=" + onSaleDate + ", rewardMsg=" + rewardMsg
				+ ", promoted=" + promoted + ", leftCount=" + leftCount
				+ ", soldPercentage=" + soldPercentage + ", completedDate="
				+ completedDate + ", enterpriseId=" + enterpriseId
				+ ", billAcceptingBankName=" + billAcceptingBankName
				+ ", withFundingInfosUserName=" + withFundingInfosUserName
				+ ", withFundingInfosUserMobileNumber="
				+ withFundingInfosUserMobileNumber
				+ ", withFundingInfosUserIdCardVerificationCardName="
				+ withFundingInfosUserIdCardVerificationCardName
				+ ", withFundingInfosUserCreatedDate="
				+ withFundingInfosUserCreatedDate + "]";
	}

}
