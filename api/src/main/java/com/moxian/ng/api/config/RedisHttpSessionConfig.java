package com.moxian.ng.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 3600 )
public class RedisHttpSessionConfig {

    @Bean 
    public HttpSessionStrategy httpSessionStrategy(){
        return new  HeaderHttpSessionStrategy(); 
    }

}
