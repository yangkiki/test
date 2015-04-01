package com.fenglianfinance.bill.messaging.config;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import com.fenglianfinance.bill.messaging.api.MessagingConstants;
import org.springframework.context.annotation.Bean;

public class RabbitConsumerConfig extends AbstractRabbitConfig {

    @PostConstruct
    public void init() {
        rabbitAdmin().declareQueue(new Queue(MessagingConstants.QUEUE_EMAIL));
        rabbitAdmin().declareQueue(new Queue(MessagingConstants.QUEUE_ORDER));
        rabbitAdmin().declareQueue(new Queue(MessagingConstants.QUEUE_SMS));
        rabbitAdmin().declareQueue(new Queue(MessagingConstants.QUEUE_PAYMENT));
        rabbitAdmin().declareQueue(new Queue(MessagingConstants.QUEUE_JOB));

        rabbitAdmin().declareExchange(new DirectExchange(MessagingConstants.EXCHANGE_EMAIL));
        rabbitAdmin().declareExchange(new DirectExchange(MessagingConstants.EXCHANGE_SMS));
        rabbitAdmin().declareExchange(new DirectExchange(MessagingConstants.EXCHANGE_ORDER));
        rabbitAdmin().declareExchange(new DirectExchange(MessagingConstants.EXCHANGE_PAYMENT));
        rabbitAdmin().declareExchange(new DirectExchange(MessagingConstants.EXCHANGE_JOB));

        rabbitAdmin().declareBinding(
                BindingBuilder
                .bind(new Queue(MessagingConstants.QUEUE_EMAIL))
                .to(new DirectExchange(MessagingConstants.EXCHANGE_EMAIL))
                .with(MessagingConstants.ROUTING_EMAIL)
        );

        rabbitAdmin().declareBinding(
                BindingBuilder
                .bind(new Queue(MessagingConstants.QUEUE_ORDER))
                .to(new DirectExchange(MessagingConstants.EXCHANGE_ORDER))
                .with(MessagingConstants.ROUTING_ORDER)
        );

        rabbitAdmin().declareBinding(
                BindingBuilder
                .bind(new Queue(MessagingConstants.QUEUE_SMS))
                .to(new DirectExchange(MessagingConstants.EXCHANGE_SMS))
                .with(MessagingConstants.ROUTING_SMS)
        );

        rabbitAdmin().declareBinding(
                BindingBuilder
                .bind(new Queue(MessagingConstants.QUEUE_PAYMENT))
                .to(new DirectExchange(MessagingConstants.EXCHANGE_PAYMENT))
                .with(MessagingConstants.ROUTING_PAYMENT)
        );
        
         rabbitAdmin().declareBinding(
                BindingBuilder
                .bind(new Queue(MessagingConstants.QUEUE_JOB))
                .to(new DirectExchange(MessagingConstants.EXCHANGE_JOB))
                .with(MessagingConstants.ROUTING_JOB)
        );
    }

    @Bean
    public SimpleMessageListenerContainer getMessageListenerContainer() {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(
                connectionFactory());
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setTaskExecutor(amqpExecutor());
        configureMessageListenerContainer(container);
        return container;
    }

    protected void configureMessageListenerContainer(SimpleMessageListenerContainer container) {
    }

}
