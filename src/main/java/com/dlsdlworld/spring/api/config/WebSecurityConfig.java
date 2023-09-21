package com.dlsdlworld.spring.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig {
    //권한 확인을 하지 않는 uri
    private static final AntPathRequestMatcher[] PERMIT_ALL_PATTERNS = new AntPathRequestMatcher[] {
            AntPathRequestMatcher.antMatcher("/test/**")
    };
    //권한 확인을 하는 uri
    private static final AntPathRequestMatcher[] AUTHENTICATION_ALL_PATTERNS = new AntPathRequestMatcher[] {
            AntPathRequestMatcher.antMatcher("/api/**")
    };

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            HandlerMappingIntrospector handlerMappingIntrospector
    ) throws Exception {
        log.info("SecurityFilterChain run");
        return httpSecurity
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers(PERMIT_ALL_PATTERNS)
                                .permitAll()
                                .requestMatchers(AUTHENTICATION_ALL_PATTERNS)
                                .authenticated()
                ).build();
    }
}

