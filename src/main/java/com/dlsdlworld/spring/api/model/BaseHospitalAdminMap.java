package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * 사용자어드민관리
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospitalAdminMap extends BaseModifiable {
}
