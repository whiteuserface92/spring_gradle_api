package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.dto.CommonCodeDto;
import com.dlsdlworld.spring.api.model.CommonCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 */
public interface CommonCodeRepository extends BaseCommonCodeRepository<CommonCode>{

    @PreAuthorize("@security.hasPermission({'CODE_READ'})")
    @Query("SELECT DISTINCT commonCode.codeCls FROM CommonCode commonCode")
    Set<String> findDistinctCodeCls();

    @Transactional(readOnly=true)
    @LogAdminExecution(descriptions = "공통 코드 조회_코드 상세 codeType='2'")
    @PreAuthorize("@security.hasPermission({'CODE_READ'})")
    @Query("SELECT new com.dlsdlworld.spring.api.dto.CommonCodeDto(a.id, a.code, a.codeNm, a.dispOrd) FROM CommonCode a WHERE a.codeCls = :codeCls AND a.codeType='2' order by a.dispOrd asc ")
    List<CommonCodeDto> findByCodeClsDetails(String codeCls);


    @Transactional(readOnly=true)
    @LogAdminExecution(descriptions = "공통 코드 조회_팝업 상세 codeType='2'")
    @PreAuthorize("@security.hasPermission({'CODE_READ'})")
    @Query("SELECT new com.dlsdlworld.spring.api.dto.CommonCodeDto(a.id, a.code, a.codeNm, a.dispOrd) FROM CommonCode a WHERE a.codeCls = :codeCls AND a.codeType='2' order by a.dispOrd asc  ")
    List<CommonCodeDto> findByCodeClsDetailList(String codeCls);

    @Transactional(readOnly=true)
    @LogAdminExecution(descriptions = "공통 코드(SITE_CD_MAP) 대분류에서 refVal조회 codeType='2'")
    @PreAuthorize("@security.hasPermission({'CODE_READ'})")
    @Query("SELECT new com.dlsdlworld.spring.api.dto.CommonCodeDto(a.id, a.refVal, a.codeNm, a.dispOrd) FROM CommonCode a WHERE a.codeCls = :codeCls AND a.codeType='2' order by a.dispOrd asc ")
    List<CommonCodeDto> findByCodeClsRefValSiteCdMap(String codeCls);


    @LogAdminExecution(descriptions = "공통 코드 조회")
    @PreAuthorize("@security.hasPermission({'CODE_READ'})")
    @Query( " select     a  " +
            " from       CommonCode a " +
            " WHERE  (1=1)"  +     // 날짜는 항상 가져오도록 설정
            " AND    a.codeType = :codeType " +
            " AND (" +
            "        ( a.codeCls like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
            "          OR  ( a.codeNm like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "          OR  ( a.codeDesc like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "     ) " )
    Page<CommonCode> findAllByKeyword(Pageable page,
                                              @RequestParam String codeType,
                                              @RequestParam (required=false, defaultValue = "") String keyword);
    /** 공통 코드의 경우 생성일로 조회 할 필요가 없으므로 날짜 조회 조건은 제외하여 수정함
     */
    @Transactional(readOnly=true)
    @LogAdminExecution(descriptions = "공통 코드 조회")
    @PreAuthorize("@security.hasPermission({'CODE_READ'})")
    @Query( " select     a  " +
            " from       CommonCode a " +
            " WHERE  a.createdOn >= :startDt and a.createdOn < :endDt "  +     // 날짜는 항상 가져오도록 설정
            " AND    a.codeType = :codeType " +
            " AND (" +
            "        ( a.codeCls like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
            "          OR  ( a.codeNm like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "          OR  ( a.codeDesc like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "     ) " )
    Page<CommonCode> findAllByKeyword_2022_02(Pageable page,
                                      @RequestParam String codeType,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime startDt,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime endDt,
                                      @RequestParam (required=false, defaultValue = "") String keyword);



}

