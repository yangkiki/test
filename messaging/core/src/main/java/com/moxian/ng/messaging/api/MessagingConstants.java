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
public class MessagingConstants {

    public static final String CHANNEL_NOTIFICATIONS = "notifications";
    public static final String CHANNEL_ORDERS = "orders";

    public static final String QUEUE_SMS = "f.queue.sms";
    public static final String QUEUE_EMAIL = "f.queue.email";
    public static final String QUEUE_ORDER = "f.queue.order";
    public static final String QUEUE_PAYMENT = "f.queue.payment";
    public static final String QUEUE_JOB = "f.queue.job";

    public static final String ROUTING_SMS = "f.routing.sms";
    public static final String ROUTING_EMAIL = "f.routing.email";
    public static final String ROUTING_ORDER = "f.routing.order";
    public static final String ROUTING_PAYMENT = "f.routing.payment";
    public static final String ROUTING_JOB = "f.routing.job";

    public static final String EXCHANGE_SMS = "f.exchange.direct.sms";
    public static final String EXCHANGE_EMAIL = "f.exchange.direct.email";
    public static final String EXCHANGE_ORDER = "f.exchange.direct.order";
    public static final String EXCHANGE_PAYMENT = "f.exchange.direct.payment";
    public static final String EXCHANGE_JOB = "f.exchange.direct.job";

}
