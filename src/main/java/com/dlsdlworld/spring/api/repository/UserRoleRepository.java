package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.UserRole;

import java.util.Collection;

/**
 */
public interface UserRoleRepository extends BaseUserRoleRepository<UserRole>{

    Collection<UserRole> findAllByUserId(Long userId);

}