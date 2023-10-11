package com.dlsdlworld.spring.api.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.dlsdlworld.spring.api.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 관리자 조회용 projection
 */
@Projection(name = "admin_projection", types = { User.class })
public interface AdminProjection extends BaseUserProjection  {

    @JsonIgnore
    String getMyCi();

    @JsonIgnore
    String getRepresentCi();

    default List<RoleProjection> getRoles(){
        List<RoleProjection> roles = new ArrayList<>();
        for (UserRoleProjection userRole: getUserRoles()) {
            roles.add(userRole.getRole());
        }
        return roles;
    };

    Set<UserRoleProjection> getUserRoles();

    /*Set<UserAuthProjection> getUserAuths();

    Set<UserPatient> getUserPatients();


    Set<UserDevice> getUserDevices();

    Set<PushRequest> getPushRequests();*/
}
