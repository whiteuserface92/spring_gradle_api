package com.dlsdlworld.spring.api.repository;


import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.model.BaseTerms;
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
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/11
 * Time : 20:15
 */
@RepositoryRestResource
public interface BaseTermsRepository<T extends BaseTerms> extends PagingAndSortingRepository<T, Long> {


    @Override
    @PreAuthorize("@security.hasPermission({'TERMS_READ'})")
    Set<T> findAll(Sort sort);

    @Override
    @PreAuthorize("@security.hasPermission({'TERMS_READ'})")
    Page<T> findAll(Pageable pageable);

    @Override
    @LogAdminExecution(code = ActionTypes.CREATE, descriptions = "약관 등록 및 수정")
    @PreAuthorize("@security.hasPermission({'TERMS_WRITE'})")
    <S extends T> S save(S entity);

    @Override
    @PreAuthorize("@security.hasPermission({'TERMS_WRITE'})")
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @PreAuthorize("@security.hasPermission({'TERMS_READ'})")
    Optional<T> findById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'TERMS_READ'})")
    boolean existsById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'TERMS_READ'})")
    Set<T> findAll();

    @Override
    @PreAuthorize("@security.hasPermission({'TERMS_READ'})")
    Set<T> findAllById(Iterable<Long> ids);

    @Override
    @PreAuthorize("@security.hasPermission({'TERMS_READ'})")
    long count();

    @Override
    @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "약관 삭제")
    @PreAuthorize("@security.hasPermission({'TERMS_DELETE'})")
    void deleteById(Long id);

    @Override
    @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "약관 삭제")
    @PreAuthorize("@security.hasPermission({'TERMS_DELETE'})")
    void delete(T entity);

    @Override
    @RestResource(exported = false)
    @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "약관 삭제")
    @PreAuthorize("@security.hasPermission({'TERMS_DELETE'})")
    void deleteAll(Iterable<? extends T> entities);

    @Override
    @RestResource(exported = false)
    @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "약관 삭제")
    @PreAuthorize("@security.hasPermission({'TERMS_DELETE'})")
    void deleteAll();
}