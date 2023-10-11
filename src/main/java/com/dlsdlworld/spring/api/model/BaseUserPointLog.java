package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : sungjin.park
 * Date : 2020/05/29
 * Time : 4:43 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserPointLog extends BaseModifiable {

	/**
	 * 사용적립구분
	 */
	@Column(length = Columns.useType, nullable = false)
	private String useType;

	/**
	 * 발생일시
	 */
	@Column(nullable = false)
	private LocalDateTime eventOn;

	/**
	 * 결제금액
	 */
	@Column
	private Integer payAmt;

	/**
	 * 적립율
	 */
	@Column
	private Short savePercent;

	/**
	 * 포인트값
	 */
	@Column
	private Integer point;

	/**
	 * 유효종료일
	 */
	@Column(nullable = false)
	private LocalDateTime endedOn;

	/**
	 * 메모
	 */
	@Column(length = Columns.memo, nullable = false)
	private String memo;

	/**
	 * 주문ID
	 */
	@Column
	private Long orderId;

	/**
	 * 게시물ID
	 */
	@Column
	private Long postId;


}
