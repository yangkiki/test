package com.fenglianfinance.bill.report.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RepayMentDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String enterpriseName;

    private String name;

    private String type;

    private int duration;

    private LocalDateTime repaymentDeadline;

    private float annualPercentageRate;

    private BigDecimal totalAmount;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getRepaymentDeadline() {
        return repaymentDeadline;
    }

    public void setRepaymentDeadline(LocalDateTime repaymentDeadline) {
        this.repaymentDeadline = repaymentDeadline;
    }

    public float getAnnualPercentageRate() {
        return annualPercentageRate;
    }

 
    public RepayMentDetails() {
        super();
    }

    public void setAnnualPercentageRate(float annualPercentageRate) {
        this.annualPercentageRate = annualPercentageRate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public RepayMentDetails(String enterprise, String name, String type, int duration,
            LocalDateTime repaymentDeadline, float annualPercentageRate, BigDecimal totalAmount) {
        super();
        this.enterpriseName = enterprise;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.repaymentDeadline = repaymentDeadline;
        this.annualPercentageRate = annualPercentageRate;
        this.totalAmount = totalAmount;
    }

}
