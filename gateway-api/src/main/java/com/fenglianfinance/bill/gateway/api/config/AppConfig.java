package com.fenglianfinance.bill.gateway.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.config.DataSourceConfig;
import com.fenglianfinance.bill.config.JpaConfig;
import com.fenglianfinance.bill.config.jackson2.ObjectMapperConfig;
import org.springframework.context.annotation.Import;

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
            DataSourceConfig.class, //
            JpaConfig.class,// 
            
        }//
)//
public class AppConfig {

}
