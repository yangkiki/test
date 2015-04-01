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
public class BackLogItemSearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private String serialNumber;

    private String type;

    private String status;

    private String active;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "BackLogItemSearchCriteria [serialNumber=" + serialNumber + ", type=" + type + ", status=" + status
                + ", active=" + active + "]";
    }

}
