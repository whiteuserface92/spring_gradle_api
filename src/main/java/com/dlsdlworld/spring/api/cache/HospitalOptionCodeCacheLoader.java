package com.dlsdlworld.spring.api.cache;

import com.dlsdlworld.spring.api.event.HospitalOptionCodeListener;
import com.dlsdlworld.spring.api.model.HospitalOptionCode;
import com.dlsdlworld.spring.api.repository.HospitalOptionCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/** 
 * 병원 옵션코드 리로딩
 * */
@Slf4j
@Service
public class HospitalOptionCodeCacheLoader {

	private final HospitalOptionCodeRepository hospitalOptionCodeRepository;

	private final HospitalOptionCodeListener hospitalOptionCodeListener;

	@Autowired
	public HospitalOptionCodeCacheLoader(
			HospitalOptionCodeRepository hospitalOptionCodeRepository,
			HospitalOptionCodeListener hospitalOptionCodeListener) {
		this.hospitalOptionCodeRepository = hospitalOptionCodeRepository;
		this.hospitalOptionCodeListener = hospitalOptionCodeListener;
	}

	/**
	 * 메시지캐시 리로딩
	 */
	public void load() {
	  int delCnt =	hospitalOptionCodeListener.deleteCaches();
		log.info("## hospital Option deleted Count:{}", delCnt);

 		Set<HospitalOptionCode> optCds = hospitalOptionCodeRepository.findAll();
		log.info("## hospital Option Codes:{}", optCds.size());
		for (HospitalOptionCode optCd : optCds) {
			hospitalOptionCodeListener.saveCache(optCd);
		}
	}


}
