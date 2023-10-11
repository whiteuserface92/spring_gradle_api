package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.User;
import com.dlsdlworld.spring.api.model.UserAdmin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 관리자 조회용 projection
 */
@Projection(name = "user_staff_role", types = { User.class, UserAdmin.class })
public interface UserStaffsRoleProjection extends BasePersistableProjection {

    Long getId();

    String getUserNm();

    String getBirthday();

    String getEmail();

    String getPhoneNo();

    String getJoinType();

    String getHospitalCd();

    default List<RoleProjection> getRoles(){
        List<RoleProjection> roles = new ArrayList<>();
        for (UserRoleProjection userRole: getUserRoles()) {
            roles.add(userRole.getRole());
        }
        return roles;
    };


    LocalDateTime getUpdatedOn();

    Long getUpdatedBy();

    LocalDateTime getCreatedOn();

    Long getCreatedBy();

    @JsonIgnore
    Set<UserRoleProjection> getUserRoles();

}
