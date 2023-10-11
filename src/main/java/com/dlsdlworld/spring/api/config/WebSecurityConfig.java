package com.dlsdlworld.spring.api.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends OAuth2ResourceServerConfig {

    private final ProfileHelper profileHelper;

    private final CheckedService checkedService;

    @Autowired
    public WebSecurityConfig(ProfileHelper profileHelper
            , CheckedService checkedService, SecurityConfig securityConfig, Environment env) {
        super(securityConfig, env);
        this.profileHelper = profileHelper;
        this.checkedService = checkedService;
    }

     /* 로그인 처리 안해도 되는것들 모음 */

    @Override
    @Bean
    public void configure(HttpSecurity http) throws Exception {

        if(profileHelper.isProd()) {//운영계만 IP 내/외부망 필터 적용
            http.addFilterAfter(new CustomIpCheckedFilter(checkedService), BasicAuthenticationFilter.class);
        }

        http.anonymous().authorities(anonymousAuthorities());

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/log/download/**").permitAll()
                // .antMatchers("/rest/**").authenticatejd()
                .antMatchers( "/uploadFile/**"  ).permitAll()
                .antMatchers( "/downloadFile/**"  ).permitAll()
                .antMatchers( "/appFileCode/**"  ).permitAll()
                .antMatchers( "/rest/ui/service/hosInfoList"  ).permitAll()
                .antMatchers( "/rest/getUserAccessHistory"  ).permitAll()
                .antMatchers( "/rest/insertUserAccessHistory"  ).permitAll()
        ;
    }
}

