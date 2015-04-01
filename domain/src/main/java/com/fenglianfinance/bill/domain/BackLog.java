/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import com.fenglianfinance.bill.domain.support.PersistableEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author hansy
 */
@Entity
@Table(name = "back_log")
public class BackLog extends PersistableEntity<Long> {

    private static final long serialVersionUID = 1L;
    
    public BackLog() {
    }

//    @OneToMany
//   @JoinColumn(name = "back_log_id")
//    private List<BackLogItem> items = new ArrayList<>();
//
//    public List<BackLogItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<BackLogItem> items) {
//        this.items = items;
//    }

    // some statistics, some can be caculated by map reduce solution in future

}
