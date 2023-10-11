package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.Terms;
import com.dlsdlworld.spring.api.projection.TermsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

/**
 */
public interface TermsRepository extends BaseTermsRepository<Terms> {

    /**
     * 약관리스트
     * @param termsOwnerCds
     * @return
     */
    @Query(value =
            "select a.id, " +
                    "       a.terms_owner_cd as \"termsOwnerCd\", " +
                    "       a.terms_cd as \"termsCd\", " +
                    "       a.terms_nm as \"termsNm\", " +
                    "       a.terms_ver as \"termsVer\", " +
                    "       a.started_on as \"startedOn\", " +
                    "       a.required as \"required\" " +
                    "  from terms_def a " +
                    "inner join ( " +
                    "   select max(id) as id " +
                    "     from terms_def " +
                    " group by terms_owner_cd, terms_cd " +
                    "   having terms_owner_cd in(:termsOwnerCds) " +
                    ") b on a.id = b.id " +
                    "order by a.disp_ord"
            , nativeQuery = true)
    List<TermsProjection> findByTerms(@Param("termsOwnerCds") List<String> termsOwnerCds);

    @Transactional(readOnly=true)
//    @LogAdminExecution(code = ActionTypes.READ, descriptions = "약관 조회")
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    @Query(" select a from Terms a " +
            " WHERE  a.createdOn >= :startDt and a.createdOn < :endDt "  +     // 날짜는 항상 가져오도록 설정 " +
            " AND (" +
            "        ( a.termsNm like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
            "          OR ( a.termsOwnerCd like CONCAT('%',:keyword,'%') OR :keyword = '' ) " +
            "     ) ")
    Page<Terms> findByKeyword(Pageable page,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt,
                              @RequestParam (required=false, defaultValue = "") String keyword);

}

