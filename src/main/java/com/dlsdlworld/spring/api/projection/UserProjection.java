package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.User;
import com.dlsdlworld.spring.api.model.UserPatient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.rest.core.config.Projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 관리자 조회용 projection
 */
@Projection(name = "user_projection", types = { User.class })
public interface UserProjection extends BaseUserProjection {

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

    Set<UserAuthProjection> getUserAuths();

    @JsonIgnore
    Set<UserPatient> getUserPatients();

    default Set<HospitalSimpleProjection> getUserHospitals(){
        return this.getUserPatients().stream().map(userPatient -> {
            return (HospitalSimpleProjection)userPatient.getHospital();
        }).collect(Collectors.toSet());
    }

}
