package com.fenglianfinance.bill.notifier.email.api;

import com.fenglianfinance.bill.notifier.email.core.EmailMessage;

public interface EmailOperations {

    public void send(String to, String subject, String message);

    public void send(EmailMessage msg);

}
