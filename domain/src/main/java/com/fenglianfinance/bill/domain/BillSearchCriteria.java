/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author gao
 */
public class BillSearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private String active;
    private String status;
    private BigDecimal denominationStart;
    private BigDecimal denominationEnd;

    private LocalDate start;
    private LocalDate end;

    private String keyword;

    private String poolStatus;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getDenominationStart() {
        return denominationStart;
    }

    public void setDenominationStart(BigDecimal denominationStart) {
        this.denominationStart = denominationStart;
    }

    public BigDecimal getDenominationEnd() {
        return denominationEnd;
    }

    public void setDenominationEnd(BigDecimal denominationEnd) {
        this.denominationEnd = denominationEnd;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getPoolStatus() {
        return poolStatus;
    }

    public void setPoolStatus(String poolStatus) {
        this.poolStatus = poolStatus;
    }

    @Override
    public String toString() {
        return "BillSearchCriteria [active=" + active + ", status=" + status + ", denominationStart="
                + denominationStart + ", denominationEnd=" + denominationEnd + ", start=" + start + ", end=" + end
                + ", keyword=" + keyword + ", poolStatus=" + poolStatus + "]";
    }

}
