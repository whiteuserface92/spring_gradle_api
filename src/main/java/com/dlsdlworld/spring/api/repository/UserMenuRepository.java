package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.UserMenu;
import com.dlsdlworld.spring.api.types.MenuTypes;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 */
public interface UserMenuRepository extends BaseUserMenuRepository<UserMenu> {

    @PreAuthorize("@security.hasPermission({'MENU_READ'})")
    UserMenu findByUserIdAndMenuTypeAndLevel(Long userId, MenuTypes menuTypes, Short level);

    @PreAuthorize("@security.hasPermission({'MENU_DELETE'})")
    void deleteByUserIdAndMenuTypeAndLevel(Long userId, MenuTypes menuType, Short level);
}
