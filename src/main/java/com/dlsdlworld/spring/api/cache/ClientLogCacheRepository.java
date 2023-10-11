package com.dlsdlworld.spring.api.cache;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface ClientLogCacheRepository extends PagingAndSortingRepository<ClientLogCache, Long> {

}
