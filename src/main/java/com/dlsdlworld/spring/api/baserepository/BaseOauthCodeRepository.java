package com.dlsdlworld.spring.api.baserepository;

import com.dlsdlworld.spring.api.basemodel.BaseOauthCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 */
@RepositoryRestResource
public interface BaseOauthCodeRepository<T extends BaseOauthCode> extends CrudRepository<T, Integer> {

    @PreAuthorize("@security.hasPermission({'AUTHORITY_READ'})")
    T findByCode(String code);

}
