package fis.ssn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MiniProject03SsnSocialSecurityNumberRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniProject03SsnSocialSecurityNumberRestApiApplication.class, args);
	}

	//Swagger configuration
    @Bean
     Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fis.ssn.restcontoller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SSN Check API")
                .description("This api is used to check SSN details of citizen")
                .contact(new Contact("Tapas", null, "tapasrout184@gmail.com"))
                .license("Tapas liciense 2.0")
                .version("1.0.0")
                .build();
    }
}
