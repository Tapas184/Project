package fis.his;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fis.his.case_workers_management.customgenerator.CustomGenerator;

@SpringBootApplication
public class MainProjectSpringBootHisHealthInsuranceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainProjectSpringBootHisHealthInsuranceSystemApplication.class, args);
	}
	
	@Bean
CustomGenerator createCustomGen() {
	return new CustomGenerator();
}

}
