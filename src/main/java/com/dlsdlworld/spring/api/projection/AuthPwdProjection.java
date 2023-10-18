package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.baseprojection.BaseAuthPwdProjection;
import com.dlsdlworld.spring.api.model.User;
import com.dlsdlworld.spring.api.model.UserAuth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.rest.core.config.Projection;


/**
 * 관리자 조회용 projection
 */
@Projection(name = "user_auth_pwd_projection", types = { User.class, UserAuth.class})
public interface AuthPwdProjection extends BaseAuthPwdProjection {

    @JsonIgnore
    String getPwd();

    @JsonIgnore
    String getHashSalt();

    @JsonIgnore
    String getHashAlgo();

}
