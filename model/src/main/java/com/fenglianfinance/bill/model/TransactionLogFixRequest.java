package com.fenglianfinance.bill.model;

import java.time.LocalDate;

public class TransactionLogFixRequest {

    private String type;

    private LocalDate beginDate;

    private LocalDate endDate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "TransactionLogFixRequest{" + "type=" + type + ", beginDate=" + beginDate + ", endDate=" + endDate + '}';
    }
}
