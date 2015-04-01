package com.fenglianfinance.bill.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.Constants;
import com.fenglianfinance.bill.config.redis.RedisConfig;
import com.fenglianfinance.bill.i18n.config.MessageSourceConfig;
import com.fenglianfinance.bill.config.DataSourceConfig;
import com.fenglianfinance.bill.config.JpaConfig;
import com.fenglianfinance.bill.config.MongoConfig;
import com.fenglianfinance.bill.config.caching.RedisCacheConfig;
import com.fenglianfinance.bill.config.jackson2.ObjectMapperConfig;
import com.fenglianfinance.bill.gateway.common.GatewayCommonConfiguer;
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
            GatewayCommonConfiguer.class
        }//
)//
public class AppConfig {



}
