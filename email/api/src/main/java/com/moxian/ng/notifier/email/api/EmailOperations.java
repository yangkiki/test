package com.moxian.ng.notifier.email.api;

import com.moxian.ng.notifier.email.core.EmailMessage;

public interface EmailOperations {

    public void send(String to, String subject, String message);

    public void send(EmailMessage msg);

}
