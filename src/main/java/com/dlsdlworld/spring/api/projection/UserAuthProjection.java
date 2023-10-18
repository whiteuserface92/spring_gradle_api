package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.baseprojection.BasePersistableProjection;
import com.dlsdlworld.spring.api.model.*;
import com.dlsdlworld.spring.api.types.AuthTypes;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 관리자 조회용 projection
 */
@Projection(name = "user_auth_projection", types = { User.class, UserAuth.class})
public interface UserAuthProjection extends BasePersistableProjection {


    AuthTypes getLastAuthType();

    LocalDateTime getLastLoggedOn();

    App getApp();

    Set<AuthSns> getAuthSnses();

    AuthPwdProjection getAuthPwd();

    Set<AuthSimple> getAuthSimples();


}
