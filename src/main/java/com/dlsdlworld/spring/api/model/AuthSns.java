package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseAuthSns;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AuthSns)
public class AuthSns extends BaseAuthSns {

    @ManyToOne
    @JoinColumn(name = "user_auth_id", nullable = false)
    private UserAuth userAuth;
}
