package com.fenglianfinance.bill.notifier.app.config;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import com.fenglianfinance.bill.messaging.api.MessagingConstants;
import com.fenglianfinance.bill.messaging.config.RabbitConsumerConfig;
import com.fenglianfinance.bill.notifier.app.client.ClientHanlder;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMessagingConsumerConfig extends RabbitConsumerConfig {

    private static final Logger log = LoggerFactory.getLogger(RabbitMessagingConsumerConfig.class);

    @Inject
    ClientHanlder clientHanlder;

    @Override
    protected void configureMessageListenerContainer(SimpleMessageListenerContainer container) {
        if (log.isDebugEnabled()) {
            log.debug("configuring messageListenerContainer...");
        }
        container.setQueueNames(
                MessagingConstants.QUEUE_EMAIL,
                MessagingConstants.QUEUE_SMS
        );
        
        container.setMessageListener(new MessageListenerAdapter(clientHanlder, messageConverter()));

    }

}
