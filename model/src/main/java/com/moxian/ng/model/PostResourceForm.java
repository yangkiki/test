/**
 * 
 */
package com.moxian.ng.model;

import java.io.Serializable;

/**
 * @author yang
 *
 */
public class PostResourceForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private PostSimple post;

    private String resourceUnicode;

    private String type;

    private String status;

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
     * @return the resourceUnicode
     */
    public String getResourceUnicode() {
        return resourceUnicode;
    }

    /**
     * @return the post
     */
    public PostSimple getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(PostSimple post) {
        this.post = post;
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

}
