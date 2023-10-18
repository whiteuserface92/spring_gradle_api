package com.dlsdlworld.spring.api.baserepository;

import com.dlsdlworld.spring.api.basemodel.BaseOauthClientDetails;
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
public interface BaseOauthClientDetailRepository<T extends BaseOauthClientDetails> extends PagingAndSortingRepository<T, Long> {

    /**
     *
     * @param clientId
     * @return
     */
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    Long countByClientId(String clientId);

    /**
     *
     * @param clientId
     * @return
     */
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    T findByClientId(String clientId);

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    Set<T> findAll(Sort sort);

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    Page<T> findAll(Pageable pageable);

    @Override
    @PreAuthorize("@security.hasPermission({'AUTHORITY_WRITE'})")
    <S extends T> S save(S entity);

    @Override
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
