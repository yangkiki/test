package com.moxian.ng.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 *
 */
public class PostDetails implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String content;

    private String type;

    private boolean active;

    private String status;

    private NameValue createdBy;

    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NameValue getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(NameValue createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

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

    @Override
    public String toString() {
        return "PostDetails{" + "id=" + id + ", title=" + title + ", content=" + content + ", type=" + type + ", active=" + active + ", status=" + status + ", createdBy=" + createdBy + ", createdDate=" + createdDate + '}';
    }

}
