package com.moxian.ng.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProductCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime start;
    private LocalDateTime end;
    private String name;
    private String type;
    private String status;
    private boolean active;
    private Long userId;
    private String promoted;

    public LocalDateTime getStart() {
        return start;
    }

    public String getPromoted() {
        return promoted;
    }

    public void setPromoted(String promoted) {
        this.promoted = promoted;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
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

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
