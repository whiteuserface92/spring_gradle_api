package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.Hospital;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * 병원정보 프로젝션
 */
@Projection(name = "hospital_simple_projection", types = {
        Hospital.class
})
public interface HospitalSimpleProjection extends BaseModifiableProjection {

    String getHospitalCd();

    String getHospitalNmCd();

    @Value("#{@commonProjector.getLocaleMessage(target.hospitalNmCd)}")
    String getHospitalNm();

    String getHospitalType();
}
