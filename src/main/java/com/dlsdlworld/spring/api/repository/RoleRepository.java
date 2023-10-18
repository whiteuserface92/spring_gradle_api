package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.baserepository.BaseRoleRepository;
import com.dlsdlworld.spring.api.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 */
public interface RoleRepository extends BaseRoleRepository<Role> {


    @Transactional(readOnly=true)
    @LogAdminExecution(descriptions = "ROLE 조회")
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    @Query( " select     a  " +
            " from       Role a " +
         //   " WHERE  a.createdOn >= :startDt and a.createdOn < :endDt "  +     // 날짜는 항상 가져오도록 설정
            " WHERE  (1=1) "  +
            " AND (" +
            "        ( a.roleNm like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
            "          OR  ( a.roleDesc like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "     ) " )
    Page<Role> findAllByKeyword(Pageable page,
                                @RequestParam (required=false, defaultValue = "") String keyword);
    /*
    Page<Role> findAllByKeyword(Pageable page,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime startDt,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime endDt,
                                @RequestParam (required=false, defaultValue = "") String keyword);*/

}

