/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.job.service;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author hantsy<hantsy@gmail.com><hantsy<hantsy@gmail.com>@gmail.com>
 */
//@Service
public class TestService {

    private static final Logger log = LoggerFactory.getLogger(TestService.class);

    @Scheduled(initialDelay = 10000L, fixedRate = 5000L)
    public void sayHello() {
        if (log.isDebugEnabled()) {
            log.debug("Hello spring scheduling...@" + (LocalDateTime.now()));
        }
    }

}
