package fis.his.application_registration.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@Component
public class Config {

	@Bean
	RestTemplate createTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	ObjectMapper createMapper() {
		return new ObjectMapper();
	}
}
