package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.model.BaseRolePrivilege;
import com.dlsdlworld.spring.api.types.ActionTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;
import java.util.Set;

/**
 */
@RepositoryRestResource
public interface BaseRolePrivilegeRepository<T extends BaseRolePrivilege> extends PagingAndSortingRepository<T, Long> {

    @Modifying
    @PreAuthorize("@security.hasPermission({'AUTHORITY_DELETE'})")
    @Query("delete from RolePrivilege rp where rp.role.id = :roleId")
    void deleteAllByRoleId(@Param("roleId") Long roleId);

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    Set<T> findAll(Sort sort);

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    Page<T> findAll(Pageable pageable);

    @Override
    @LogAdminExecution(code = ActionTypes.CREATE, descriptions = "권한 생성/수정")
    @PreAuthorize("@security.hasPermission({'AUTHORITY_WRITE'})")
    <S extends T> S save(S entity);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'AUTHORITY_WRITE'})")
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    Optional<T> findById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    boolean existsById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    Set<T> findAll();

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    Set<T> findAllById(Iterable<Long> ids);

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    long count();

    @Override
    @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "권한 삭제")
    @PreAuthorize("@security.hasPermission({'AUTHORITY_DELETE'})")
    void deleteById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_DELETE'})")
    void delete(T entity);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'AUTHORITY_DELETE'})")
    void deleteAll(Iterable<? extends T> entities);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'AUTHORITY_DELETE'})")
    void deleteAll();
}
