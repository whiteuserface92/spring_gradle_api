package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.DataTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * 미들웨어 고정변수
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseApiParamLegacy extends BaseModifiable {

    /**
     * 순번
     */
    @Column(nullable = false)
    private Short seq;

    /**
     * 레거시 필드식별자
     */
    @Column(nullable = false)
    private Long fieldId;

    /**
     * 필드명
     */
    @Column(length = Columns.fieldNm, nullable = false)
    private String fieldNm;

    /**
     * 필드명
     */
    @Column(length = Columns.fieldValue, nullable = false)
    private String fieldValue;

    /**
     * 데이터유형
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.dataType, nullable = false)
    private DataTypes dataType;

    /**
     * 필드설명
     */
    @Column(length = Columns.fieldDesc)
    private String fieldDesc;
}
