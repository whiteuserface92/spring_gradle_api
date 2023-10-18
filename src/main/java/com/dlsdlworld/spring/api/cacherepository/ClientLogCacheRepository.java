package com.dlsdlworld.spring.api.cacherepository;

import com.dlsdlworld.spring.api.cachemodel.ClientLogCache;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface ClientLogCacheRepository extends PagingAndSortingRepository<ClientLogCache, Long> {

}
