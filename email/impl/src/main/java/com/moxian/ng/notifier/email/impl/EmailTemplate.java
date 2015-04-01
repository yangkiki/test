package com.moxian.ng.notifier.email.impl;

import com.moxian.ng.notifier.email.api.EmailConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moxian.ng.notifier.email.core.EmailMessage;
import com.moxian.ng.notifier.email.api.EmailOperations;
import java.util.Date;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.util.StringUtils;

public class EmailTemplate implements EmailOperations {

    private static final Logger log = LoggerFactory.getLogger(EmailTemplate.class);

    private final EmailConfiguration config;

    private final JavaMailSenderImpl mailSender;

    public EmailTemplate(EmailConfiguration config) {
        this.config = config;

        this.mailSender = new JavaMailSenderImpl();
        this.mailSender.setHost(config.getHost());
        this.mailSender.setPort(config.getPort());
        this.mailSender.setUsername(config.getUsername());
        this.mailSender.setPassword(config.getPassword());
        this.mailSender.setDefaultEncoding("UTF-8");

        //Google Mail provides two ports to connnect gmail server.
        //1. 465 for SSL
        //2. 587 fot TLS/STARTTLS
        //but you should sign in gmail to activate your mail account for the first time.
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.timeout", "25000");
//        props.setProperty("mail.smtp.quitwait", "false"); 
        props.setProperty("mail.debug", String.valueOf(config.isDebug()));

//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");

        this.mailSender.setJavaMailProperties(props);
    }

    @Override
    public void send(String to, String subject, String message) {
        if (log.isDebugEnabled()) {
            log.debug("to @" + to);
            log.debug("subject @" + subject);
            log.debug("message @" + message);
        }

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setFrom(new InternetAddress(config.getFrom()));
                helper.setTo(new InternetAddress(to));
                helper.setSubject(subject);
                helper.setSentDate(new Date());
                helper.setText(message, true);
            }
        };

        this.mailSender.send(preparator);
    }

    @Override
    public void send(EmailMessage msg) {
        if (log.isDebugEnabled()) {
            log.debug("send EmailMessage@" + msg);
        }

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setFrom(new InternetAddress(config.getFrom()));
                helper.setTo(new InternetAddress(msg.getTo()));
                helper.setSubject(msg.getSubject());
                helper.setSentDate(new Date());
                helper.setText(msg.getContent(), true);

                if (StringUtils.hasText(msg.getReplyTo())) {
                    helper.setReplyTo(new InternetAddress(msg.getReplyTo()));
                }
            }
        };

        this.mailSender.send(preparator);
    }

}
