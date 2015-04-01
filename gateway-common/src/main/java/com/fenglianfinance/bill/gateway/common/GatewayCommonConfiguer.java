/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.gateway.common;

import com.fenglianfinance.bill.SerialNumberGeneratorUtils;
import com.fenglianfinance.bill.repository.OrderRepository;
import com.fenglianfinance.bill.repository.ProductRepository;
import com.fenglianfinance.bill.repository.TransactionLogRepository;
import com.fenglianfinance.bill.repository.TransactionReconciliationRepository;
import com.fenglianfinance.bill.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hansy
 */
@Configuration
@PropertySource(value = "classpath:/gwconfig.properties", ignoreResourceNotFound = true)
public class GatewayCommonConfiguer {

    private static final Logger log = LoggerFactory.getLogger(GatewayCommonConfiguer.class);

    @Inject
    private OrderRepository orderRepository;

    @Bean
    public SerialNumberGenerator serialNumberGenerator() {
        return new SerialNumberGenerator();
    }

    @Bean
    public GatewayOperations gatewayTemplate() {
        GatewayOperations gatewayOperations = new GatewayTemplate(orderRepository);

        return gatewayOperations;
    }

    @Bean
    @Transactional
    public GatewayService gatewayService(
            GatewayOperations gatewayOperations,
            SerialNumberGenerator generator,
            TransactionLogRepository tlogRepository,
            UserRepository userRespoitory,
            SerialNumberGeneratorUtils utils, 
            TransactionReconciliationRepository reconRepsoitory,
            ProductRepository productRepository ){
        return new GatewayService(gatewayOperations,
                generator, 
                tlogRepository, 
                userRespoitory, 
                orderRepository, 
                utils, 
                reconRepsoitory,
                productRepository);
    }

}
