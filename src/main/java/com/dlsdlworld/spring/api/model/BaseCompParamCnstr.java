package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * API 정의
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/11
 * Time : 20:15
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCompParamCnstr extends BaseCreatable {

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

}

