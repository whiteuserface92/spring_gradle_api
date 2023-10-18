package com.dlsdlworld.spring.api.baserepository;//package com.dlsdlworld.spring.api.repository;
//
//import com.dlsdlworld.spring.api.basemodel.BaseAuthTemp;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.security.access.prepost.PreAuthorize;
//
//import java.util.Optional;
//
//public interface BaseAuthTempRepository<T extends BaseAuthTemp> extends PagingAndSortingRepository<T, Long> {
//    @Override
//    <S extends T> S save(S entity);
//    @Override
//    @PreAuthorize("@security.hasPermission({'USER_READ'})")
//    Optional<T> findById(Long id);
//
//    @Override
//    @PreAuthorize("@security.hasPermission({'USER_DELETE'})")
//    void deleteById(Long id);
//
//
//}
