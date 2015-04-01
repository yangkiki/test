package com.fenglianfinance.bill.config.redis;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

@Configuration
@PropertySource("classpath:/redis.properties")
public class RedisConfig {

    private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Inject
    private Environment env;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        if (log.isInfoEnabled()) {
            log.info("connecting reids:" + env.getProperty("redis.host"));
        }

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();

        connectionFactory.setHostName(env.getProperty("redis.host"));
        connectionFactory.setUsePool(false);

        if (StringUtils.hasText(env.getProperty("redis.password"))) {
            connectionFactory.setPassword(env.getProperty("redis.password"));
        }

        return connectionFactory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory());
        return stringRedisTemplate;
    }
    
    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        //redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        return redisTemplate;
    }

//    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
//        return new Jackson2JsonRedisSerializer(Object.class);
//    }
}
