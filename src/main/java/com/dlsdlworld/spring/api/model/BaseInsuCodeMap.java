package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * 병원보험사코드매핑
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/30
 * Time : 8:37 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuCodeMap extends BaseModifiable {

}
