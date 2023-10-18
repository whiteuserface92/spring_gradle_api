package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCertFile extends BaseModifiable {

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
	private Integer fileSize;

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

	/**
	 * 백업URI
	 */
	@Column(length = Columns.backupUri)
	private String backupUri;

	/**
	 * 백업종류
	 */
	@Column(length = Columns.backupType)
	private String backupType;


}
