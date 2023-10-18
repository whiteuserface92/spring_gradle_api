package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.projection.AppInfoProjection;
import com.dlsdlworld.spring.api.repository.AppPlatformRepository;
import com.dlsdlworld.spring.api.types.AppDeploymentTypes;
import com.dlsdlworld.spring.api.types.PlatformTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;


@Slf4j
@Validated
@RestController
@RequestMapping("/rest")
public class AppPlatformController {

	private final AppPlatformRepository appPlatformRepository;

	@Autowired
	public AppPlatformController(AppPlatformRepository appPlatformRepository) {
		this.appPlatformRepository = appPlatformRepository;
	}

	/**
	 * 앱 설치후 앱에서 요청하는 기본 정보
	 * @param pkgNm
	 * @param deployType
	 * @param platformType
	 * @return
	 */
	@GetMapping(value = "/appPlatforms/search/findAppInfo")
	public AppInfoProjection updApiParam(@RequestParam @NotEmpty String pkgNm,
										 @RequestParam AppDeploymentTypes deployType,
										 @RequestParam PlatformTypes platformType) {
		AppInfoProjection appInfoProjection = appPlatformRepository.findByPkgNmAndDeployTypeAndPlatformType(pkgNm, deployType, platformType);
		log.trace("appInfoProjection:{}", appInfoProjection);

		return appInfoProjection;
	}
}
