package com.dlsdlworld.spring.api.cache;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 공통코드 캐시 저장소
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/29
 * Time : 14:57
 */
@Repository
public interface CommonCodeCacheRepository extends PagingAndSortingRepository<CommonCodeCache, Long> {

    List<CommonCodeCache> findByCodeCls(String codeCls);
}
