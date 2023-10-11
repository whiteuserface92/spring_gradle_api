package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
 * API 정의
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/11
 * Time : 20:15
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCompServerSet extends BaseCreatable {

    /**
     * 기관구분
     */
    @Column(length = Columns.compType, nullable = false)
    private String compType;

	/**
	 * 서버구분
     */
    @Column(nullable = false, length = Columns.serverType)
    private String serverType;

	/**
	 * 서버ip
	 */
	@Column(nullable = false, length = Columns.serviceIp)
    private String serverIp;

	/**
	 * 서버포트
	 */
	@Column(nullable = false)
	private Integer serverPort;

	/**
	 * 메모
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String memo;
}

