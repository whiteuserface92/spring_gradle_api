package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseAuthPwd;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AuthPwd)
public class AuthPwd extends BaseAuthPwd {

    @JsonIgnore
    private String pwd;

    @JsonIgnore
    private String hashSalt;

    @JsonIgnore
    private String hashAlgo;

   /* @OneToOne
//    @JoinColumn(name = "user_auth_id", foreignKey = @ForeignKey(name = "FK_AuthPwd_UserAuth", value = ConstraintMode.CONSTRAINT))
    @JoinColumn(name = "user_auth_id", foreignKey = @ForeignKey(name = "fk_auth_pwd_user_auth", value = ConstraintMode.CONSTRAINT))
    private UserAuth userAuth;*/

    /**
     * 사용자인증  ID 컬럼 인증 위해 현재 값 처리 로직
     */
    @OneToOne
    @JoinColumn(name = "user_auth_id", nullable = false)
    private UserAuth userAuth;
}
