/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.gateway.common;

import com.fenglianfinance.bill.SerialNumberUtils;
import com.fenglianfinance.bill.payment.model.SerialNumberIndicator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hansy
 */
public class SerialNumberGenerator {

    public SerialNumberIndicator next() {
        String ordId = SerialNumberUtils.next();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String ordDate = dtf.format(LocalDateTime.now());

        SerialNumberIndicator indicator = new SerialNumberIndicator();
        indicator.setOrdDate(ordDate);
        indicator.setOrdId(ordId);

        return indicator;
    }
}
