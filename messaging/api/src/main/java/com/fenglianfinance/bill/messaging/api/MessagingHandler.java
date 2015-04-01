/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.messaging.api;

/**
 *
 * @author hansy
 */
public interface MessagingHandler<T> {

    public void handleMessage(T message);
}
