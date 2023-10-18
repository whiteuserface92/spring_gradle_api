package com.dlsdlworld.spring.api.baseprojection;


import com.dlsdlworld.spring.api.types.GenderTypes;

import java.time.LocalDateTime;

/**
 */
public interface BaseUserProjection {

    Long getId();

    Boolean getEnabled();

    GenderTypes getSex();

    String getUserNm();

    String getBirthday();

    String getEmail();

    String getPhoneNo();

    String getPostNo();

    String getAddr1();

    String getAddr2();

    String getJoinType();

    String getMyCi();


    String getRepresentCi();

    String getHospitalCd();

    String getTelecomCd();

    String getCountryCd();

    Integer getMileage();

    LocalDateTime getUpdatedOn();

    Long getUpdatedBy();

    LocalDateTime getCreatedOn();
}
