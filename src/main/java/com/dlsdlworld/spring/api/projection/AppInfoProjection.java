package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.App;
import com.dlsdlworld.spring.api.model.AppPlatform;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.rest.core.config.Projection;

/**

 */
@Projection(name = "appInfo_projection", types = {AppPlatform.class, App.class})
public interface AppInfoProjection {

	/**
	 * 해시코드
	 * @return
	 */
	String getHashKey();

	/**
	 * 스토어주소
	 * @return
	 */
	String getStoreUrl();

	/**
	 * 앱식별자
	 * @return
	 */
	default Long getAppId() {
		return getApp().getId();
	}

	/**
	 * 앱버전
	 * @return
	 */
	default String getAppVer() {
		return getApp().getAppVer();
	}

	/**
	 * json으로 변환하지 않는다.
	 * @return
	 */
	@JsonIgnore
	App getApp();
}
