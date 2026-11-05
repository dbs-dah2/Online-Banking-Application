package com.dbs.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI bankingOpenApi() {
		return new OpenAPI().info(new Info().title("REST API").description("desd").version("1.0.0")
				.contact(new Contact().name("susmita"))
				.license(new License().name("MIT")));
	}
}
