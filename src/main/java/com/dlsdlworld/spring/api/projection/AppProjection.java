package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.App;
import com.dlsdlworld.spring.api.model.Group;
import com.dlsdlworld.spring.api.model.Hospital;
import org.springframework.data.rest.core.config.Projection;

/**
 * 앱정보 프로젝션
 */
@Projection(name = "app_hospital_group", types = App.class)
public interface AppProjection {

    String getPlatformType();

    String getAppNm();

    String getAppVer();

    String getDeployType();

    String getFcmKey();

    String getHashKey();

    String getPkgNm();

    String getStoreUrl();

    String getFidoApiKey();

    Boolean getMultiEnabled();

    String getAppState();

    /**
     * 병원정보 반환
     * @return 병원정보
     */
    Hospital getHospital();

    /**
     * 그룹정보 반환
     * @return 그룹정보
     */
    Group getGroup();
}
