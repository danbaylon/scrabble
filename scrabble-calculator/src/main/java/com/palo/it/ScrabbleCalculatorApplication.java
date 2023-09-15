package com.palo.it;

import com.palo.it.config.ApplicationProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
@OpenAPIDefinition
public class ScrabbleCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrabbleCalculatorApplication.class, args);

	}
}
