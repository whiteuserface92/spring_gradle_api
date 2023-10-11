package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.IOTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 병원 API 파라미터 설정
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospitalApiParam extends BaseModifiable {

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
     * 미들웨어필드명
     */
    @Column(length = Columns.fieldNm)
    private String fieldNm;

    /**
     * 미들웨어필드ID
     */
    @Column
    private Long fieldId;

    /**
     * 테스트값
     */
    @Lob
    private String testValue;
}
