package com.dlsdlworld.spring.api.cache;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : suyeon.you
 * Date : 2020-08-11
 * Time : 오후 12:11
 */
@Repository
public interface ClientLogCacheRepository extends PagingAndSortingRepository<ClientLogCache, Long> {

}
