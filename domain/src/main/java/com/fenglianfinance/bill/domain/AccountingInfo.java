/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author hantsy
 */
@Embeddable
public class AccountingInfo implements Serializable {

    @Column(name = "acct_id")
    private String acctId;

    @Column(name = "acct_cust_id")
    private String acctCustId;

    @Column(name = "acct_audit_desc")
    private String auditDesc;

    @Column(name = "acct_audit_status")
    private String auditStatus;

    public AccountingInfo() {
    }

    public AccountingInfo(String acctId, String acctCustId) {
        this.acctId = acctId;
        this.acctCustId = acctCustId;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String accountingId) {
        this.acctId = accountingId;
    }

    public String getAcctCustId() {
        return acctCustId;
    }

    public void setAcctCustId(String acctCustId) {
        this.acctCustId = acctCustId;
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Override
    public String toString() {
        return "AccountingInfo{" + "acctId=" + acctId + ", acctCustId=" + acctCustId + ", auditDesc=" + auditDesc + ", auditStatus=" + auditStatus + '}';
    }

}
