package com.dlsdlworld.spring.api.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.dlsdlworld.spring.api.model.Privilege;
import com.dlsdlworld.spring.api.model.Role;
import com.dlsdlworld.spring.api.model.RolePrivilege;
import org.springframework.data.rest.core.config.Projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 역할정보 프로젝션

 */
@Projection(name = "role_projection1", types = { Role.class, Privilege.class })
public interface RoleProjection {

    String getId();

    String getRoleNm();

    String getRoleDesc();

    /**
     * 프로젝터를 통해 권한정보 리스트 조회
     * @return 권한정보 리스트
     */
    default List<Privilege> getPrivileges() {
        List<Privilege> privileges = new ArrayList<>();
        for (RolePrivilege rolePrivilege : getRolePrivileges()) {
            privileges.add(rolePrivilege.getPrivilege());
        }

        return privileges;
    }

    @JsonIgnore
    Set<RolePrivilege> getRolePrivileges();


}
