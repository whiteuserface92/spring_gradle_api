package com.dlsdlworld.spring.api.service;

import com.dlsdlworld.spring.api.config.SecurityConfig;
import com.dlsdlworld.spring.api.exception.ForbiddenException;
import com.dlsdlworld.spring.api.exception.InvalidUserCredentialsException;
import com.dlsdlworld.spring.api.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class OAuthClientService {
    private static final Logger log = LoggerFactory.getLogger(OAuthClientService.class);
    @Resource
    private SecurityConfig SecurityConfig;
    public static final int DEFAULT_TIMEOUT = 5000;

    public OAuthClientService(SecurityConfig SecurityConfig) {
        this.SecurityConfig = SecurityConfig;
    }

    public Map<String, Object> getRefreshToken(final String refreshToken) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap();
        params.add("grant_type", "refresh_token");
        params.add("refresh_token", refreshToken);
        params.add("client_id", this.SecurityConfig.getOauthClientId());
        return this.getToken(params);
    }

    public Map<String, Object> getAccessToken(final String username, final String password) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap();
        params.add("grant_type", "password");
        params.add("username", username);
        params.add("password", password);
        return this.getToken(params);
    }

    private Map<String, Object> getToken(MultiValueMap<String, Object> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(this.SecurityConfig.getOauthClientId(), this.SecurityConfig.getOauthClientSecret());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(params, headers);

        try {
            RestTemplate restTemplate = this.getRestTemplate();
            log.trace("SecurityConfig:{}", this.SecurityConfig);
            ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
            };
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(this.SecurityConfig.getTokenUrl(), HttpMethod.POST, httpEntity, responseType, new Object[0]);
            return (Map)response.getBody();
        } catch (HttpClientErrorException var7) {
            if (var7.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new InvalidUserCredentialsException(var7.getCause());
            } else if (var7.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new UnauthorizedException(var7.getCause());
            } else if (var7.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new ForbiddenException(var7.getCause());
            } else {
                throw new UnauthorizedException(var7.getCause());
            }
        } catch (Exception var8) {
            throw new UnauthorizedException(var8.getCause());
        }
    }

    private RestTemplate getRestTemplate() {
        int timeout = this.SecurityConfig.getOauthRequestTimeout() == null ? 5000 : this.SecurityConfig.getOauthRequestTimeout();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(timeout);
        factory.setReadTimeout(timeout);
        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }
}
