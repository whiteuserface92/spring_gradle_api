package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 병원코드정의
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/30
 * Time : 8:37 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuCode extends BasePersistable {

    /**
     * 보험사코드
     */
    @Column(length = Columns.insuCompCd, nullable = false)
    private String insuCompCd;

    /**
     * 진단과목코드: 03, 진료비항목코드: 05, 추가예정,,,,,,
     */
    @Column(length = Columns.mapType, nullable = false)
    private String mapType;

    /**
     * 보험사에서 제공하는  코드
     */
    @Column(length = Columns.useCd, nullable = false)
    private String useCd;

    /**
     * 보험사에서 제공하는  코드명
     */
    @Column(length = Columns.useNm, nullable = false)
    private String useNm;

    /**
     * 예비항목(NH보험 추가코드로 현재사용중)
     */
    @Column(length = Columns.etcVal)
    private String etcVal;
}
