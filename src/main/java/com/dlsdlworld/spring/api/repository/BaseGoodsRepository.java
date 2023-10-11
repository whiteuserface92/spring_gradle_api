package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.BaseGoods;
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
public interface BaseGoodsRepository<T extends BaseGoods> extends PagingAndSortingRepository<T, Long> {

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_READ'})")
    Set<T> findAll(Sort sort);

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_READ'})")
    Page<T> findAll(Pageable pageable);

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_WRITE'})")
    <S extends T> S save(S entity);

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_WRITE'})")
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_DELETE'})")
    Optional<T> findById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_READ'})")
    boolean existsById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_READ'})")
    Set<T> findAll();

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_READ'})")
    Set<T> findAllById(Iterable<Long> ids);

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_READ'})")
    long count();

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_DELETE'})")
    void deleteById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'GOODS_DELETE'})")
    void delete(T entity);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'GOODS_DELETE'})")
    void deleteAll(Iterable<? extends T> entities);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'GOODS_DELETE'})")
    void deleteAll();
}
