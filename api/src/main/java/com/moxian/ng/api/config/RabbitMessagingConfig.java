/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.config;

import com.moxian.ng.messaging.config.AbstractRabbitConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
@Configuration
@PropertySource("classpath:/rabbit.properties")
public class RabbitMessagingConfig extends AbstractRabbitConfig{
    
}
