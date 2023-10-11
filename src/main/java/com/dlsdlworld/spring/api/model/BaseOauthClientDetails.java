package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseOauthClientDetails extends BasePersistable {

    private String clientId;

    private String resourceIds;

    @Column(nullable = false)
    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    @Column(nullable = false)
    private Integer accessTokenValidity;

    @Column(nullable = false)
    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;
}
