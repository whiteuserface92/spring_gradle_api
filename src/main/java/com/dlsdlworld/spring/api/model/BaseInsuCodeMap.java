package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * 병원보험사코드매핑
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuCodeMap extends BaseModifiable {

}
