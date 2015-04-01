/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.messaging.core;

import com.moxian.ng.messaging.api.MessagingPublisher;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
public abstract class AbstractMessagingPublisher<T> implements MessagingPublisher<T> {

    private static final Logger log = LoggerFactory.getLogger(AbstractMessagingPublisher.class);

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(T message) {
        if (log.isDebugEnabled()) {
            log.debug("send message @" + message + " with routing key @" + routingKey());
        }
        this.rabbitTemplate.convertAndSend(routingKey(), message);
    }


}
