package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.model.BaseBoard;
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
public interface BaseBoardRepository<T extends BaseBoard> extends PagingAndSortingRepository<T, Long> {

    @Override
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    Set<T> findAll(Sort sort);

    @Override
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    Page<T> findAll(Pageable pageable);

    @Override
    @LogAdminExecution(code = ActionTypes.CREATE, descriptions = "게시판 등록 및 수정")
    @PreAuthorize("@security.hasPermission({'API_WRITE'})")
    <S extends T> S save(S entity);

    @Override
    @PreAuthorize("@security.hasPermission({'API_WRITE'})")
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    Optional<T> findById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    boolean existsById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    Set<T> findAll();

    @Override
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    Set<T> findAllById(Iterable<Long> ids);

    @Override
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    long count();

    @Override
    @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "게시판 삭제")
    @PreAuthorize("@security.hasPermission({'API_DELETE'})")
    void deleteById(Long id);

    @Override
    @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "게시판 삭제")
    @PreAuthorize("@security.hasPermission({'API_DELETE'})")
    void delete(T entity);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'API_DELETE'})")
    void deleteAll(Iterable<? extends T> entities);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'API_DELETE'})")
    void deleteAll();
}
