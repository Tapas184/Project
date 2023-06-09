package fis.his.admin.case_workers_management.utils.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("twilio")
public class TwiloConfiguration {

	@Value("${twilio.account_sid}")
	public String accountSid;
	@Value("${twilio.auth_token}")
	public String authToken;
	@Value("${twilio.message_sid}")
	public String messageSid;
	
	
}
