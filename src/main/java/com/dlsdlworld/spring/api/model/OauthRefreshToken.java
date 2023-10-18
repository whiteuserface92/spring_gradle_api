package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseOauthRefreshToken;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.OauthRefreshToken)
public class OauthRefreshToken extends BaseOauthRefreshToken {
}
