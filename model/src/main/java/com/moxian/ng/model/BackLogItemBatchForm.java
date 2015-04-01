package com.moxian.ng.model;

import java.io.Serializable;
import java.util.List;

public class BackLogItemBatchForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public BackLogItemBatchForm() {}

    private List<Long> billIds;

    private Long backLogId;

    private String type;

    public List<Long> getBillIds() {
        return billIds;
    }

    public void setBillIds(List<Long> billIds) {
        this.billIds = billIds;
    }

    public Long getBackLogId() {
        return backLogId;
    }

    public void setBackLogId(Long backLogId) {
        this.backLogId = backLogId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
