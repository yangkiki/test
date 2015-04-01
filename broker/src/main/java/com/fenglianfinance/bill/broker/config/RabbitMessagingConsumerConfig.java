package com.fenglianfinance.bill.broker.config;

import com.fenglianfinance.bill.broker.service.OrderHandler;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import com.fenglianfinance.bill.messaging.api.MessagingConstants;
import com.fenglianfinance.bill.messaging.config.RabbitConsumerConfig;
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
