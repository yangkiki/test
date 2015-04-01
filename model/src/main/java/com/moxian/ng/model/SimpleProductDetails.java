package com.moxian.ng.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SimpleProductDetails {

    private Long id; // id

    private String name; // name 

    private float annualPercentageRate; // percent

    private int duration; // duration

    private String billAcceptingBankName; //billAcceptingBankName 

    private String status; // status

    private String type;

    private LocalDateTime onSaleDate;

    private String rewardMsg; // rewardMsg

    private LocalDateTime repaymentDeadline; //rangTime

    private LocalDateTime offSaleDate; // end

    private LocalDateTime interestSettledDate;

    private BigDecimal unitPrice;

    private int soldPercent;
    
    private int soldCount;

    private int leftCount;

    private int soldPercentage;
    
    private BigDecimal totalAmount = BigDecimal.ZERO; // totalvalue
    
    private String description;
    
    private Long licenseId;

    public int getSoldPercentage() {
        return soldPercentage;
    }

    public void setSoldPercentage(int soldPercentage) {
        this.soldPercentage = soldPercentage;
    }
    
    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getSoldPercent() {
        this.soldPercent = getSoldPercentage();
        return this.soldPercent;
    }

    public void setSoldPercent(int soldPercent) {
        this.soldPercent = soldPercent;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public String getRewardMsg() {
        return rewardMsg;
    }

    public void setRewardMsg(String rewardMsg) {
        this.rewardMsg = rewardMsg;
    }

    public LocalDateTime getRepaymentDeadline() {
        return repaymentDeadline;
    }

    public void setRepaymentDeadline(LocalDateTime repaymentDeadline) {
        this.repaymentDeadline = repaymentDeadline;
    }

    public LocalDateTime getOffSaleDate() {
        return offSaleDate;
    }

    public void setOffSaleDate(LocalDateTime offSaleDate) {
        this.offSaleDate = offSaleDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(LocalDateTime onSaleDate) {
        this.onSaleDate = onSaleDate;
        this.offSaleDate = this.onSaleDate.plusDays(this.duration);
    }

    public String getBillAcceptingBankName() {
        return billAcceptingBankName;
    }

    public void setBillAcceptingBankName(String billAcceptingBankName) {
        this.billAcceptingBankName = billAcceptingBankName;
    }

    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getInterestSettledDate() {
        return interestSettledDate;
    }

    public void setInterestSettledDate(LocalDateTime interestSettledDate) {
        this.interestSettledDate = interestSettledDate;
    }

    @Override
    public String toString() {
        return "SimpleProductDetails{" + "id=" + id + ", name=" + name + ", annualPercentageRate="
                + annualPercentageRate + ", duration=" + duration + ", totalAmount=" + totalAmount
                + ", billAcceptingBankName=" + billAcceptingBankName + ", status=" + status + ", type=" + type
                + ", onSaleDate=" + onSaleDate + ", rewardMsg=" + rewardMsg + ", repaymentDeadline="
                + repaymentDeadline + ", offSaleDate=" + offSaleDate + ", soldCount=" + soldCount + ", unitPrice="
                + unitPrice + ", soldPercent=" + soldPercent + ", description=" + description + ", licenseId="
                + licenseId + '}';
    }

}
