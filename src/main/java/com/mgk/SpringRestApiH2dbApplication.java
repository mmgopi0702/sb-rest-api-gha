package com.mgk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;


@OpenAPIDefinition(
		info = @Info(
				
				title = "SpringBoot REST API Documentation",
				description = "SpringBoot REST API Documentation",
				version = 	"V1.0",
				contact = @Contact(
						
						name = "Gopikrishnan",
						email = "mgk@gmail.com",
						url = "www.google.com"
						),
				license = @License(
						name = "Apache 2.0",
						url = "ww.google.com"
						)
				),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Documentation",
				url = "www.google.com"
				)
		
		)
@SpringBootApplication
public class SpringRestApiH2dbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiH2dbApplication.class, args);
	}

}
