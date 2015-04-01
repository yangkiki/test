package com.moxian.ng.notifier.sms.api;

public interface SmsConfigurer {
	
	public SmsConfiguration smsConfiguration();
	
	public SmsOperations smsTemplate();
}
