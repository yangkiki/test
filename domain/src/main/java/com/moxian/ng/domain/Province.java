/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.domain;

import com.moxian.ng.domain.support.PersistableEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
@Entity
@Table(name = "provinces")
public class Province extends PersistableEntity<Long> {

    private String name;

    @Column(name = "area_code")
    private String areaCode;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Province() {
    }

    public Province(String name) {
       this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
