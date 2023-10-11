package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 6:47 오후
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
