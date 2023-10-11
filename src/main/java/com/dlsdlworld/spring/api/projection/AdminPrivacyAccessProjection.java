package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.AdminPrivacyAccess;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 관리자 민감정보 접근 기록
 */
@Projection(name = "AdminPrivacyAccessProjection", types = {AdminPrivacyAccess.class})
public interface AdminPrivacyAccessProjection {

    Long getId();
    String getUserAccnt();
    String getHospitalCd();
    String getService();
    String getAction();
    String getDescription();
    String getRequest();
    String getResult();
    String getIp();
    LocalDate getYmd();
    LocalDateTime getCreatedOn();
}
