/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import com.fenglianfinance.bill.domain.support.PersistableEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author hantsy
 */
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder extends PersistableEntity<Long> {

    public enum Status {

        //NA,// default status, when is created
        PENDING_PAYMENT,// The order is placed, and notify user to pay.
        //WAITING_FOR_TRANSACTION,// payment is done by user side, but waiting for the response from bank interface or payment interface to confirm it.
        PAID,//payment is successful
        PAYMENT_FAILED,//payment is failed
        INTEREST_ACCRUED, //interest is accured
        IN_REPAYMENT,// copy the product status, in repayment.
        REPAYMENT_FAILED,//repaymetn failed.
        REPAYMENT_COMPLETED, // copy the completed status.
        CANCELED// canceled by user
    }

    @Column(name = "serial_number")
    private String serialNumber;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
//    private List<OrderItem> items = new ArrayList<>();
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    private BigDecimal amount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean active = true;

    @Column(name = "accrued_interest_amount")
    private BigDecimal accruedInterestAmount = BigDecimal.ZERO;

    @Column(name = "accrued_start_date")
    private LocalDate accruedStartDate;

    @Column(name = "accrued_end_date")
    private LocalDate accruedEndDate;

    @Column(name = "completed_date")
    private LocalDateTime completedDate;

    @Column(name = "paid_date")
    private LocalDateTime paidDate;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserAccount user;

    @Column(name = "placed_date")
    private LocalDateTime placedDate;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "daily_interests",
//            joinColumns = {
//                @JoinColumn(name = "order_id")
//            }
//    )
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DailyAccruedInterest> dailyInterests = new ArrayList<>();

    public void addDailyInterest(DailyAccruedInterest interest) {
        this.setAccruedInterestAmount(this.getAccruedInterestAmount().add(
            interest.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
        this.setAccruedEndDate(interest.getAccruedDate().toLocalDate());
        this.dailyInterests.add(interest);
    }

    public PurchaseOrder() {
        this.placedDate = LocalDateTime.now();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
    }

    public LocalDateTime getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDateTime paidDate) {
        this.paidDate = paidDate;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public LocalDateTime getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(LocalDateTime placedDate) {
        this.placedDate = placedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BigDecimal getAccruedInterestAmount() {
        return accruedInterestAmount;
    }

    public void setAccruedInterestAmount(BigDecimal accruedInterestAmount) {
        this.accruedInterestAmount = accruedInterestAmount;
    }

    public LocalDate getAccruedStartDate() {
        return accruedStartDate;
    }

    public void setAccruedStartDate(LocalDate accruedStartDate) {
        this.accruedStartDate = accruedStartDate;
    }

    public LocalDate getAccruedEndDate() {
        return accruedEndDate;
    }

    public void setAccruedEndDate(LocalDate accruedEndDate) {
        this.accruedEndDate = accruedEndDate;
    }

    public List<DailyAccruedInterest> getDailyInterests() {
        return dailyInterests;
    }

    public void setDailyInterests(List<DailyAccruedInterest> dailyInterests) {
        this.dailyInterests = dailyInterests;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" + "serialNumber=" + serialNumber + ", product=" + product + ", amount=" + amount + ", status=" + status + ", active=" + active + ", accruedInterestAmount=" + accruedInterestAmount + ", accruedStartDate=" + accruedStartDate + ", accruedEndDate=" + accruedEndDate + ", completedDate=" + completedDate + ", paidDate=" + paidDate + ", user=" + user + ", placedDate=" + placedDate + '}';
    }

}
