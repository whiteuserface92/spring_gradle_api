package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 1:15 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseOauthRefreshToken extends BasePersistable {
    private String tokenId;
    private byte[] token;
    private byte[] authentication;
}
