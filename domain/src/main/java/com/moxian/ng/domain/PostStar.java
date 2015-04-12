/**
 * 
 */
package com.moxian.ng.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.moxian.ng.domain.support.AuditableEntity;

/**
 * @author yang
 *
 */
@Entity
@Table(name = "post_stars")
public class PostStar extends AuditableEntity<UserAccount, Long> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public enum Status {
        NORMAL, UNNORMAL
    }

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "active")
    private boolean active;

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
