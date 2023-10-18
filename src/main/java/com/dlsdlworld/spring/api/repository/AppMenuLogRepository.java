package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.baserepository.BaseAppMenuLogRepository;
import com.dlsdlworld.spring.api.dto.AppMenuLogDto;
import com.dlsdlworld.spring.api.model.AppMenuLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppMenuLogRepository extends BaseAppMenuLogRepository<AppMenuLog> {

    Optional<AppMenuLog> findById(Long id);

    Optional<AppMenuLog> findByAppId(Long appId);


    Page<AppMenuLog> findByLogTypeOrderByCreatedOnDesc(Short logType, Pageable pageable);

    @Transactional(readOnly=true)
    @LogAdminExecution(descriptions = "앱 메뉴  로그 조회")
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    @Query( " select     a  " +
        " from       AppMenuLog a " +
   //     " WHERE  a.createdOn >= :startDt and a.createdOn < :endDt "  +     // 날짜는 항상 가져오도록 설정
        " WHERE (1=1) "+
      //  " AND    a.logType = :logType " +   @RequestParam Short logType,
        " AND (" +
        "        ( a.userAccnt like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
        "          OR  ( a.errorCd like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
        "          OR  ( a.errorMsg like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
        "     ) " )
    Page<AppMenuLog> findAllByKeyword(Pageable page,
                                      @RequestParam (required=false, defaultValue = "") String keyword);

    @Transactional(readOnly=true)
    @LogAdminExecution(descriptions = "앱 메뉴 로그 조회")
    @PreAuthorize("@security.hasPermission({'API_READ'})")
      @Query( " select     new com.dlsdlworld.spring.api.dto.AppMenuLogDto( a.id, a.userAgent, a.deviceType  , a.userIp, a.menuId, a.hospitalMenuId , a.userAccnt, a.appId, a.deviceUuid , a.userId, a.patientNo, a.hospitalId   , a.hashKey, a.errorCd, a.errorMsg, a.logType   , a.transId, a.destUrl, a.createdOn,  b.nameCd ) " +
    //  @Query( " select     new com.dlsdlworld.spring.api.service.dto.AppMenuLogDto( a.id, a.userAgent,  a.userIp, a.menuId, a.hospitalMenuId , a.userAccnt, a.appId, a.deviceUuid , a.userId, a.patientNo, a.hospitalId   , a.hashKey, a.errorCd, a.errorMsg, a.logType   , a.transId, a.destUrl, a.createdOn,  b.nameCd ) " +
    //@Query( " select     new com.dlsdlworld.spring.api.service.dto.AppMenuLogDto( a.id, a.userAccnt, a.userIp, a.menuId, a.createdOn, b.nameCd ) " +
            " from       AppMenuLog a left join Menu b " +
            " on a.menuId = b.id " +
            " WHERE  a.createdOn >= :startDt and a.createdOn < :endDt "  +     // 날짜는 항상 가져오도록 설정
           //  " WHERE (1=1) "+
            //  " AND    a.logType = :logType " +   @RequestParam Short logType,
            " AND (" +
            "        ( a.userAccnt like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
            "          OR  ( a.errorCd like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "          OR  ( a.errorMsg like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
              "        OR  ( b.nameCd like CONCAT('%',:keyword,'%') OR :keyword = '' )  " +
            "     ) " )

   Page<AppMenuLogDto> findAllByKeywordDto(Pageable page,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime startDt,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime endDt,
                                         @RequestParam (required=false, defaultValue = "") String keyword);


}
