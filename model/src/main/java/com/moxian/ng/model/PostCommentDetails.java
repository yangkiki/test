/**
 * 
 */
package com.moxian.ng.model;

import java.io.Serializable;

/**
 * @author yang
 *
 */
public class PostCommentDetails implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private PostDetails post;

    private String comment;

    private UserAccountDetails publishUser;

    private UserAccountDetails replyUser;

    public String status;
    
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the post
     */
    public PostDetails getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(PostDetails post) {
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
    public UserAccountDetails getPublishUser() {
        return publishUser;
    }

    /**
     * @param publishUser the publishUser to set
     */
    public void setPublishUser(UserAccountDetails publishUser) {
        this.publishUser = publishUser;
    }

    /**
     * @return the replyUser
     */
    public UserAccountDetails getReplyUser() {
        return replyUser;
    }

    /**
     * @param replyUser the replyUser to set
     */
    public void setReplyUser(UserAccountDetails replyUser) {
        this.replyUser = replyUser;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
