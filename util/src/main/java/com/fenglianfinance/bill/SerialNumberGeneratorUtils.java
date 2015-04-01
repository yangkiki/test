/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill;

import com.fenglianfinance.bill.cache.service.CacheService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author hantsy
 */
@Service
public class SerialNumberGeneratorUtils {

    private static final Logger log = LoggerFactory.getLogger(SerialNumberGeneratorUtils.class);

    private static final String ORDER_SN_CACHE_PREFIX = "SN:PO:";

    private static final String RECONCILIATION_SN_CACHE_PREFIX = "SN:RECONCILIATION:";

    @Inject
    private CacheService cacheService;

    public String nextOrderSerialNumber() {

        if (log.isDebugEnabled()) {
            log.debug("get nextOrderSerialNumber @");
        }

        return nextSerialNumber(ORDER_SN_CACHE_PREFIX);
    }

    public String nextReconciliationSerialNumber() {

        if (log.isDebugEnabled()) {
            log.debug("get nextReconciliationSerialNumber @");
        }

        return nextSerialNumber(RECONCILIATION_SN_CACHE_PREFIX);
    }

    private String nextSerialNumber(String cachePrefix) {

        if (log.isDebugEnabled()) {
            log.debug("get nextSerialNumber @");
        }

        String ldt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        if (log.isDebugEnabled()) {
            log.debug("local date time format info@" + ldt);
        }

        AtomicLong currentSerialNumber = (AtomicLong) cacheService.get(cachePrefix + ldt);

        if (currentSerialNumber == null) {
            currentSerialNumber = new AtomicLong(1);
        }

        long nextKeyLong = currentSerialNumber.getAndIncrement();

        cacheService.set(cachePrefix + ldt, currentSerialNumber);

        String nextKeyString = String.valueOf(nextKeyLong);

        if (log.isDebugEnabled()) {
            log.debug("next key string@" + nextKeyString);
        }

        String appendedString = "";
        for (int i = 0; i < 6 - nextKeyString.length(); i++) {
            appendedString += "0";
        }

        if (log.isDebugEnabled()) {
            log.debug("appended string@" + appendedString);
        }

        return ldt + appendedString + nextKeyString;
    }

}
