package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.model.AppLog;
import com.dlsdlworld.spring.api.types.ActionTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

/**
 * 모델린 변경으로 구현 방식 변경
 */
@RepositoryRestResource
public interface AppLogRepository<T extends AppLog> extends PagingAndSortingRepository<T, Long>{

  @Override
  @PreAuthorize("@security.hasPermission({'APP_READ'})")
  Set<T> findAll(Sort sort);

  @Override
  @PreAuthorize("@security.hasPermission({'APP_READ'})")
  Page<T> findAll(Pageable pageable);

  @Override
  @LogAdminExecution(code = ActionTypes.CREATE, descriptions = "앱 정보 등록 및 수정")
  @PreAuthorize("@security.hasPermission({'APP_WRITE'})")
  <S extends T> S save(S entity);

  @Transactional(readOnly=true)
  @LogAdminExecution(descriptions = "앱 로그 조회")
  //@PreAuthorize("@security.hasPermission({'API_READ'})")
  @Query( " select     a  " +
           " from       AppLog a  " +
           " WHERE  (1=1)"  +
           " AND a.createdOn >= :startDt and a.createdOn < :endDt  "+
         //  " AND   ( ( a.errorEnabled = :errorEnabled  ) OR ( :errorEnabled ='' ) ) " +
           " AND (" +
           "        ( a.reqData like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
           "          OR  ( a.reqUrl like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
           "          OR  ( a.respData like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
           "          OR  ( a.userIp like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
           "        OR  ( a.userAccnt like CONCAT('%',:keyword,'%') OR :keyword = '' )  " +
           "     ) " )
  Page<AppLog> findAllByKeyword(Pageable page,
                                   //@RequestParam (required=false, defaultValue = "") String errorEnabled,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime startDt,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime endDt,
                                         @RequestParam (required=false, defaultValue = "") String keyword) ;
  /**
   * 스케줄러 RedisLogToDbInsertScheduler 에서 서버 job으로 실행되어서 권한 체크 우선 해제함
   * @param entities
   * @param <S>
   * @return
   */
  @Override
 // @PreAuthorize("@security.hasPermission({'APP_WRITE'})")
  <S extends T> Iterable<S> saveAll(Iterable<S> entities);

  @Override
  @PreAuthorize("@security.hasPermission({'APP_READ'})")
  Optional<T> findById(Long id);

  @Override
  @PreAuthorize("@security.hasPermission({'APP_READ'})")
  boolean existsById(Long id);

  @Override
  @PreAuthorize("@security.hasPermission({'APP_READ'})")
  Set<T> findAll();

  @Override
  @PreAuthorize("@security.hasPermission({'APP_READ'})")
  Set<T> findAllById(Iterable<Long> ids);

  @Override
  @PreAuthorize("@security.hasPermission({'APP_READ'})")
  long count();

  @Override
  @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "앱 정보 삭제")
  @PreAuthorize("@security.hasPermission({'APP_DELETE'})")
  void deleteById(Long id);

  @Override
  @PreAuthorize("@security.hasPermission({'APP_DELETE'})")
  void delete(T entity);

  @Override
  @RestResource(exported = false)
  @PreAuthorize("@security.hasPermission({'APP_DELETE'})")
  void deleteAll(Iterable<? extends T> entities);

  @Override
  @RestResource(exported = false)
  @PreAuthorize("@security.hasPermission({'APP_DELETE'})")
  void deleteAll();

}
