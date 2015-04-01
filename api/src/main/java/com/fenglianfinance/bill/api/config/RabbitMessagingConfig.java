/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.api.config;

import com.fenglianfinance.bill.messaging.config.AbstractRabbitConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author hansy
 */
@Configuration
@PropertySource("classpath:/rabbit.properties")
public class RabbitMessagingConfig extends AbstractRabbitConfig{
    
}
