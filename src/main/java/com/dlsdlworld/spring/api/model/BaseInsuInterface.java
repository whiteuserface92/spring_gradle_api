package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 보험사전문
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuInterface extends BaseCreatable {

    /**
     * 보험사id
     */
    @Column(nullable = false)
    private Long insuId;

    /**
     * 인터페이스id
     */
    @Column(nullable = false)
    private Long interfaceId;

    /**
     * 파라미터순서
     */
    @Column(nullable = false)
    private Short paramOrd;

    /**
     * 파라메터명
     */
    @Column(length = Columns.paramNm, nullable = false)
    private String paramNm;

    /**
     * 파라메터설명
     */
    @Column(length = Columns.paramDesc, nullable = false)
    private String paramDesc;

    /**
     * 필수여부
     */
    @Column(nullable = false)
    private Boolean required;

    /**
     * 배열그룹여부
     */
    @Column(nullable = false)
    private Boolean arrayEnabled;

    /**
     * 데이터유형
     */
    @Column(length = Columns.dataType, nullable = false)
    private String dataType;

    /**
     * 데이터길이
     */
    @Column(nullable = false)
    private Short dataLength;

    /**
     * 데이터포맷
     */
    @Column(length = Columns.dataFormat)
    private String dataFormat;

    /**
     * 파라메터메모
     */
    @Column(length = Columns.paramMemo)
    private String paramMemo;

    /**
     * 레벨
     */
    @Column
    private Short level;

    /**
     * 상위파라미터순서
     */
    @Column
    private Short upParamOrd;
}
