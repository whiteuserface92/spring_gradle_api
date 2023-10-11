package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.Hospital;
import com.dlsdlworld.spring.api.model.HospitalMenu;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Set;

/**
 */
public interface HospitalMenuRepository extends BaseHospitalMenuRepository<HospitalMenu>{

    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ','HOSPITAL_WRITE','HOSPITAL_DELETE'})")
    List<HospitalMenu> findAllByHospital(Hospital hospital);


    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ','HOSPITAL_WRITE','HOSPITAL_DELETE'})")
    Set<HospitalMenu> findByHospitalIdAndProdCd(Long hospitalId, String prodCd);

}

