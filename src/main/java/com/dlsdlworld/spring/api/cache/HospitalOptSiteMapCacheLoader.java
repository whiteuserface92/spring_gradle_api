package com.dlsdlworld.spring.api.cache;

import com.dlsdlworld.spring.api.event.HospitalOptSiteMapListener;
import com.dlsdlworld.spring.api.model.HospitalOptSiteMap;
import com.dlsdlworld.spring.api.repository.HospitalOptSiteMapRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/** 
 * 병원 사이트옵션매핑 코드 리로딩
 */
@Slf4j
@Service
public class HospitalOptSiteMapCacheLoader {

	private final HospitalOptSiteMapRepository hospitalOptSiteMapRepository;

	private final HospitalOptSiteMapListener hospitalOptSiteMapListener;

	@Autowired
	public HospitalOptSiteMapCacheLoader(
			HospitalOptSiteMapRepository hospitalOptSiteMapRepository,
			HospitalOptSiteMapListener hospitalOptSiteMapListener) {
		this.hospitalOptSiteMapRepository = hospitalOptSiteMapRepository;
		this.hospitalOptSiteMapListener = hospitalOptSiteMapListener;
	}

	/**
	 * 메시지캐시 리로딩
	 */
	public void load() {
	  int delCnt =	hospitalOptSiteMapListener.deleteCaches();
		log.info("## hospital Opt Site Map deleted Count:{}", delCnt);

 		Set<HospitalOptSiteMap> siteMaps = hospitalOptSiteMapRepository.findAll();
		log.info("## hospital Opt Site Maps:{}", siteMaps.size());
		for (HospitalOptSiteMap siteMap : siteMaps) {
			hospitalOptSiteMapListener.saveCache(siteMap);
		}
	}


}
