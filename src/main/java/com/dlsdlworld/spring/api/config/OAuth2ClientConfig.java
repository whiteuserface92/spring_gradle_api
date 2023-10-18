package com.dlsdlworld.spring.api.config;

import com.dlsdlworld.spring.api.service.OAuthClientService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnProperty(
        prefix = "common",
        name = {"oauth-client-id", "oauth-client-secret", "token-url"}
)
public class OAuth2ClientConfig {
    @Resource
    private SecurityConfig SecurityConfig;

    public OAuth2ClientConfig() {
    }

    @Bean
    public OAuthClientService oAuthClientService() {
        OAuthClientService oAuthClientService = new OAuthClientService(this.SecurityConfig);
        return oAuthClientService;
    }
}
