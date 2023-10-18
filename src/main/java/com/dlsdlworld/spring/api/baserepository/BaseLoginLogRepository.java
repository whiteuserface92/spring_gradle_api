package com.dlsdlworld.spring.api.baserepository;

import com.dlsdlworld.spring.api.basemodel.BaseLoginLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;
import java.util.Set;

@RepositoryRestResource
public interface BaseLoginLogRepository<T extends BaseLoginLog> extends PagingAndSortingRepository<T, Long> {

    @Override
    Set<T> findAll(Sort sort);

    @Override
    Page<T> findAll(Pageable pageable);

    @Override
    <S extends T> S save(S entity);

    @Override
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<T> findById(Long id);

    @Override
    boolean existsById(Long id);

    @Override
    Set<T> findAll();

    @Override
    Set<T> findAllById(Iterable<Long> ids);

    @Override
    long count();

    @Override
    void deleteById(Long id);

    @Override
    void delete(T entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends T> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
