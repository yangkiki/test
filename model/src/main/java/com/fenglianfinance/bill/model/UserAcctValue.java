package com.fenglianfinance.bill.model;

import java.io.Serializable;

public class UserAcctValue implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String accountingInfoAcctCustId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountingInfoAcctCustId() {
        return accountingInfoAcctCustId;
    }

    public void setAccountingInfoAcctCustId(String accountingInfoAcctCustId) {
        this.accountingInfoAcctCustId = accountingInfoAcctCustId;
    }

    @Override
    public String toString() {
        return "UserAcctValue{" + "id=" + id + ", name=" + name + ", accountingInfoAcctCustId=" + accountingInfoAcctCustId + '}';
    }

}
