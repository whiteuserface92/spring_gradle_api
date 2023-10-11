package com.dlsdlworld.spring.api.cache;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/04/02
 * Time : 2:13 오후
 */
@Data
@NoArgsConstructor
@RedisHash("CommonCodeCache")
public class CommonCodeCache {

	@Id
	private Long id;

	/** 코드*/
	private String code;

	/** 분류코드*/
	@Indexed
	private String codeCls;

	/** 코드타입*/
	private String codeType;

	/** 코드명*/
	private String codeNm;

	/** 코드영문명*/
	private String codeNmEng;

	/** 표시순서*/
	private String dispOrd;

	/** 참조값*/
	private String refVal;

	/** 코드설명*/
	private String codeDesc;

	@Builder
	public CommonCodeCache(Long id,
	                       String code,
	                       String codeCls,
	                       String codeType,
	                       String codeNm,
	                       String codeNmEng,
	                       String dispOrd,
	                       String refVal,
	                       String codeDesc) {
		this.id = id;
		this.code = code;
		this.codeCls = codeCls;
		this.codeType = codeType;
		this.codeNm = codeNm;
		this.codeNmEng = codeNmEng;
		this.dispOrd = dispOrd;
		this.refVal = refVal;
		this.codeDesc = codeDesc;
	}
}
