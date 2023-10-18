package com.dlsdlworld.spring.api.baserepository;//package com.dlsdlworld.spring.api.repository;
//
//import com.dlsdlworld.spring.api.basemodel.BaseCertDocVerify;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.data.rest.core.annotation.RestResource;
//import org.springframework.security.access.prepost.PreAuthorize;
//
//import java.util.Optional;
//import java.util.Set;
//
///**
// */
//@RepositoryRestResource
//public interface BaseCertDocVerifyRepository<T extends BaseCertDocVerify> extends PagingAndSortingRepository<T, String> {
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_READ'})")
//    Set<T> findAll(Sort sort);
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_READ'})")
//    Page<T> findAll(Pageable pageable);
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_WRITE'})")
//    <S extends T> S save(S entity);
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_WRITE'})")
//    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_DELETE'})")
//    Optional<T> findById(String id);
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_READ'})")
//    boolean existsById(String id);
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_READ'})")
//    Set<T> findAll();
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_READ'})")
//    Set<T> findAllById(Iterable<String> ids);
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_READ'})")
//    long count();
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_DELETE'})")
//    void deleteById(String id);
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'CERT_DELETE'})")
//    void delete(T entity);
//
//    @Override
//    @RestResource(exported = false)
//    @PreAuthorize("@security.hasPermission({'CERT_DELETE'})")
//    void deleteAll(Iterable<? extends T> entities);
//
//    @Override
//    @RestResource(exported = false)
//    @PreAuthorize("@security.hasPermission({'CERT_DELETE'})")
//    void deleteAll();
//}
