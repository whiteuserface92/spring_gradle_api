package com.dlsdlworld.spring.api.baserepository;//package com.dlsdlworld.spring.api.repository;
//
//import com.dlsdlworld.spring.api.aop.LogAdminExecution;
//import com.dlsdlworld.spring.api.basemodel.BaseNotice;
//import com.dlsdlworld.spring.api.types.ActionTypes;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.data.rest.core.annotation.RestResource;
//
//import java.util.Optional;
//import java.util.Set;
//
///**
// */
//@RepositoryRestResource
//public interface BaseNoticeRepository<T extends BaseNotice> extends PagingAndSortingRepository<T, Long> {
//
//    @Override
////    @PreAuthorize("@security.hasPermission({'API_READ'})")
//    Set<T> findAll(Sort sort);
//
//    @Override
////    @PreAuthorize("@security.hasPermission({'API_READ'})")
//    Page<T> findAll(Pageable pageable);
//
//    @Override
////    @PreAuthorize("@security.hasPermission({'API_WRITE'})")
//    @LogAdminExecution(code = ActionTypes.CREATE, descriptions = "공지사항 등록 및 수정")
//    <S extends T> S save(S entity);
//
//    @Override
////    @PreAuthorize("@security.hasPermission({'API_WRITE'})")
//    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
//
//    @Override
////    @PreAuthorize("@security.hasPermission({'API_READ'})")
//    Optional<T> findById(Long id);
//
//    @Override
////    @PreAuthorize("@security.hasPermission({'API_READ'})")
//    boolean existsById(Long id);
//
//    @Override
////    @PreAuthorize("@security.hasPermission({'API_READ'})")
//    Set<T> findAll();
//
//    @Override
////    @PreAuthorize("@security.hasPermission({'API_READ'})")
//    Set<T> findAllById(Iterable<Long> ids);
//
//    @Override
////    @PreAuthorize("@security.hasPermission({'API_READ'})")
//    long count();
//
//    @Override
//    @LogAdminExecution(code = ActionTypes.DELETE, descriptions = "공지사항 삭제")
////    @PreAuthorize("@security.hasPermission({'API_DELETE'})")
//    void deleteById(Long id);
//
//    @Override
////    @PreAuthorize("@security.hasPermission({'API_DELETE'})")
//    void delete(T entity);
//
//    @Override
//    @RestResource(exported = false)
////    @PreAuthorize("@security.hasPermission({'API_DELETE'})")
//    void deleteAll(Iterable<? extends T> entities);
//
//    @Override
//    @RestResource(exported = false)
////    @PreAuthorize("@security.hasPermission({'API_DELETE'})")
//    void deleteAll();
//}
