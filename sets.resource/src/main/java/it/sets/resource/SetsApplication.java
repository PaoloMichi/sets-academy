package it.sets.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class },
					   scanBasePackages = { "it.sets.resource.core",
											"it.sets.resource.model",
											"it.sets.resource.repository",
											"it.sets.resource.service",
											"it.sets.resource.web" })
public class SetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SetsApplication.class, args);
	}

}
