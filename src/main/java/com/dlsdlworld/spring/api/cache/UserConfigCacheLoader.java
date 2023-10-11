package com.dlsdlworld.spring.api.cache;

import com.dlsdlworld.spring.api.event.UserConfigListener;
import com.dlsdlworld.spring.api.model.UserConfig;
import com.dlsdlworld.spring.api.repository.UserConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/** 
 * 사용자 환경설정 전체 리스너 코드 리로딩
 */
@Slf4j
@Service
public class UserConfigCacheLoader {

	private final UserConfigRepository userConfigRepository;

	private final UserConfigListener userConfigListener;

	@Autowired
	public UserConfigCacheLoader(
			UserConfigRepository userConfigRepository,
			UserConfigListener userConfigListener) {
		this.userConfigRepository = userConfigRepository;
		this.userConfigListener = userConfigListener;
	}

	/**
	 * 메시지캐시 리로딩
	 */
	public void load() {


	  int delCnt =	userConfigListener.deleteCaches();
		log.info("## UserConfig all deleted Count:{}", delCnt);

		Set<UserConfig> configMaps = userConfigRepository.findAll();

		log.info("## UserConfig all Maps:{}", configMaps.size());
		userConfigListener.saveCache(configMaps);

		/*for (UserConfig siteMap : configMaps) {
			userConfigListener.saveCache(siteMap);
		}*/
	}


}
