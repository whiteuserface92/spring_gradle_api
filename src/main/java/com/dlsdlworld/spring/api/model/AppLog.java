package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AppLog)
public class AppLog extends BaseAppLog {

	/**
	 * 사용자
	 */
	@Column(name = "user_id")
	private Long userId;
/*  로그 남길경우에는 user_id가 0 매핑 되지 않은 사용자도 입력되어야함
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;*/
}
