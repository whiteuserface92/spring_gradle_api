package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
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
