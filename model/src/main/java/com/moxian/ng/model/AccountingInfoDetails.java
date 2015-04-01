/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.model;

import java.io.Serializable;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
public class AccountingInfoDetails implements Serializable {

    private String acctId;

    private String acctCustId;

    private String auditDesc;

    private String auditStatus;

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

}
