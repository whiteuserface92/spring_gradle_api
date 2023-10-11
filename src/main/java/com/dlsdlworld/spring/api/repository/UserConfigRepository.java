package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.dto.UserConfigDTO;
import com.dlsdlworld.spring.api.model.UserConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

/**

 */
public interface UserConfigRepository extends BaseUserConfigRepository<UserConfig> {
    @Query(nativeQuery = true,
            value = "select uc.* \n" +
                    "from user_config uc \n" +
                    "  where uc.user_id = ?1 ")
    @PreAuthorize("@security.hasPermission({'USER_READ'})")
    UserConfig findByUserId(Long id);


    @Query(nativeQuery = true,
            value = "select uc.* \n" +
                    "from user_config uc \n" +
                    "  where uc.user_id = ?1 ")
    @PreAuthorize("@security.hasPermission({'USER_READ'})")
    UserConfigDTO findByUserIdDTO(Long id);



}
