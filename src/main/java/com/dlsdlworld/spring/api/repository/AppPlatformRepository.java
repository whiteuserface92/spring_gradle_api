package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.model.AppPlatform;
import com.dlsdlworld.spring.api.projection.AppInfoProjection;
import com.dlsdlworld.spring.api.types.AppDeploymentTypes;
import com.dlsdlworld.spring.api.types.PlatformTypes;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/30
 * Time : 15:46
 */
public interface AppPlatformRepository extends BaseAppPlatformRepository<AppPlatform> {

	/**
	 * 패키지명, 배포타입, 플랫폼타입으로 조회
	 * @param pkgNm
	 * @param deployType
	 * @param platformType
	 * @return
	 */
	@PreAuthorize("@security.hasPermission({'APP_READ'})")
	AppInfoProjection findByPkgNmAndDeployTypeAndPlatformType(String pkgNm, AppDeploymentTypes deployType, PlatformTypes platformType);

	@PreAuthorize("@security.hasPermission({'APP_READ'})")
	Optional<AppPlatform> findByPkgNmAndPlatformType(String pkgNm, PlatformTypes platformType);

}
