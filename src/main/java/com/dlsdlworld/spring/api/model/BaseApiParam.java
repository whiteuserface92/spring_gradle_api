package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.DataTypes;
import com.dlsdlworld.spring.api.types.IOTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseApiParam extends BaseModifiable {

    /**
     * 입출력구분
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.ioType, nullable = false)
    private IOTypes ioType;

    /**
     * 파라미터순서
     */
    @Column(nullable = false)
    private Short paramOrd;

    /**
     * 필수여부
     */
    @Column(nullable = false)
    private Boolean required;

    /**
     * 재정의파라미터
     */
    @Column(length = Columns.ovrdParamNm)
    private String ovrdParamNm;

    /**
     * 재정의데이터타입
     */
    @Enumerated(EnumType.STRING)
    private DataTypes ovrdDataType;

    /**
     * 레벨
     */
    private Short level;

    /**
     * 상위파라미터순서, 파라메터가 구조체일 경우 상위파라미터순서값으로 묶기위함
     */
    private Short upParamOrd;

    /**
     * 데이터길이
     */
    private Short dataLength;

    /**
     * 소수점자리수
     */
    private Short dataScale;

    /**
     * 데이터포맷은 도메인에 정의하지만, 도메인과 다르게 포맷을 정의해야 할 경우 파라메터에 정의한 값을 우선으로 한다.
     */
    @Column(length = Columns.dataFormat)
    private String dataFormat;

    /**
     * 파라메터에 대한 설명
     */
    @Column(length = Columns.paramMemo)
    private String paramMemo;
}
