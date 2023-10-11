package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.App;
import org.springframework.data.rest.core.config.Projection;

/**
 */
@Projection(name = "app_simple", types = App.class)
public interface AppSimpleProjection {

    Long getId();


    String getAppNm();

    String getAppState();

    /**
     * 병원정보 반환
     * @return 병원정보
     */
    HospitalSimpleProjection getHospital();

    /**
     * group 은 어떻게?
     */
}
