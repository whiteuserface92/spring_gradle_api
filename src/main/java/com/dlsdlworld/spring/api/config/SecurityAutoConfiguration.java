package com.dlsdlworld.spring.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({SecurityConfig.class})
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SecurityAutoConfiguration.class);

    public SecurityAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityConfig lemonSecurityConfig(SecurityProperties props) {
        log.trace("AuthProperties:{}", props);
        SecurityConfig config = new SecurityConfig();
        config.setRsaKeyValue(props.getRsaKeyValue());
        config.setOauthClientId(props.getOauthClientId());
        config.setOauthClientSecret(props.getOauthClientSecret());
        config.setTokenUrl(props.getTokenUrl());
        return config;
    }
}
