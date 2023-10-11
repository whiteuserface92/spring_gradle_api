package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/23
 * Time : 2:06 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuBank extends BaseModifiable {

    /**
     * 표시순서
     */
    @Column(length = Columns.dispOrd, nullable = false)
    private String dispOrd;

}
