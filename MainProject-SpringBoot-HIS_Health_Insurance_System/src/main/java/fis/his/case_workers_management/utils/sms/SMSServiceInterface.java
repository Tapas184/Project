package fis.his.case_workers_management.utils.sms;

import com.twilio.type.PhoneNumber;

public interface SMSServiceInterface {
	public String sendSms(PhoneNumber toNumber, String body);

}
