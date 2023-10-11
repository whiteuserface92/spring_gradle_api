package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.dto.HospitalOptSiteMapDto;
import com.dlsdlworld.spring.api.model.HospitalOptSiteMap;
import com.dlsdlworld.spring.api.model.HospitalOptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 */
public interface HospitalOptSiteMapRepository extends BaseHospitalOptSiteMapRepository<HospitalOptSiteMap> {

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
    //Long id, Long hospitalId, String refCd, String defCd, String siteCd ,String codeCls
    @Query( " select   new com.dlsdlworld.spring.api.dto.HospitalOptSiteMapDto( a.id,  a.hospitalId, b.codeCls, b.code, a.siteCd, b.codeNm ,b.codeCls ) " +
            " from       CommonCode b left join " +
            " HospitalOptSiteMap a on b.code = a.defCd and b.codeCls = a.refCd and a.hospitalId =:hospitalId " +
            " WHERE  (1=1) "  +
             " AND b.codeCls IN ( :refVals ) " +
            "  AND b.codeType ='2' " +
            "  AND (" +
            "        ( b.code like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
            "          OR  (  b.codeNm  like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "          OR  (  b.codeCls  like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "     ) " +
            "     order by b.codeCls , b.dispOrd  "
             )
    Page<HospitalOptSiteMapDto> findAllByHospitalList(Pageable page, Long hospitalId
      , @RequestParam(required=false, defaultValue = "") String keyword
    , List<String> refVals);


    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ','HOSPITAL_WRITE','HOSPITAL_DELETE'})")
    List<HospitalOptionCode> findByHospitalId(Long hospitalId);

}

