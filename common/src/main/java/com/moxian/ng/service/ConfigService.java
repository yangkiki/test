/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@Service
public class ConfigService {

    private static final Logger log = LoggerFactory.getLogger(ConfigService.class);

    private static final String DEFAULT_FEE = "0.00";

    private static final String DEFAULT_MAX_TENDER_RATE = "0.09";

    private static final String DEFAULT_BORROWER_RATE = "0.99";//borrowerRate

    private static final String DEFAULT_MERCHANT_CUSTID = "6000060000736315";

    private static final String CONFIG_PREFIX = "CONFIG:";

    private static final String PROP_FEE = CONFIG_PREFIX + "FEE";

    private static final String PROP_MAX_TENDER_RATE = CONFIG_PREFIX + "MAX_TENDER_RATE";

    private static final String PROP_BORROWER_RATE = CONFIG_PREFIX + "BORROWER_RATE";

    private static final String PROP_MERCHANT_CUSTID = CONFIG_PREFIX + "MERCHANT_CUSTID";

    @Inject
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void initialize() {
        if (log.isDebugEnabled()) {
            log.debug("initializing config service...");
        }

        if (readFee() == null) {
            writeFee(DEFAULT_FEE);
        }

        if (readMaxTenderRate() == null) {
            writeMaxTenderRate(DEFAULT_MAX_TENDER_RATE);
        }

        if (readBorrowerRate() == null) {
            writeBorrowerRate(DEFAULT_BORROWER_RATE);
        }

        if (readMerchantCustID() == null) {
            writeMerchantCustID(DEFAULT_MERCHANT_CUSTID);
        }

    }

    public String readFee() {
        return stringRedisTemplate.opsForValue().get(PROP_FEE);
    }

    public void writeFee(String fee) {
        stringRedisTemplate.opsForValue().set(PROP_FEE, fee);
    }

    public String readMaxTenderRate() {
        return stringRedisTemplate.opsForValue().get(PROP_MAX_TENDER_RATE);
    }

    public void writeMaxTenderRate(String fee) {
        stringRedisTemplate.opsForValue().set(PROP_MAX_TENDER_RATE, fee);
    }

    public String readBorrowerRate() {
        return stringRedisTemplate.opsForValue().get(PROP_BORROWER_RATE);
    }

    public void writeBorrowerRate(String fee) {
        stringRedisTemplate.opsForValue().set(PROP_BORROWER_RATE, fee);
    }

    public String readMerchantCustID() {
        return stringRedisTemplate.opsForValue().get(PROP_MERCHANT_CUSTID);
    }

    public void writeMerchantCustID(String v) {
        stringRedisTemplate.opsForValue().set(PROP_MERCHANT_CUSTID, v);
    }

}
