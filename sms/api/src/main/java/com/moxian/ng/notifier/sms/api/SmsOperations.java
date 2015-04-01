package com.moxian.ng.notifier.sms.api;

import com.moxian.ng.notifier.sms.core.SmsMessage;


public interface SmsOperations {
	
	public String send(String mobile, String message);

        public String send(SmsMessage msg);
}
