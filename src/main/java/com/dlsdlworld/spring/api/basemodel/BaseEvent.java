//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//import java.time.LocalDateTime;
//
///**
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseEvent extends BaseModifiable {
//
//	/**
//	 * 이벤트 종류
//	 */
//	@Column(length = Columns.eventType, nullable = false)
//	private String eventType;
//
//	/**
//	 * 이벤트명
//	 */
//	@Column(length = Columns.eventNm, nullable = false)
//	private String eventNm;
//
//	/**
//	 * 시작일시
//	 */
//	@Column(nullable = false)
//	private LocalDateTime startedOn;
//
//	/**
//	 * 종료일시
//	 */
//	@Column(nullable = false)
//	private LocalDateTime endedOn;
//
//	/**
//	 * 유효일수
//	 */
//	@Column(nullable = false)
//	private Short validDays;
//
//	/**
//	 * 이벤트 설명
//	 */
//	@Column(length = Columns.eventDesc, nullable = false)
//	private String eventDesc;
//
//	/**
//	 * 적립 방법
//	 */
//	@Column(length = Columns.saveType, nullable = false)
//	private String saveType;
//
//	/**
//	 * 적립값
//	 */
//	@Column(nullable = false)
//	private Integer saveVal;
//
//
//	/**
//	 * 메모
//	 */
//	@Column(length = Columns.memo, nullable = false)
//	private String memo;
//
//
//}
