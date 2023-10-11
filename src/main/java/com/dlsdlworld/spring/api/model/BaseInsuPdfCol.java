package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/04/07
 * Time : 8:59 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuPdfCol extends BaseModifiable {

	/**
	 * 페이지번호
	 */
	@Column(nullable = false)
	private Integer pageNo;

	/**
	 * 필드순번
	 */
	@Column(nullable = false)
	private Integer fieldNo;

	/**
	 * 필드제목
	 */
	@Column(nullable = false, length = Columns.fieldTitle)
	private String fieldTitle;

	/**
	 * 필드값
	 */
	@Column(nullable = false, length = Columns.fieldValue)
	private String fieldValue;

	/**
	 * 필드타입
	 */
	@Column(nullable = false, length = Columns.fieldType)
	private String fieldType;

	/**
	 * 필드선택값
	 */
	@Column(length = Columns.optionValue)
	private String optionValue;

	/**
	 * 자동작성여부
	 */
	@Column(nullable = false)
	private Boolean autoMarkingEnabled;

	/**
	 * X좌표
	 */
	@Column(name = "start_x", nullable = false)
	private Double startX;

	/**
	 * Y좌표
	 */
	@Column(name = "start_y", nullable = false)
	private Double startY;

	/**
	 * 필드넓이
	 */
	@Column(nullable = false)
	private Double fieldWidth;

	/**
	 * 폰트사이즈
	 */
	@Column(nullable = false)
	private Double fontSize;

	/**
	 * 폰트간격
	 */
	@Column(nullable = false)
	private Double fontInterval;
}
