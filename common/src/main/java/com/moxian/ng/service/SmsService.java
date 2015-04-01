/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.service;

import com.moxian.ng.cache.service.CacheService;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.exception.MobileNumberNotBelongToUserException;
import com.moxian.ng.messaging.api.MessagingConstants;
import com.moxian.ng.model.SmsCodeResult;
import com.moxian.ng.notifier.sms.core.SmsMessage;
import com.moxian.ng.repository.UserRepository;
import java.util.Date;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@Service
public class SmsService {

    private static final Logger log = LoggerFactory.getLogger(SmsService.class);

    private static final String PREFIX = "SMS_SERVICE:";

    private static final int EXPIRES_IN_SECONDS = 60;

    @Inject
    private CacheService cacheService;

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Inject
    private MessageSource messageSource;

    @Inject
    private UserRepository userRepository;

    public boolean validate(String mobileNumber, String smsCode, Boolean removeSmsCode) {
        Assert.notNull(smsCode, "sms response code can not not null");

        if (log.isDebugEnabled()) {
            log.debug("verifySmsCode @ mobileNumber @" + mobileNumber + ", smsCode@" + smsCode);
        }

        String existing = (String) cacheService.get(PREFIX + mobileNumber);

        if (log.isDebugEnabled()) {
            log.debug("fetching generated code from cache @" + existing);
        }
        if(smsCode.equals(existing)){
        	if(removeSmsCode != null && removeSmsCode){
        		cacheService.delete(PREFIX + mobileNumber);
        	}
        	return true;
        }
        return false;
    }

    public SmsCodeResult generateSmsCode(String mobileNumber, Long userId) {
        Assert.notNull("mobileNumber can not be null");
        if (log.isDebugEnabled()) {
            log.debug("generateSmsCode @ mobileNumber @" + mobileNumber);
        }

        if (userId != null) {
            
            UserAccount user = userRepository.findOne(userId);
            
            if (!mobileNumber.equals(user.getMobileNumber())) {
                throw new MobileNumberNotBelongToUserException();
            }
        }

        String result = RandomStringUtils.randomNumeric(6);

        if (log.isDebugEnabled()) {
            log.debug("generated random number @" + result);
        }

        cacheService.set(PREFIX + mobileNumber, result, EXPIRES_IN_SECONDS);

        final SmsMessage smsMessage = new SmsMessage(mobileNumber, messageSource.getMessage("smscode", new String[]{result}, null));
        // messagingClient.send(smsMessage);

        rabbitTemplate.convertAndSend(MessagingConstants.EXCHANGE_SMS, MessagingConstants.ROUTING_SMS, smsMessage);

        return new SmsCodeResult(mobileNumber, null, new Date().getTime());
    }

}
