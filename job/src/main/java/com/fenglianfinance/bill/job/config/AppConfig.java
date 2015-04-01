package com.fenglianfinance.bill.job.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.config.redis.RedisConfig;
import com.fenglianfinance.bill.config.DataSourceConfig;
import com.fenglianfinance.bill.config.JpaConfig;
import com.fenglianfinance.bill.gateway.common.GatewayCommonConfiguer;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

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
                        ControllerAdvice.class,//I
                        Configuration.class//
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
           // ObjectMapperConfig.class,//         
            DataSourceConfig.class,//
            JpaConfig.class,//
            BatchInfrastructureConfig.class,//
            GatewayCommonConfiguer.class,//
            JobConfiguration.class,//
            RedisConfig.class,//
            
        }//
)//
@EnableAsync(mode = AdviceMode.ASPECTJ)
@EnableScheduling()
//@ImportResource(value="classpath:/baseContext.xml")
public class AppConfig {

}
