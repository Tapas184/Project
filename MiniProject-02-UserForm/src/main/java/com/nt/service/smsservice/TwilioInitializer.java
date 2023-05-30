package com.nt.service.smsservice;

import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInitializer {

	private TwiloConfiguration cong;

	public TwilioInitializer(TwiloConfiguration cong) {
		this.cong = cong;
		Twilio.init(cong.accountSid, cong.authToken);
	}
	
	
}
