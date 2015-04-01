/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.messaging.api;

/**
 *
 * @author hansy
 */
public interface MessagingPublisher<T> {

    public void send(T message);

    public String routingKey();

}
