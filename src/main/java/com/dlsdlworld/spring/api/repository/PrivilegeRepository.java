package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.baserepository.BasePrivilegeRepository;
import com.dlsdlworld.spring.api.model.Privilege;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/30
 *  // "        (  a.privilegeNm like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
 * Time : 15:46
 */
public interface PrivilegeRepository extends BasePrivilegeRepository<Privilege> {

    @Transactional(readOnly=true)
    @LogAdminExecution(descriptions = "권한조회 ")
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    @Query( " select a " +
            " from       Privilege a " +
            //   " WHERE  a.createdOn >= :startDt and a.createdOn < :endDt "  +     // 날짜는 항상 가져오도록 설정
            " WHERE  (1=1) "  +
            "     AND   ( a.privilegeDesc like CONCAT('%',:keyword,'%') OR :keyword = '' )"
    )
    Page<Privilege> findAllByKeyword(Pageable page,
                                        @RequestParam(required=false, defaultValue = "") String keyword);
}

