package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.IpAccess;

import java.util.Set;

/**
 */
public interface IpAccessRepository extends BaseIpAccessRepository<IpAccess> {

    Set<IpAccess> findAllByUserId(Long userId);
}
