
package com.fenglianfinance.bill.domain.support;

import java.io.Serializable;


public class EnterpriseCriteria implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private boolean active;
    private String name;
   
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
