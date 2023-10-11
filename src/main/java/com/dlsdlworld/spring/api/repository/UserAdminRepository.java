package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.UserAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 */
public interface UserAdminRepository extends BaseUserAdminRepository<UserAdmin> {

  @Transactional(readOnly=true)
  @PreAuthorize("@security.hasPermission({'API_READ'})")
  @Query( " select a from UserAdmin a " +
      " WHERE a.appliedOn >= :startDt and a.appliedOn <= :endDt " +
      " AND (" +
      "       (a.adminStatus like CONCAT('%', :status,'%') or :status='')" +
      "     )" +
      " AND (" +
      "       (a.user.email like CONCAT('%', :keyword, '%') or :keyword='')" +
      "     ) ")
  Page<UserAdmin> findAllByKeyword(Pageable page,
                                   @RequestParam(name="status", required=false, defaultValue = "") String status,
                                   @RequestParam (name="keyword", required=false, defaultValue = "") String keyword,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt);


}
