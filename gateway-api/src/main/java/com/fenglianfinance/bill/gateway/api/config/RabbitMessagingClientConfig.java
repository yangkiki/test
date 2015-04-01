package com.fenglianfinance.bill.gateway.api.config;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import com.fenglianfinance.bill.messaging.api.MessagingConstants;
import com.fenglianfinance.bill.messaging.config.AbstractRabbitConfig;
import com.fenglianfinance.bill.messaging.config.RabbitConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySource("classpath:/rabbit.properties")
public class RabbitMessagingClientConfig extends AbstractRabbitConfig {


}
