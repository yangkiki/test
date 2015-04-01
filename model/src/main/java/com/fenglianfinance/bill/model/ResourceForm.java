package com.fenglianfinance.bill.model;

import java.io.Serializable;

public class ResourceForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer parentId;

    private String name;

    private String code;

    private String url;

    private String type;

    private Integer sortIndex;

    private String status;

    private String description;

    public ResourceForm(Integer parentId, String name, String code, String url, String type, Integer sortIndex,
            String status, String description) {
        super();
        this.parentId = parentId;
        this.name = name;
        this.code = code;
        this.url = url;
        this.type = type;
        this.sortIndex = sortIndex;
        this.status = status;
        this.description = description;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ResourceForm [parentId=" + parentId + ", name=" + name + ", code=" + code + ", url=" + url + ", type="
                + type + ", sortIndex=" + sortIndex + ", status=" + status + ", description=" + description + "]";
    }

}
