package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.FidoSet;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/13
 * Time : 16:51
 */
public interface FidoSetRepository extends BaseFidoSetRepository<FidoSet> {

    FidoSet findByTenantCd(String tenantCd);

}