/**
 * 
 */
package com.moxian.ng.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yang
 *
 */
public class PostResourceDetails implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private PostDetails post;

    private String resourceUnicode;

    private String type;

    private String status;

    private LocalDateTime createOn;

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
     * @return the resourceUnicode
     */
    public String getResourceUnicode() {
        return resourceUnicode;
    }

    /**
     * @param resourceUnicode the resourceUnicode to set
     */
    public void setResourceUnicode(String resourceUnicode) {
        this.resourceUnicode = resourceUnicode;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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

    /**
     * @return the createOn
     */
    public LocalDateTime getCreateOn() {
        return createOn;
    }

    /**
     * @param createOn the createOn to set
     */
    public void setCreateOn(LocalDateTime createOn) {
        this.createOn = createOn;
    }

}
