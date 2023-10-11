package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 보험사영업일관리
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuCalendar implements Serializable {

    /**
     * 년월일
     */
    @Id
    @Column(length = Columns.yyyymmdd, nullable = false)
    private String yyyymmdd;

    /**
     * 년월
     */
    @Column(length = Columns.yyyymm)
    private String yyyymm;

    /**
     * 연도
     */
    @Column(length = Columns.yyyy)
    private String yyyy;

    /**
     * 월
     */
    @Column(length = Columns.mm)
    private String mm;

    /**
     * 일자
     */
    @Column(length = Columns.dd)
    private String dd;

    /**
     * 휴일구분(1-평일,2-휴일,3-대체휴일,4-임시공휴일)
     */
    @Column(length = Columns.holidayTypeCd)
    private String holidayTypeCd;

    /**
     * 월별 영업종료일자(yyyyMMdd)
     */
    @Column(length = Columns.closeYmd, nullable = false)
    private String closeYmd;

    /**
     * 분기
     */
    @Column(length = Columns.quarter)
    private String quarter;

    /**
     * 영업일수
     */
    private Integer bussDayCnt;
}
