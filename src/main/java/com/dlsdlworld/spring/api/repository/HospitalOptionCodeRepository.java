package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.dto.HospitalOptionCodeDto;
import com.dlsdlworld.spring.api.model.HospitalOptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : woong.jang
 * Date : 2022/02/15
 * Time : 15:46
 */
public interface HospitalOptionCodeRepository extends BaseHospitalOptionCodeRepository<HospitalOptionCode>{

    /**
     * HospitalCD 제외 : Repo에서 조회시 사용
     * @param id
     * @param code
     * @param codeNm
     * @param optVal
     * @param refVal
     * @param hospitalId
     */
    @Transactional(readOnly=true)
    @LogAdminExecution(descriptions = "별병원별 옵션값 조회")
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ','HOSPITAL_WRITE','HOSPITAL_DELETE'})")
    @Query( " select   new com.dlsdlworld.spring.api.dto.HospitalOptionCodeDto( a.id,  b.code, b.codeNm ,a.optVal,b.refVal,  a.hospital.id ) " +
            " from       CommonCode b left join " +
            " HospitalOptionCode a on b.code = a.optCd and a.hospital.id =:hospitalId " +
            " WHERE  (1=1) "  +
            " AND b.codeCls ='OPT_CD' and b.codeType ='2' " +
            " AND (" +
            "        ( b.code like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
            "          OR  (  b.codeNm  like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "     ) " +
            "     order by b.code , b.dispOrd  "
             )

    Page<HospitalOptionCodeDto> findAllByHospitalList(Pageable page, Long hospitalId
      , @RequestParam(required=false, defaultValue = "") String keyword);


    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ','HOSPITAL_WRITE','HOSPITAL_DELETE'})")
    List<HospitalOptionCode> findByHospitalId(Long hospitalId);

}

