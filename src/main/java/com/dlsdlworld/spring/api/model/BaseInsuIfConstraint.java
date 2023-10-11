package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 공지사항
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuIfConstraint extends BaseModifiable {

    /**
     * 규칙유형
     */
    @Column(length = Columns.ruleType, nullable = false)
    private String ruleType;

    /**
     * 조건값1
     */
    @Column(length = Columns.ruleArg)
    private String ruleArg1;

    /**
     * 조건값2
     */
    @Column(length = Columns.ruleArg)
    private String ruleArg2;

    /**
     * 파라메터id
     */
    @Column(nullable = false)
    private Long insuInterfaceId;

}
