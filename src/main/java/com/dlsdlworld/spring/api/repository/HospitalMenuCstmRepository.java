package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.HospitalMenuCstm;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Set;

/**
 * 병원별 메뉴의 속성 정보 Permission은?
 */
public interface HospitalMenuCstmRepository extends BaseHospitalMenuCsmtRepository<HospitalMenuCstm> {


    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ','HOSPITAL_WRITE','HOSPITAL_DELETE'})")
    Set<HospitalMenuCstm> findByHospitalMenuId(Long hospitalMenuId);

}

