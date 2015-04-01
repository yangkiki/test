package com.moxian.ng.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.moxian.ng.domain.support.AuditableEntity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author hantsy
 *
 */
@Entity
@Table(name = "posts")
public class Post extends AuditableEntity<UserAccount, Long> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public enum Type {

        ANNOUNCEMENT,
        NEWS,
        LICENSE
    }

    public enum Status {

        DRAFT,
        PUBLISHED
    }

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    @Size(max = 2000)
    private String content;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type = Type.ANNOUNCEMENT;

    @Column(name = "is_active")
    private boolean active = true;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        if (active != other.active)
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (status != other.status)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (type != other.type)
            return false;
        return true;
    }
    

}
