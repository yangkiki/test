package com.moxian.ng.broker.config;

import com.moxian.ng.config.redis.RedisConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.moxian.ng.config.DataSourceConfig;
import com.moxian.ng.config.JpaConfig;
import com.moxian.ng.config.jackson2.ObjectMapperConfig;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan(//
        basePackages = {//
            "com.fenglianfinance.bill",//
        }, //
        excludeFilters = { //          

            @Filter(//
                    type = FilterType.ANNOTATION,//
                    value = {//
                        RestController.class,//
                        ControllerAdvice.class,//
                        Configuration.class
                    }//
            ) //
        }
)

@PropertySources(
        value = { //               

            @PropertySource("classpath:/app.properties"),//
        }//
)//
@Import(
        value = {//
            ObjectMapperConfig.class,//         
            DataSourceConfig.class,//
            JpaConfig.class,//
            RedisConfig.class,//
        }//
)//
public class AppConfig {

}
