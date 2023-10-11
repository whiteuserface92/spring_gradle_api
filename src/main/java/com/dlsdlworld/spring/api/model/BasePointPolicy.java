package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BasePointPolicy extends BaseModifiable {

	/**
	 * 정책 종류
	 */
	@Column(length = Columns.policyType, nullable = false)
	private String policyType;

	/**
	 * 정책명
	 */
	@Column(length = Columns.policyNm, nullable = false)
	private String policyNm;

	/**
	 * 시작일시
	 */
	@Column(nullable = false)
	private LocalDateTime startedOn;

	/**
	 * 종료일시
	 */
	@Column(nullable = false)
	private LocalDateTime endedOn;

	/**
	 * 정책설명
	 */
	@Column(length = Columns.memo, nullable = false)
	private String memo;

	/**
	 * 하한값
	 */
	@Column(nullable = false)
	private Integer minVal;

	/**
	 * 상한값
	 */
	@Column(nullable = false)
	private Integer maxVal;

}
