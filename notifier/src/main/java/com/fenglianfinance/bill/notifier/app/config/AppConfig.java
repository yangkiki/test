package com.fenglianfinance.bill.notifier.app.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fenglianfinance.bill.config.jackson2.ObjectMapperConfig;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(//
        basePackages = {//
            "com.fenglianfinance.bill.notifier",//
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
        }//
)//
public class AppConfig {

}
