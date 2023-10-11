package com.dlsdlworld.spring.api.repository;


import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.model.BaseHospitalOptSiteMap;
import com.dlsdlworld.spring.api.types.ActionTypes;
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
 * desc: 병원별 옵션 사이트 매핑 관리 레파지 토리

 */
@RepositoryRestResource
public interface BaseHospitalOptSiteMapRepository<T extends BaseHospitalOptSiteMap> extends PagingAndSortingRepository<T, Long> {

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    Set<T> findAll(Sort sort);

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    Page<T> findAll(Pageable pageable);

    @Override
    @LogAdminExecution(code = ActionTypes.CREATE, descriptions = "메뉴 트리 생성/수정")
    @PreAuthorize("@security.hasPermission({'HOSPITAL_WRITE'})")
    <S extends T> S save(S entity);

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_WRITE'})")
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    Optional<T> findById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    boolean existsById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    Set<T> findAll();

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    Set<T> findAllById(Iterable<Long> ids);

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    long count();

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_DELETE'})")
    void deleteById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_DELETE'})")
    void delete(T entity);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'HOSPITAL_DELETE'})")
    void deleteAll(Iterable<? extends T> entities);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'HOSPITAL_DELETE'})")
    void deleteAll();
}
