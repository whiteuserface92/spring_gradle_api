package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.AppVersion;
import com.dlsdlworld.spring.api.projection.AppVersionProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : hskim
 * Date : 2020/07/06
 * Time : 18:46
 */

@RepositoryRestResource(excerptProjection = AppVersionProjection.class)
public interface AppVersionRepository extends BaseAppVersionRepository<AppVersion> {

    // Collection<AppVersion> findAllByAppPlatformId(Long appPlatformId);

    AppVersion findTopByAppPlatformIdOrderByVersionCdDesc(Long appPlatformId);

    Page<AppVersion> findAllByAppPlatformId(Pageable page, Long appPlatformId);

}


