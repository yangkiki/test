package com.moxian.ng.notifier.email.impl;

import com.moxian.ng.notifier.email.api.EmailOperations;
import com.moxian.ng.notifier.email.api.EmailConfigurer;

public abstract class AbstractEmailConfigurer implements EmailConfigurer {

    @Override
    public EmailOperations emailTemplate() {
        return new EmailTemplate(this.emailConfiguration());
    }

    /**
     * Override this method and add a @Bean annotation to expose it as a spring
     * managed bean.
     *
     * @return
     */
    public EmailOperations emailTemplateBean() {
        return this.emailTemplate();
    }

}
