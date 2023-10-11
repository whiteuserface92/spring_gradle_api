package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.UserAuthPwd;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : suyeon.you
 * Date : 2020-08-19
 * Time : 오후 6:23
 */
@RepositoryRestResource
public interface UserAuthPwdRepository extends PagingAndSortingRepository<UserAuthPwd, Long> {

    Set<UserAuthPwd> findByUserIdAndUserAccntIsNotNull(Long userId);
}
