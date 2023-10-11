package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.FidoSet;

/**
 */
public interface FidoSetRepository extends BaseFidoSetRepository<FidoSet> {

    FidoSet findByTenantCd(String tenantCd);

}