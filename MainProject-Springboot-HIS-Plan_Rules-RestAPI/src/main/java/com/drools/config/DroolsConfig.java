package com.drools.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This configuration class creates KIE container for Drools
 * @author Tapas Rout
 *
 */
@Configuration
@EnableSwagger2
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
    
    //Swagger configuration
    @Bean
     Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.drools.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Eligibility Check API")
                .description("This api is used to check eligibility of the User")
                .version("1.0.0")
                .build();
    }
  
}
