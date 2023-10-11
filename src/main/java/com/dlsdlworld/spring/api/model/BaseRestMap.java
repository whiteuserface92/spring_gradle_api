package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseRestMap extends BaseModifiable {

	/**
	 * 화면에서 호출하는 rest url
	 */
	@Column(length = Columns.serviceUrl)
	private String srcUrl;

}
