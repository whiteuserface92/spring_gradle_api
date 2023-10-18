package com.dlsdlworld.spring.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = {"com.dlsdlworld.spring.api.adminApiController",
											"com.dlsdlworld.spring.api.repository",
											"com.dlsdlworld.spring.api.adminApiService",
											"com.dlsdlworld.spring.api.service",
											"com.dlsdlworld.spring.api.cacheloader",
											"com.dlsdlworld.spring.api.event",
											"com.dlsdlworld.spring.api.scheduler",
											"com.dlsdlworld.spring.api.config",
											"com.dlsdlworld.spring.api.projection"
                                           })
@EnableJpaRepositories({"com.dlsdlworld.spring.api.repository"})
@EnableConfigurationProperties
@EnableSpringConfigured
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}



}
