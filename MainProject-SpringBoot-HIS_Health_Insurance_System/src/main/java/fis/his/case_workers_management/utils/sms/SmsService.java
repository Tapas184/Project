package fis.his.case_workers_management.utils.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService implements SMSServiceInterface {
	@Autowired
	private TwiloConfiguration config;

	@Override
	public String sendSms(PhoneNumber toNumber, String body) {
		Message msg = Message.creator(toNumber, config.messageSid, body)
		        .create();
		return msg.getStatus().toString() ;
	}
	
}
