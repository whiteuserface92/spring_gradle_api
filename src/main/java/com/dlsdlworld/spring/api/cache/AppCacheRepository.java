package com.dlsdlworld.spring.api.cache;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface AppCacheRepository extends PagingAndSortingRepository<AppCache, Long> {

    AppCache findByPkgNmAndDeployTypeAndPlatformType(String pkgNm, String deployType, String platformType);

    Page<AppCache> findAllByHospitalId(Long hospitalId, Pageable page);

    Page<AppCache> findAllByAppNmOrPkgNm(String appNm, String pkgNm, Pageable var1);
    Page<AppCache> findAllByAppNmOrPkgNmAndHospitalId(String appNm, String pkgNm, Long hospitalId, Pageable var1);
}