//package com.dlsdlworld.spring.api.config;
//
//import com.dlsdlworld.spring.api.types.PermissionTypes;
//import com.dlsdlworld.spring.api.utils.AuthUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//@EnableResourceServer
//public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
//    private static final Logger log = LoggerFactory.getLogger(OAuth2ResourceServerConfig.class);
//
//    private final SecurityConfig securityConfig;
//
//    private final Environment env;
//
//
//    public OAuth2ResourceServerConfig(SecurityConfig securityConfig, Environment env) {
//        this.securityConfig = securityConfig;
//        this.env = env;
//    }
//
//    public void configure(HttpSecurity http) throws Exception {
//        ((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()).authorizeRequests().anyRequest()).permitAll().and()).anonymous().authorities(AuthUtils.anonymousAuthorities());
//    }
//
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenServices(this.tokenServices());
//    }
//
//    protected List<GrantedAuthority> anonymousAuthorities() {
//        return Arrays.asList(new SimpleGrantedAuthority(PermissionTypes.HOSPITAL_READ.getCode()), new SimpleGrantedAuthority(PermissionTypes.MESSAGE_READ.getCode()), new SimpleGrantedAuthority(PermissionTypes.USER_WRITE.getCode()), new SimpleGrantedAuthority(PermissionTypes.APP_READ.getCode()), new SimpleGrantedAuthority(PermissionTypes.AUTHORITY_READ.getCode()), new SimpleGrantedAuthority(PermissionTypes.TERMS_READ.getCode()));
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public TokenStore tokenStore() {
//        TokenStore tokenStore = new JwtTokenStore(this.accessTokenConverter());
//        log.trace("@@JwtTokenStore:{}", tokenStore);
//        return tokenStore;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setVerifierKey(this.securityConfig.getRsaKeyValue());
//        log.trace("@@JwtAccessTokenConverter:{}", converter);
//        return converter;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(this.tokenStore());
//        log.trace("@@DefaultTokenServices:{}", defaultTokenServices);
//        return defaultTokenServices;
//    }
//}
//
