package com.dlsdlworld.spring.api.cache;

import com.dlsdlworld.spring.api.event.BaseCommonCodeListener;
import com.dlsdlworld.spring.api.model.CommonCode;
import com.dlsdlworld.spring.api.repository.CommonCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 */
@Slf4j
@Service
public class CommonCodeCacheLoader {

	private final CommonCodeRepository commonCodeRepository;
	private final BaseCommonCodeListener baseCommonCodeListener;

	@Autowired
	public CommonCodeCacheLoader(
		CommonCodeRepository commonCodeRepository,
		BaseCommonCodeListener baseCommonCodeListener) {
		this.commonCodeRepository = commonCodeRepository;
		this.baseCommonCodeListener = baseCommonCodeListener;
	}

	/**
	 *
	 */
	public void load() {
		Set<CommonCode> codes = commonCodeRepository.findAll();
		log.info("## codes:{}", codes.size());
		for (CommonCode code: codes) {
			baseCommonCodeListener.saveCache(code);
		}
	}


	/**
	 *
	 */
	public void load(String codeCls) {
		Set<CommonCode> codes = Optional.ofNullable( commonCodeRepository.findByCodeCls(codeCls)).orElseGet(()->new HashSet<CommonCode>());
		baseCommonCodeListener.deleteCacheByCodeCls(codeCls);
		log.info("## codes:{}", codes.size());
		for (CommonCode code: codes) {
			baseCommonCodeListener.saveCache(code);
		}
	}
}
