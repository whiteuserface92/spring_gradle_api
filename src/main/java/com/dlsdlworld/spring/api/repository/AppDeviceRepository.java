package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.AppDevice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 */
public interface AppDeviceRepository extends BaseAppDeviceRepository<AppDevice> {

    /**
     * 앱별 단말기 정보 복합키(appId, userDeviceId) 조회
     * @param appId
     * @param userDeviceId
     * @return
     */
    @PreAuthorize("@security.hasPermission({'USER_READ'})")
    @Query("select ad from AppDevice as ad where ad.app.id = ?1 and ad.userDevice.id = ?2")
    AppDevice findByAppIdAndDeviceId(Long appId, Long userDeviceId);
//    Set<AppDevice> findByAppIdAndDeviceId(Long appId, Long userDeviceId);

//    @Query("update AppDevice ad set ad.updatedBy = ?1, ad.pushToken = ?2, ad.lastAuthType = ?3 where ad.id = ?4")
//    void saveById(Long updatedBy, String pushToken, String lastAuthType, @PathVariable Long id);


}

