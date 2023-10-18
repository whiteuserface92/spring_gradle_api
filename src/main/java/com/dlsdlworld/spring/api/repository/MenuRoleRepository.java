package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.baserepository.BaseMenuRoleRepository;
import com.dlsdlworld.spring.api.model.MenuRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 */
public interface MenuRoleRepository extends BaseMenuRoleRepository<MenuRole> {


    @Query(nativeQuery = true,
            value = "select c.id as hospital_menu_id \n" +
                    "from user_role a\n" +
                    "left join role_menu b on a.role_id = b.role_id \n" +
                    "join hospital_menu c on b.hospital_menu_id = c.id \n" +
                    "where a.user_id = ?1")
    @PreAuthorize("@security.hasPermission({'MENU_READ'})")
    List<Long> findMenuRoleHospitalMenus(Long id);

    @Query(nativeQuery = true,
            value = "select a.role_id \n" +
                    "from role_menu a\n" +
                    "where hospital_menu_id = ?1")
    @PreAuthorize("@security.hasPermission({'MENU_READ'})")
    List<Long> findMenuRoleHospitalMenuId(Long id);

    @Modifying
    @Query(nativeQuery=true, value="delete from role_menu a where a.hospital_menu_id = ?1")
    @PreAuthorize("@security.hasPermission({'MENU_DELETE'})")
    void deleteAllByHospitalMenuId(Long id);

}
