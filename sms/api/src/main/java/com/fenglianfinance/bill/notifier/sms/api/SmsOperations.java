package com.fenglianfinance.bill.notifier.sms.api;

import com.fenglianfinance.bill.notifier.sms.core.SmsMessage;


public interface SmsOperations {
	
	public String send(String mobile, String message);

        public String send(SmsMessage msg);
}
