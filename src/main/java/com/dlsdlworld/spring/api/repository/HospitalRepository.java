package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.dto.HospitalUiDTO;
import com.dlsdlworld.spring.api.model.Hospital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project : lemoncare-plus-parent
 * Plus 모델링 변경으로 수정
 * Developer : ricky
 * Date : 2020/01/30
 * Time : 15:46
 */
public interface HospitalRepository extends BaseHospitalRepository<Hospital> {


    @Transactional(readOnly=true)
    @Query("SELECT new com.dlsdlworld.spring.api.dto.HospitalUiDTO(a.id, a.hospitalCd, a.hospitalNm) FROM Hospital a WHERE  a.enabled = true and a.hospitalCd <> 'lemonhc' order by a.id asc ")
    List<HospitalUiDTO> findByHospitalCdAndNmCustom();

}

