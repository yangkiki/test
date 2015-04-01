package com.fenglianfinance.bill.notifier.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.fenglianfinance.bill.notifier.email.api.EmailConfiguration;
import com.fenglianfinance.bill.notifier.email.api.EmailOperations;
import com.fenglianfinance.bill.notifier.email.impl.AbstractEmailConfigurer;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@Configuration
@PropertySource("classpath:/emailconfig.properties")
public class EmailConfig extends AbstractEmailConfigurer {

    private static final Logger log = LoggerFactory.getLogger(EmailConfig.class);

    @Inject
    Environment env;

    @Override
    @Bean
    public EmailConfiguration emailConfiguration() {
        final String username = env.getProperty("mail.username");
        final String password = env.getProperty("mail.password");
        final String host = env.getProperty("mail.host");
        final String port = env.getProperty("mail.port");
        final String fromAddr = env.getProperty("mail.from");
        final String debug = env.getProperty("main.debug");

        if (log.isDebugEnabled()) {
            log.debug("email configuration@ username@" + username
                    + ", password@" + password
                    + ", host@" + host
                    + ", port@" + port
                    + ", fromAddr @" + fromAddr
                    + ", debug@" + debug);
        }

        EmailConfiguration config = new EmailConfiguration();

        config.setUsername(username);
        config.setPassword(password);
        config.setHost(host);

        if (StringUtils.hasText(port)) {
            config.setPort(Integer.parseInt(port));
        }
        config.setFrom(fromAddr);
        if (StringUtils.hasText(debug)) {
            config.setDebug(Boolean.parseBoolean(debug));
        }

        return config;
    }

    @Bean
    @Override
    public EmailOperations emailTemplateBean() {
        return super.emailTemplateBean();
    }

}
