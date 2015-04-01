package com.moxian.ng.model;

import java.io.Serializable;

public class RoleForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RoleForm{" + "name=" + name + ", description=" + description + ", id=" + id + '}';
    }

}
