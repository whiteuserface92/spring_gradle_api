package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 사용자단말기
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 4:48 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserDevice extends BaseModifiable {

	/**
	 * 사용여부(2020.06.08 by 김태식)
	 * uuid가 앱을 설치할때마다 생성되어 중복 로그인 방지를 위해 추가함.
	 * 동일 사용자의 이전에 생성된 데이터는 false로 업데이트 한다.
	 */
	@Column(nullable = false)
	private Boolean enabled;
}
