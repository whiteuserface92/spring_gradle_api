package com.dlsdlworld.spring.api.cacherepository;

import com.dlsdlworld.spring.api.cachemodel.ProgDeployCache;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface ProgDeployCacheRepository extends PagingAndSortingRepository<ProgDeployCache, String> {

}
