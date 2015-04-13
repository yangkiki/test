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

import com.moxian.ng.domain.support.PersistableEntity;

/**
 * @author yang
 *
 */
@Entity
@Table(name = "post_comments")
public class PostComment extends PersistableEntity<Long> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public enum Status {
        ACTIVE, INACTIVE
    }

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "comments")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "publish_user_id")
    private UserAccount publishUser;

    @ManyToOne
    @JoinColumn(name = "reply_user_id")
    private UserAccount replyUser;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public Status status;
    
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
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the publishUser
     */
    public UserAccount getPublishUser() {
        return publishUser;
    }

    /**
     * @param publishUser the publishUser to set
     */
    public void setPublishUser(UserAccount publishUser) {
        this.publishUser = publishUser;
    }

    /**
     * @return the replyUser
     */
    public UserAccount getReplyUser() {
        return replyUser;
    }

    /**
     * @param replyUser the replyUser to set
     */
    public void setReplyUser(UserAccount replyUser) {
        this.replyUser = replyUser;
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
