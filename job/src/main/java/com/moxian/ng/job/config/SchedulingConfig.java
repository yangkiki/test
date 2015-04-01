/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.job.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
@Configuration
@EnableScheduling()
public class SchedulingConfig implements SchedulingConfigurer {

    private static final Logger log = LoggerFactory.getLogger(SchedulingConfig.class);

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (log.isDebugEnabled()) {
            log.debug("call configureTasks...");
        }

        //TODO read all config from database.
        //taskRegistrar.addCronTask(null);
        //taskRegistrar.addFixedDelayTask(null, delay);
        //taskRegistrar.addFixedRateTask(null);
        //taskRegistrar.addTriggerTask(null, null);
    }

}
