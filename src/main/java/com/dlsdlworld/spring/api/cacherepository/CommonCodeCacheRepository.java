package com.dlsdlworld.spring.api.cacherepository;

import com.dlsdlworld.spring.api.cachemodel.CommonCodeCache;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 공통코드 캐시 저장소
 */
@Repository
public interface CommonCodeCacheRepository extends PagingAndSortingRepository<CommonCodeCache, Long> {

    List<CommonCodeCache> findByCodeCls(String codeCls);
}
