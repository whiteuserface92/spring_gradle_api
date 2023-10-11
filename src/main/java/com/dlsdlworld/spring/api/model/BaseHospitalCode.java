package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/30
 * Time : 8:37 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospitalCode extends BasePersistable {

    /**
     * 병원코드
     */
    @Column(length = Columns.hospitalCd, nullable = false)
    private String hospitalCd;

    /**
     * 진단과목코드: 03, 진료비항목코드: 05, 추가예정,,,,,,
     */
    @Column(length = Columns.mapType, nullable = false)
    private String mapType;

    /**
     * 병원사용코드
     */
    @Column(length = Columns.useCd, nullable = false)
    private String useCd;

    /**
     * 병원사용코드명
     */
    @Column(length = Columns.useNm, nullable = false)
    private String useNm;
}
