package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.ConstraintTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * 파라미터 제약설정
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospitalParamConstraint extends BaseModifiable {

    /**
     * 규칙유형
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.ruleType, nullable = false)
    private ConstraintTypes ruleType;

    /**
     * 규칙파라미터1
     */
    @Column(length = Columns.ruleArg)
    private String ruleArg1;

    /**
     * 규칙파라미터2
     */
    @Column(length = Columns.ruleArg)
    private String ruleArg2;
}
