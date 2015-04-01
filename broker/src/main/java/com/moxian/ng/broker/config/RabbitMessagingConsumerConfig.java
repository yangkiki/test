package com.moxian.ng.broker.config;

import com.moxian.ng.broker.service.OrderHandler;
import com.moxian.ng.messaging.api.MessagingConstants;
import com.moxian.ng.messaging.config.RabbitConsumerConfig;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMessagingConsumerConfig extends RabbitConsumerConfig {

    private static final Logger log = LoggerFactory.getLogger(RabbitMessagingConsumerConfig.class);

    @Inject
    OrderHandler clientHanlder;

    @Override
    protected void configureMessageListenerContainer(SimpleMessageListenerContainer container) {
        if(log.isDebugEnabled()){
            log.debug("configuring MessageListenerContainer...@@@");
        }
        
        container.setQueueNames(
                MessagingConstants.QUEUE_ORDER,
                MessagingConstants.QUEUE_PAYMENT
        );
        container.setMessageListener(new MessageListenerAdapter(clientHanlder, messageConverter()));

    }

}
