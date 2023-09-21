package com.dlsdlworld.spring.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories("com.dlsdlworld.spring.api.repository")
@EnableWebSecurity
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
