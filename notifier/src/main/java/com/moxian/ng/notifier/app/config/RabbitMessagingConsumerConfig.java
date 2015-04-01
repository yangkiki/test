package com.moxian.ng.notifier.app.config;

import com.moxian.ng.messaging.api.MessagingConstants;
import com.moxian.ng.messaging.config.RabbitConsumerConfig;
import com.moxian.ng.notifier.app.client.ClientHanlder;
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
