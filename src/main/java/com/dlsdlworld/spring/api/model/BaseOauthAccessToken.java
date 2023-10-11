package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * OAuth 인증 토큰
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseOauthAccessToken extends BasePersistable {

    private String tokenId;

    private byte[] token;

    private String authenticationId;

    private String userName;

    private String clientId;

    private byte[] authentication;

    private String refreshToken;
}
