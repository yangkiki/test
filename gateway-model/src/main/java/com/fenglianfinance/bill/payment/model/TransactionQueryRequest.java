/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;

/**
 *
 * @author hansy
 */
public class TransactionQueryRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public String beginDate;
    public String endDate;
    public String pageNum="1";
    public String pageSize="50";
    public String queryTransType; //交易查询类型   放款交易查询LOANS   还款交易REPAYMENT   投标交易PAYMENT    取现交易WITHDRAW

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getQueryTransType() {
        return queryTransType;
    }

    public void setQueryTransType(String queryTransType) {
        this.queryTransType = queryTransType;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "TransactionQueryRequest{" + "beginDate=" + beginDate + ", endDate=" + endDate + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", queryTransType=" + queryTransType + '}';
    }

}
