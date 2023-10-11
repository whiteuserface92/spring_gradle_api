package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseOauthRefreshToken extends BasePersistable {
    private String tokenId;
    private byte[] token;
    private byte[] authentication;
}
