package com.moxian.ng.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.moxian.ng.domain.support.PersistableEntity;


@Entity
@Table(name="categories")
public class Category extends PersistableEntity<Long> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String name;

    public Category(){}
    public Category(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category [name=" + name + "]";
    }

}
