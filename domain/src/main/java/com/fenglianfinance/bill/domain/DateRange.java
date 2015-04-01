/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;

/**
 *
 * @author hansy
 */
@Embeddable
public class DateRange implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime from;

    private LocalDateTime to;

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }


}
