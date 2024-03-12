package com.andoliver46.ms.cambioservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(
				new Info()
				.title("Cambio Service API")
				.version("v1")
				.description("Documentation of Cambio Service API")
				.license(new License()
						.name("Apache 2.0")
						.url("http://springdoc.org")));
	}

}
