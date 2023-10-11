package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.UserAuthPwd;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 */
@RepositoryRestResource
public interface UserAuthPwdRepository extends PagingAndSortingRepository<UserAuthPwd, Long> {

    Set<UserAuthPwd> findByUserIdAndUserAccntIsNotNull(Long userId);
}
