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
public abstract class BaseCompApiParam extends BaseCreatable {

    /**
     * 파라미터 순서
     */
    @Column(name = "param_ord", nullable = false)
    private Short paramOrd;

    /**
     * 데이터길이
     */
    @Column(name = "data_length", nullable = false)
    private Short dataLength;

    /**
     * 파라미터 메모
     */
    @Column(length = Columns.paramMemo)
    private String paramMemo;

}

