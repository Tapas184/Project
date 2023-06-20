package com.drools.configur;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {


	@Bean
	KieContainer kieContainer() {
		KieServices kieServices = KieServices.Factory.get();
		return kieServices.getKieClasspathContainer();
	}
}
