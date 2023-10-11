package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 12:53 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospitalOptionCode extends BasePersistable {
     /**
     * 재정의이미지주소코드
     */
    @Column(length = Columns.code, nullable = true)
    private String optCd;

    /**
     * 재정의메뉴명코드
     */
    @Column(length = Columns.refVal, nullable = true)
    private String optVal;


}
