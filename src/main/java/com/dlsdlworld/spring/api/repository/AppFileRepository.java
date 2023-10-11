package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.AppFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;
import java.util.Set;

/**
 * <p>
 *   app_file 테이블 spring jpa
 * </p>
 * @author hyunmin.kim
 * @since 21.07
 */
@RepositoryRestResource
public interface AppFileRepository extends PagingAndSortingRepository<AppFile, Long>{

  @PreAuthorize("@security.hasPermission({'USER_READ'})")
  Set<AppFile> findAll(Sort sort);

  @PreAuthorize("@security.hasPermission({'USER_READ'})")
  Page<AppFile> findAll(Pageable pageable);

  @PreAuthorize("@security.hasPermission({'USER_READ'})")
  Optional<AppFile> findById(Long id);

  @PreAuthorize("@security.hasPermission({'USER_READ'})")
  boolean existsById(Long id);

  @PreAuthorize("@security.hasPermission({'USER_READ'})")
  Set<AppFile> findAll();

  @PreAuthorize("@security.hasPermission({'USER_READ'})")
  Set<AppFile> findAllById(Iterable<Long> ids);

  @PreAuthorize("@security.hasPermission({'USER_READ'})")
  long count();

  @PreAuthorize("@security.hasPermission({'USER_DELETE'})")
  void deleteById(Long id);

  @PreAuthorize("@security.hasPermission({'USER_DELETE'})")
  void delete(AppFile entity);

  @RestResource(exported = false)
  @PreAuthorize("@security.hasPermission({'USER_DELETE'})")
  void deleteAll(Iterable<? extends AppFile> entities);

  @RestResource(exported = false)
  @PreAuthorize("@security.hasPermission({'USER_DELETE'})")
  void deleteAll();

  @Override
  @RestResource(exported = false)
  @PreAuthorize("@security.hasPermission({'USER_WRITE'})")
  <S extends AppFile> S save(S entity);

  @RestResource(exported = false)
  @PreAuthorize("@security.hasPermission({'USER_WRITE'})")
  <S extends AppFile> Iterable<S> saveAll(Iterable<S> entities);

  AppFile findByAppIdAndAppVerId(long appId, long appVerId);

}
