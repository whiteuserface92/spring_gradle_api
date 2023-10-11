package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * 사용자어드민관리
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : hskim
 * Date : 2020/08/11
 * Time : 2:28 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospitalAdminMap extends BaseModifiable {
}
