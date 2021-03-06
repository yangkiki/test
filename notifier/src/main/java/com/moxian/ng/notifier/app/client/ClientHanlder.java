/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.notifier.app.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moxian.ng.notifier.email.api.EmailOperations;
import com.moxian.ng.notifier.email.core.EmailMessage;
import com.moxian.ng.notifier.sms.api.SmsOperations;
import com.moxian.ng.notifier.sms.core.SmsMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@Named
public class ClientHanlder {

    private static final Logger log = LoggerFactory.getLogger(ClientHanlder.class);

    @Inject
    private SmsOperations smsOperations;

    @Inject
    private EmailOperations emailOperations;

    @Inject
    private ObjectMapper objectMapper;

    public void handleMessage(SmsMessage sms) {
        if (log.isDebugEnabled()) {
            log.debug("handle sms message @" + sms);
        }
        smsOperations.send(sms.getMobileNumber(), sms.getContent());
    }

    public void handleMessage(EmailMessage emailMsg) {
        if (log.isDebugEnabled()) {
            log.debug("handle email message @" + emailMsg);
        }

        emailOperations.send(emailMsg);
    }

}
