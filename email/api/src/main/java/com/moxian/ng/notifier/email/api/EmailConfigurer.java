package com.moxian.ng.notifier.email.api;

public interface EmailConfigurer {
	
	public EmailConfiguration emailConfiguration();
	
	public EmailOperations emailTemplate();
}
