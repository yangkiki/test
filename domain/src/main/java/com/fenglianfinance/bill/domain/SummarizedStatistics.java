/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import com.fenglianfinance.bill.domain.support.PersistableEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author hantsy
 */
@Entity
@Table(name = "summarized_statistics")
public class SummarizedStatistics extends PersistableEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Column(name = "summarized_date")
    private LocalDate summarizedDate;

    @Column(name = "user_registration_cnt")
    private int userRegisterationCount;

    @Column(name = "id_verification_cnt")
    private int idCardVerificationCount;

    @Column(name = "incharge_user_cnt")
    private int inchargeUserCount;

    @Column(name = "total_incharge_amount")
    private BigDecimal totalInchargeAmount;

    @Column(name = "avg_incharge_amount")
    private BigDecimal avgInchargeAmount;

    @Column(name = "paid_user_cnt")
    private int paidUserCount;

    @Column(name = "payment_log_cnt")
    private int paymentLogCount;

    @Column(name = "total_payment_amount")
    private BigDecimal totalPaymentAmount;

    @Column(name = "avg_payment_amount")
    private BigDecimal avgPaymentAmount;

    @Column(name = "avg_payment_cnt")
    private float avgPaymentCount;

    @Column(name = "withdraw_user_cnt")
    private int withdrawUserCount;

    @Column(name = "total_withdraw_amount")
    private BigDecimal totalWithdrawAmount;

    @Column(name = "avg_withdraw_amount")
    private BigDecimal avgWithdrawAmount;

    @Column(name = "user_view_cnt")
    private long userViewCount;

    @Column(name = "page_view_cnt")
    private long pageViewCount;

    public LocalDate getSummarizedDate() {
        return summarizedDate;
    }

    public void setSummarizedDate(LocalDate summarizedDate) {
        this.summarizedDate = summarizedDate;
    }

    public int getUserRegisterationCount() {
        return userRegisterationCount;
    }

    public void setUserRegisterationCount(int userRegisterationCount) {
        this.userRegisterationCount = userRegisterationCount;
    }

    public int getIdCardVerificationCount() {
        return idCardVerificationCount;
    }

    public void setIdCardVerificationCount(int idCardVerificationCount) {
        this.idCardVerificationCount = idCardVerificationCount;
    }

    public int getInchargeUserCount() {
        return inchargeUserCount;
    }

    public void setInchargeUserCount(int inchargeUserCount) {
        this.inchargeUserCount = inchargeUserCount;
    }

    public BigDecimal getTotalInchargeAmount() {
        return totalInchargeAmount;
    }

    public void setTotalInchargeAmount(BigDecimal totalInchargeAmount) {
        this.totalInchargeAmount = totalInchargeAmount;
    }

    public BigDecimal getAvgInchargeAmount() {
        return avgInchargeAmount;
    }

    public void setAvgInchargeAmount(BigDecimal avgInchargeAmount) {
        this.avgInchargeAmount = avgInchargeAmount;
    }

    public int getPaidUserCount() {
        return paidUserCount;
    }

    public void setPaidUserCount(int paidUserCount) {
        this.paidUserCount = paidUserCount;
    }

    public int getPaymentLogCount() {
        return paymentLogCount;
    }

    public void setPaymentLogCount(int paymentLogCount) {
        this.paymentLogCount = paymentLogCount;
    }

    public BigDecimal getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    public BigDecimal getAvgPaymentAmount() {
        return avgPaymentAmount;
    }

    public void setAvgPaymentAmount(BigDecimal avgPaymentAmount) {
        this.avgPaymentAmount = avgPaymentAmount;
    }

    public float getAvgPaymentCount() {
        return avgPaymentCount;
    }

    public void setAvgPaymentCount(float avgPaymentCount) {
        this.avgPaymentCount = avgPaymentCount;
    }

    public int getWithdrawUserCount() {
        return withdrawUserCount;
    }

    public void setWithdrawUserCount(int withdrawUserCount) {
        this.withdrawUserCount = withdrawUserCount;
    }

    public BigDecimal getTotalWithdrawAmount() {
        return totalWithdrawAmount;
    }

    public void setTotalWithdrawAmount(BigDecimal totalWithdrawAmount) {
        this.totalWithdrawAmount = totalWithdrawAmount;
    }

    public BigDecimal getAvgWithdrawAmount() {
        return avgWithdrawAmount;
    }

    public void setAvgWithdrawAmount(BigDecimal avgWithdrawAmount) {
        this.avgWithdrawAmount = avgWithdrawAmount;
    }

    public long getUserViewCount() {
        return userViewCount;
    }

    public void setUserViewCount(long userViewCount) {
        this.userViewCount = userViewCount;
    }

    public long getPageViewCount() {
        return pageViewCount;
    }

    public void setPageViewCount(long pageViewCount) {
        this.pageViewCount = pageViewCount;
    }
    
    
    

}
