package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseOauthCode extends BasePersistable {

    private String code;

    @Lob
    private byte[] authentication;
}
