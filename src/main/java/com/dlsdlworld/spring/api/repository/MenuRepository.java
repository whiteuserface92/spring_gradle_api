package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.baserepository.BaseMenuRepository;
import com.dlsdlworld.spring.api.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 */
public interface MenuRepository extends BaseMenuRepository<Menu> {

    @Transactional(readOnly=true)
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    @Query( " select     a  " +
            " from       Menu a " +
            " WHERE (1=1)"  +     // 날짜는 항상 가져오도록 설정
            " AND (" +
            "        ( a.nameCd like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
            "          OR  ( a.menuDesc like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "          OR  ( a.imgUrlCd like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "          OR  ( a.serviceUrl like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "     ) " )
    Page<Menu> findAllByKeyword(Pageable page,
                                @RequestParam (required=false, defaultValue = "") String keyword);

}
