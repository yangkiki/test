/**
 * 
 */
package com.moxian.ng.model;

import java.io.Serializable;

/**
 * @author yang
 *
 */
public class PostSimple implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;

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

}
