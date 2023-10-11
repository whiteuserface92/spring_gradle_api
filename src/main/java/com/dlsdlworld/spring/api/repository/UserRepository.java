package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 */
public interface UserRepository extends BaseUserRepository<User>{

    @PreAuthorize("@security.hasPermission({'USER_READ','USER_WRITE','USER_DELETE'})")
    Set<User> findAllByUserNmContainsOrEmailContains(String userNm, String email);


    /**
     * 관리자에서 리스트 출력용 (jpql)
     * @param keyword
     * @param page
     * @return
     */
    @Transactional(readOnly=true)
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    @Query( " select     a " +
            " from       User a " +
            " WHERE  (1=1) "  +     // 날짜는 항상 가져오도록 설정
            " AND (" +
            "        ( a.email like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
            "          OR ( a.userNm like CONCAT('%',:keyword,'%') OR :keyword = '' ) " +
            "     ) "
    )
    Page<User> findAllByKeyword(Pageable page,
                                             /* @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt,*/
                                              @RequestParam (required=false, defaultValue = "") String keyword
                                              // @RequestParam (required=false, defaultValue = "") String domainDesc
    );

    @Transactional(readOnly=true)
    @Query(nativeQuery = true, value = "select id from user_mst where my_ci = ?1")
    @PreAuthorize("@security.hasPermission({'USER_READ'})")
    Long findByMyCi(String myCi);


}
