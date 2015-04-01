package com.fenglianfinance.bill.messaging.config;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/rabbit.properties")
public class AbstractRabbitConfig {

    private static final Logger log = org.slf4j.LoggerFactory
            .getLogger(AbstractRabbitConfig.class);

    @Inject
    private Environment env;

    @Inject
    ObjectMapper objectMapper;

    @Bean
    public Executor amqpExecutor() {

        int poolSize = env.getProperty("rb.executor.poolSize", Integer.class);

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolSize);
        executor.setThreadNamePrefix("RabbitExecutor-");
        executor.initialize();

        return executor;
    }

    @Bean
    public ConnectionFactory connectionFactory() {

        String addresses = env.getProperty("rb.addresses");
        String vhost = env.getProperty("rb.vhost");
        String username = env.getProperty("rb.username");
        String password = env.getProperty("rb.password");

        if (log.isDebugEnabled()) {
            log.debug("connecting to RabbitMQ@\n addresses:" + addresses
                    + "\n vhost:" + vhost
                    + "\n username:" + username
                    + "\n password:" + password);
        }

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vhost);
        connectionFactory.setChannelCacheSize(25);

        connectionFactory.setExecutor(amqpExecutor());

        return connectionFactory;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);
        return retryTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        messageConverter.setJsonObjectMapper(objectMapper);
        return messageConverter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRetryTemplate(retryTemplate());
        template.setMessageConverter(messageConverter());
        configureRabbitTemplate(template);
        return template;
    }

    protected void configureRabbitTemplate(RabbitTemplate template) {
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

}
