package com.dlsdlworld.spring.api.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.dlsdlworld.spring.api.model.Menu;
import com.dlsdlworld.spring.api.model.MenuRole;
import com.dlsdlworld.spring.api.model.Role;
import org.springframework.data.rest.core.config.Projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 메뉴정보 프로젝션

 */
@Projection(name = "menu_projection1", types = {Menu.class, Role.class})
public interface MenuProjection {

    String getNameCd();

    String getImgUrlCd();

    String getMenuDesc();

    String getServiceUrl();

    @JsonIgnore
    Set<MenuRole> getMenuRoles();

    /**
     * 역할정보 리스트
     * @return 역할정보 리스트
     */
    default List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        for (MenuRole menuRole : getMenuRoles()) {
            roles.add(menuRole.getRole());
        }

        return roles;
    }
}
