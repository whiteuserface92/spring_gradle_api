package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.DataTypes;
import com.dlsdlworld.spring.api.types.DomainTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseDomainMapping extends BaseCreatable {

	/**
	 * 기관구분(병원:hospital, 보험사:insurance) 값을 사용
	 * 당장은 보험사만 사용하겠지만, 타기관과의 매핑에도 활용하기 위해 구분을 두어 구별한다.
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = Columns.compType, nullable = false)
	private DomainTypes compType;

	/**
	 * 병원의 경우 병원id(hospital_mst.id), 보험사일 경우 insu_mst.id 값을 지정
	 */
	@Column(nullable = false)
	public Long compId;

	/**
	 * 매핑변수명
	 */
	@Column(nullable = false, length = Columns.mapParamNm)
	public String mapParamNm;

	/**
	 * 매핑데이터유형, 도메인에 정의된 데이터유형을 바꿔서 사용할 경우 쓸 수 있도록 이 컬럼을 구성함
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = Columns.mapDataType)
	private DataTypes mapDataType;

}
