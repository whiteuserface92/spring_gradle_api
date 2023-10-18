package com.dlsdlworld.spring.api.cacherepository;

import com.dlsdlworld.spring.api.cachemodel.AppLogCache;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface AppLogCacheRepository extends PagingAndSortingRepository<AppLogCache, Long> {

}
