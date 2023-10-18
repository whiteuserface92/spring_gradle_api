package com.dlsdlworld.spring.api.baserepository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.basemodel.BaseMessage;
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
public interface BaseMessageRepository<T extends BaseMessage> extends PagingAndSortingRepository<T, Long> {

    @PreAuthorize("@security.hasPermission({'MESSAGE_READ'})")
    Set<T> findByLangCdAndMsgCdStartsWith(String langCd, String msgCd);

    @PreAuthorize("@security.hasPermission({'MESSAGE_READ'})")
    Set<T> findByMsgCd(String msgCd);

    @PreAuthorize("@security.hasPermission({'MESSAGE_READ'})")
    Set<T> findByMsgContentContains(String msgContent);

    @Override
    @PreAuthorize("@security.hasPermission({'MESSAGE_READ'})")
    Set<T> findAll(Sort sort);

    @Override
    @PreAuthorize("@security.hasPermission({'MESSAGE_READ'})")
    Page<T> findAll(Pageable pageable);

    @Override
    @LogAdminExecution(code = ActionTypes.CREATE, descriptions = "다국어 생성/수정")
    @PreAuthorize("@security.hasPermission({'MESSAGE_WRITE'})")
    <S extends T> S save(S entity);

    @Override
    @PreAuthorize("@security.hasPermission({'MESSAGE_WRITE'})")
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @PreAuthorize("@security.hasPermission({'MESSAGE_READ'})")
    Optional<T> findById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'MESSAGE_READ'})")
    boolean existsById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'MESSAGE_READ'})")
    Set<T> findAll();

    @Override
    @PreAuthorize("@security.hasPermission({'MESSAGE_READ'})")
    Set<T> findAllById(Iterable<Long> ids);

    @Override
    @PreAuthorize("@security.hasPermission({'MESSAGE_READ'})")
    long count();

    @Override
    @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "다국어 삭제")
    @PreAuthorize("@security.hasPermission({'MESSAGE_DELETE'})")
    void deleteById(Long id);

    @Override
    @PreAuthorize("@security.hasPermission({'MESSAGE_DELETE'})")
    void delete(T entity);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'MESSAGE_DELETE'})")
    void deleteAll(Iterable<? extends T> entities);

    @Override
    @RestResource(exported = false)
    @PreAuthorize("@security.hasPermission({'MESSAGE_DELETE'})")
    void deleteAll();
}
