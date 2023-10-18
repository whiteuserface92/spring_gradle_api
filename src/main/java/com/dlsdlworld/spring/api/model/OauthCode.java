package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseOauthCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.OauthCode)
public class OauthCode extends BaseOauthCode {
}
