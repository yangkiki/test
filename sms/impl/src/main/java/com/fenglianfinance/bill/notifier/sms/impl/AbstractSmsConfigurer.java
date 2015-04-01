package com.fenglianfinance.bill.notifier.sms.impl;

import com.fenglianfinance.bill.notifier.sms.api.SmsOperations;
import com.fenglianfinance.bill.notifier.sms.api.SmsConfigurer;

public abstract class AbstractSmsConfigurer implements SmsConfigurer {

    @Override
    public SmsOperations smsTemplate() {
        return new SmsTemplate(this.smsConfiguration());
    }

    /**
     * Override this method and add a @Bean annotation to expose it as a spring
     * managed bean.
     *
     * @return
     */
    public SmsOperations smsTemplateBean() {
        return this.smsTemplate();
    }
}
