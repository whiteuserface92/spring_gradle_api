package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseOauthAccessToken;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = "oauth_access_token")
public class OauthAccessToken extends BaseOauthAccessToken {
}
