package com.fenglianfinance.bill.notifier.app.config;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.fenglianfinance.bill.notifier.sms.api.SmsOperations;
import com.fenglianfinance.bill.notifier.sms.impl.AbstractSmsConfigurer;
import com.fenglianfinance.bill.notifier.sms.api.SmsConfiguration;

/**
 *
 * @author hantsy
 *
 */
@Configuration
@PropertySource("classpath:/smsconfig.properties")
public class SmsConfig extends AbstractSmsConfigurer {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SmsConfig.class);

    private static final String ACCOUNT = "account";

    private static final String PASSWORD = "password";

    private static final String API_URL = "apiurl";

    @Inject
    private Environment env;

    @Bean
    @Override
    public SmsConfiguration smsConfiguration() {
        if (log.isDebugEnabled()) {
            log.debug("get sms configuration@account:"
                    + env.getProperty(PASSWORD)
                    + ",password:" + env.getProperty(ACCOUNT)
                    + ",url:" + env.getProperty(API_URL));
        }
        SmsConfiguration smsConfig = new SmsConfiguration();
        smsConfig.setPassword(env.getProperty(PASSWORD));
        smsConfig.setAccount(env.getProperty(ACCOUNT));
        smsConfig.setApiUrl(env.getProperty(API_URL));

        return smsConfig;
    }

    @Bean
    @Override
    public SmsOperations smsTemplateBean() {
        return super.smsTemplateBean();
    }

}
