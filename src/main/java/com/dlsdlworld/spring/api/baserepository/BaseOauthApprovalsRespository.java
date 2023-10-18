package com.dlsdlworld.spring.api.baserepository;//package com.dlsdlworld.spring.api.repository;
//
//
//import com.dlsdlworld.spring.api.basemodel.BaseOauthApprovals;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.security.access.prepost.PreAuthorize;
//
//import java.util.List;
//
///**
// */
//@RepositoryRestResource
//public interface BaseOauthApprovalsRespository<T extends BaseOauthApprovals> extends CrudRepository<T, Integer> {
//
//    /**
//     *
//     * @param userId
//     * @param clientId
//     * @return
//     */
//    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
//    List<T> findAllByUserIdAndClientId(String userId, String clientId);
//
//    /**
//     *
//     * @param userId
//     * @param clientId
//     * @param scope
//     */
//    @PreAuthorize("@security.hasPermission({'AUTHORITY_DELETE'})")
//    void deleteByUserIdAndClientIdAndScope(String userId, String clientId, String scope);
//}
