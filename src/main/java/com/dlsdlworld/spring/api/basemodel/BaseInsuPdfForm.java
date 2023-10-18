//package com.dlsdlworld.spring.api.model;
//
//import com.dlsdlworld.spring.api.types.DomainTypes;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.MappedSuperclass;
//
///**
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseInsuPdfForm extends BaseModifiable {
//
//	/**
//	 * 기관구분
//	 */
//	@Enumerated(EnumType.STRING)
//	@Column(length = Columns.compType, nullable = false)
//	private DomainTypes compType;
//
//	/**
//	 * 기관id
//	 */
//	@Column(nullable = false)
//	private Long compId;
//
//	/**
//	 * 서식명
//	 */
//	@Column(length = Columns.formNm, nullable = false)
//	private String formNm;
//
//	/**
//	 * 파일경로
//	 */
//	@Column(length = Columns.pdfFileUrl, nullable = false)
//	private String fileUrl;
//
//
//	/**
//	 * 파일원본
//	 */
//	private byte[] fileBinary;
//
//	/**
//	 * pdf 가로모드 확인
//	 */
//	@Column(nullable = false)
//	private Boolean portraitEnabled;
//
//	/**
//	 * pdf form 별 출력 타입을 정한다
//	 */
//	@Column(length = Columns.useCd, nullable = false)
//	private String outputColType;
//}
