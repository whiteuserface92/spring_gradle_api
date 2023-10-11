package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.model.BaseHospital;
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
 */
@RepositoryRestResource
public interface BaseHospitalRepository<T extends BaseHospital> extends PagingAndSortingRepository<T, Long> {

    /**
     *
     * @param hospitalCd
     * @return
     */
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    T findByHospitalCd(String hospitalCd);

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    Set<T> findAll(Sort sort);

    @Override
    @PreAuthorize("@security.hasPermission({'HOSPITAL_READ'})")
    Page<T> findAll(Pageable pageable);

    @Override
    @LogAdminExecution(code = ActionTypes.CREATE, descriptions = "병원 정보 등록 및 수정")
    @PreAuthorize("@security.hasPermission({'HOSPITAL_WRITE'})")
    <S extends T> S save(S entity);

    @Override
    @RestResource(exported = false)
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
    @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "병원 정보 삭제")
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
