package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.User;
import com.dlsdlworld.spring.api.model.UserRole;
import org.springframework.data.rest.core.config.Projection;

/**
 * 관리자 조회용 projection
 */
@Projection(name = "userRole_projection", types = { User.class, UserRole.class})
public interface UserRoleProjection extends BaseModifiableProjection {

    RoleProjection getRole();

}
