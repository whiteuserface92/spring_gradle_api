package com.dlsdlworld.spring.api.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//class WebSecurityConfig extends OAuth2ResourceServerConfig {
//
//    private final ProfileHelper profileHelper;
//
//    private final CheckedService checkedService;
//
//    @Autowired
//    public WebSecurityConfig(ProfileHelper profileHelper
//            , CheckedService checkedService, SecurityConfig securityConfig, Environment env) {
//        super(securityConfig, env);
//        this.profileHelper = profileHelper;
//        this.checkedService = checkedService;
//    }
//
//     /* 로그인 처리 안해도 되는것들 모음 */
//
//    @Override
//    @Bean
//    public void configure(HttpSecurity http) throws Exception {
//
//        if(profileHelper.isProd()) {//운영계만 IP 내/외부망 필터 적용
//            http.addFilterAfter(new CustomIpCheckedFilter(checkedService), BasicAuthenticationFilter.class);
//        }
//
//        http.anonymous().authorities(anonymousAuthorities());
//
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//        ;
//    }
//}
////


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() { // bcrypt 해쉬 알고리즘 이용
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**")
                .antMatchers("/h2-console/**", "/swagger-ui/**");
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
            security.csrf().disable();
            security.authorizeRequests()
                .antMatchers("/").permitAll();
            security.anonymous().disable();

//        security
//                .authorizeRequests()
//                .antMatchers("/","/login").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN") // '/admin/*' 요청은 ADMIN 권한을 가진 사용자만 접근 가능
//                .anyRequest().authenticated() // 그 외 모든 요청은 인증된 사용자만 접근 가능
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/loginsuccess")
//                .failureUrl("/fail")
//                .permitAll()
//                .and()
//                .logout()
//                .and()
//                .csrf().disable();
    }


}
