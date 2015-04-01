package com.fenglianfinance.bill.notifier.email.api;

public interface EmailConfigurer {
	
	public EmailConfiguration emailConfiguration();
	
	public EmailOperations emailTemplate();
}
