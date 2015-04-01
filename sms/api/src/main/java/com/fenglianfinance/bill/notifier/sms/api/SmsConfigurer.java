package com.fenglianfinance.bill.notifier.sms.api;

import com.fenglianfinance.bill.notifier.sms.api.SmsOperations;
import com.fenglianfinance.bill.notifier.sms.api.SmsConfiguration;

public interface SmsConfigurer {
	
	public SmsConfiguration smsConfiguration();
	
	public SmsOperations smsTemplate();
}
