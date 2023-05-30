package com.nt.service.smsservice;

import com.twilio.type.PhoneNumber;

public interface SmsInterface {

	public String sendSms(PhoneNumber toNumber, String body);
}
