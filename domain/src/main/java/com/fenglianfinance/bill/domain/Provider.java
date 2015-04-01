package com.fenglianfinance.bill.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fenglianfinance.bill.domain.support.PersistableEntity;

@Entity
@Table(name = "hotel_providers")
public class Provider extends PersistableEntity<Long> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    public Provider(){}
    
    public Provider(String name) {
        this.name=name;
    }

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

    @Override
    public String toString() {
        return "Provider [name=" + name + ", description=" + description + "]";
    }



}
