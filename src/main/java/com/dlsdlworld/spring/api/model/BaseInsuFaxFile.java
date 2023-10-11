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
public abstract class BaseInsuFaxFile extends BaseCreatable {

	/**
	 * 파일식별키
	 */
	@Column(length = Columns.fileKey, nullable = false)
	private String fileKey;

	/**
	 * 파일이름
	 */
	@Column(length = Columns.fileNm, nullable = false)
	private String fileNm;

	/**
	 * 파일크기
	 */
	@Column(nullable = false)
	private Long fileSize;

	/**
	 * 파일유형
	 */
	@Column(length = Columns.fileType, nullable = false)
	private String fileType;

	/**
	 * 암호화방법
	 */
	@Column(length = Columns.fileEnc, nullable = false)
	private String fileEnc;

	/**
	 * 파일원본
	 */
	@Column(nullable = false)
	private byte[] fileBinary;
}
