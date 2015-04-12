/**
 * 
 */
package com.moxian.ng.domain;

import java.time.LocalDateTime;

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
@Table(name = "post_resources")
public class PostResource extends PersistableEntity<Long> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public enum Type {

        TEXT, VIDEO, MUSIC, PHOTO, Link, VOICE
    }

    public enum Status {
        PUBLIC, PRIVATE
    }

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "res_id")
    private String resourceUnicode;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "create_on")
    private LocalDateTime createOn = LocalDateTime.now();

    @Column(name = "active")
    private boolean active = true;

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
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
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
