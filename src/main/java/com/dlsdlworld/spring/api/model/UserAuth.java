package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.UserAuth)
public class UserAuth extends BaseUserAuth {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //TODO System admin의 경우 app과 연관이 없으므로 임시로 nullable로 만든다
    @ManyToOne
    @JoinColumn(name = "app_id", nullable = true)
    private App app;

    @OneToMany(mappedBy = "userAuth")
    private Set<AuthSns> authSnses;

    @OneToMany(mappedBy = "userAuth")
    private Set<AuthSimple> authSimples;

    @OneToOne(mappedBy = "userAuth")
    private AuthPwd authPwd;
}
