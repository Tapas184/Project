package com.drools.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration class creates KIE container for Drools
 * @author Tapas Rout
 *
 */
@Configuration
public class DroolsConfig {

    /**
     * This method creates KIE container bean
     * @return : KieContainer
     */
    @Bean
     KieContainer kieContainer() {
        return KieServices.Factory.get().getKieClasspathContainer();
    }
    /**
     * This method creates KiefileSystem  bean
     * @return :KieContainer
     */
    
    @Bean
    KieFileSystem kieFileSystem() {
    	return KieServices.Factory.get().newKieFileSystem();
    }
}
