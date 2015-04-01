package com.moxian.ng.api.config;

import com.moxian.ng.Constants;
import com.moxian.ng.config.DataSourceConfig;
import com.moxian.ng.config.JpaConfig;
import com.moxian.ng.config.MongoConfig;
import com.moxian.ng.config.caching.RedisCacheConfig;
import com.moxian.ng.config.jackson2.ObjectMapperConfig;
import com.moxian.ng.config.redis.RedisConfig;
import com.moxian.ng.i18n.config.MessageSourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(//
        basePackageClasses = {Constants.class}, //
        excludeFilters = { //

            @Filter(//
                    type = FilterType.ANNOTATION, //
                    value = {//
                        RestController.class, //
                        ControllerAdvice.class, //
                        Configuration.class//
                    }//
            ) //
        }
)//
@PropertySources(
        value = { //    

            @PropertySource("classpath:/app.properties"),//
            //    @PropertySource(value = "classpath:/database.properties", ignoreResourceNotFound = true), //
            @PropertySource(value = "classpath:/mongo.properties", ignoreResourceNotFound = true), //
           //@PropertySource(value = "classpath:/redis.properties", ignoreResourceNotFound = true), //
        }//
)//

@Import(
        value = {//
            ObjectMapperConfig.class,//
            MessageSourceConfig.class,//
            DataSourceConfig.class,//
            JpaConfig.class,//
            MongoConfig.class,//
            RedisConfig.class,//
            RedisCacheConfig.class,//
          //  GatewayCommonConfiguer.class
        }//
)//
public class AppConfig {



}
