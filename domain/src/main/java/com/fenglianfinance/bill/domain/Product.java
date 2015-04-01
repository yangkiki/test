/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import com.fenglianfinance.bill.domain.support.AuditableEntity;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

//import java.time.temporal.TemporalUnit;

/**
 *
 * @author hansy
 */
@Entity
@Table(name = "products", uniqueConstraints = { @UniqueConstraint(columnNames = "bill_id") })
public class Product extends AuditableEntity<UserAccount, Long> {

    private static final long serialVersionUID = 1L;

    public enum InterestAccrualType {

        T, //T
        T_PLUS_ONE, //T+1
    }

    public enum Type {

		NEWBIE, // 新手
		HOT, // 爆款
		DEMAND, // 活期
		FIXED, // 定期
		WITHFUNDING_DAY, // 按天配资
		WITHFUNDING_MONTH; // 按月配资
	}

    public enum Status {

        FOR_SALE, // 1.待售 
        ON_SALE, //2.热卖中 
        SOLD_OUT, //3.售罄 
        COMPLETED;//4.项目结束（即完成本息归还） 
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 40)
    private Type type;

    @Column(name = "name", length = 80)
    private String name;

    @Column(name = "discription", length = 1000)
    private String discription;

    @Column(name = "annual_percentage_rate")
    private float annualPercentageRate = 0.0f;

    @Column(name = "duration")
    @Min(1)
    private int duration = 1;

    @Column(name = "on_sale_date")
    private LocalDateTime onSaleDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "repayment_deadline")
    private LocalDateTime repaymentDeadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "sold_count")
    private int soldCount;

    // process information
    @Column(name = "reward_msg")
    private String rewardMsg;

    @ManyToOne
    @JoinColumn(name = "license_id")
    private Post license;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Column(name = "promoted")
    private boolean promoted = false;

    @Embedded
    @AttributeOverrides(value = { @AttributeOverride(column = @Column(name = "purchase_limit_min"), name = "min"),
            @AttributeOverride(column = @Column(name = "purchase_limit_max"), name = "max") })
    private IntRange purchaseLimit;

    //    @Embedded
    //    @AttributeOverrides(value = {
    //        @AttributeOverride(column = @Column(name = "issued_from"), name = "from"),
    //        @AttributeOverride(column = @Column(name = "issued_to"), name = "to")})
    //    private DateRange issuedDateRange;
    @Enumerated(EnumType.STRING)
    @Column(name = "interest_accrual_type")
    private InterestAccrualType interestAccrualType;

    //结息日期
    @Column(name = "interest_settled_date")
    private LocalDateTime interestSettledDate;

    @Column(name = "completed_date")
    private LocalDate completedDate;

    @Column(name = "left_count")
    private int leftCount = 0;

    @Column(name = "sold_percentage")
    private int soldPercentage = 0;

    @Column(name = "is_active")
    private boolean active = true;
    
    @Column(name = "published_to_acct")
    private boolean publishedToAcct = false;
    
    @Column(name = "published_to_acct_date")
    private LocalDateTime publishedToAcctDate ;

	@ManyToOne
	@JoinColumn(name = "with_funding_info_id")
	private WithFundingInfos withFundingInfos;

	@PrePersist
	public void prePersist() {
		updateCompleteDate();
		updateLeftCount();
		updateSoldPercentage();
	}

    @PreUpdate
    public void preUpdate() {
        //updateCompleteDate();
        updateLeftCount();
        updateSoldPercentage();
    }

    private void updateCompleteDate() {
        if (duration > 0 && this.getOnSaleDate() != null) {
            this.completedDate = this.getOnSaleDate().toLocalDate().plus(duration, ChronoUnit.DAYS);
        }
    }

    private void updateLeftCount() {
        if (this.totalAmount != null && this.totalAmount.intValue() > 0) {
            this.leftCount = this.totalAmount.divide(unitPrice, RoundingMode.HALF_UP).intValue() - this.soldCount;
        }
    }

    private void updateSoldPercentage() {
        if (this.totalAmount != null && this.totalAmount.intValue() > 0) {
            this.soldPercentage = this.unitPrice.multiply(new BigDecimal(this.soldCount))
                    .divide(this.totalAmount, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue();
        }
    }

    public boolean isPublishedToAcct() {
        return publishedToAcct;
    }

    public void setPublishedToAcct(boolean publishedToAcct) {
        this.publishedToAcct = publishedToAcct;
    }

    public LocalDateTime getPublishedToAcctDate() {
        return publishedToAcctDate;
    }

    public void setPublishedToAcctDate(LocalDateTime publishedToAcctDate) {
        this.publishedToAcctDate = publishedToAcctDate;
    }
    
   
    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public void setSoldPercentage(int soldPercentage) {
        this.soldPercentage = soldPercentage;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public int getLeftCount() {
        return this.leftCount;
    }

    public int getSoldPercentage() {
        return this.soldPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
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

    public LocalDateTime getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(LocalDateTime onSaleDate) {
        this.onSaleDate = onSaleDate;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public IntRange getPurchaseLimit() {
        return purchaseLimit;
    }

    public void setPurchaseLimit(IntRange purchaseLimit) {
        this.purchaseLimit = purchaseLimit;
    }

    //    public DateRange getIssuedDateRange() {
    //        return issuedDateRange;
    //    }
    //
    //    public void setIssuedDateRange(DateRange issuedDateRange) {
    //        this.issuedDateRange = issuedDateRange;
    //    }
    public InterestAccrualType getInterestAccrualType() {
        return interestAccrualType;
    }

    public void setInterestAccrualType(InterestAccrualType interestAccrualType) {
        this.interestAccrualType = interestAccrualType;
    }

    public LocalDateTime getInterestSettledDate() {
        return interestSettledDate;
    }

    public void setInterestSettledDate(LocalDateTime interestSettledDate) {
        this.interestSettledDate = interestSettledDate;
    }

    public String getRewardMsg() {
        return rewardMsg;
    }

    public void setRewardMsg(String rewardMsg) {
        this.rewardMsg = rewardMsg;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public Post getLicense() {
        return license;
    }

    public void setLicense(Post license) {
        this.license = license;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

	public WithFundingInfos getWithFundingInfos() {
		return withFundingInfos;
	}

	public void setWithFundingInfos(WithFundingInfos withFundingInfos) {
		this.withFundingInfos = withFundingInfos;
	}

	@Override
	public String toString() {
		return "Product [type=" + type + ", name=" + name + ", discription="
				+ discription + ", annualPercentageRate="
				+ annualPercentageRate + ", duration=" + duration
				+ ", onSaleDate=" + onSaleDate + ", totalAmount=" + totalAmount
				+ ", repaymentDeadline=" + repaymentDeadline + ", status="
				+ status + ", unitPrice=" + unitPrice + ", soldCount="
				+ soldCount + ", rewardMsg=" + rewardMsg + ", license="
				+ license + ", bill=" + bill + ", enterprise=" + enterprise
				+ ", promoted=" + promoted + ", purchaseLimit=" + purchaseLimit
				+ ", interestAccrualType=" + interestAccrualType
				+ ", interestSettledDate=" + interestSettledDate
				+ ", completedDate=" + completedDate + ", leftCount="
				+ leftCount + ", soldPercentage=" + soldPercentage
				+ ", active=" + active + ", withFundingInfos="
				+ withFundingInfos + "]";
	}

}
