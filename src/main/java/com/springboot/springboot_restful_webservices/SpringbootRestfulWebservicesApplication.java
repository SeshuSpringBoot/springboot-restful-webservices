package com.springboot.springboot_restful_webservices;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Restful API Documentation",
				description = "Spring Boot Restful API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Seshu",
						email = "nagasesha999@gmail.com",
						url = "https://www.nagasesha.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.nagasesha.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot user management documentation",
				url = "https://www.nagasesha.net.user_management.html"
		)
)
public class SpringbootRestfulWebservicesApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
