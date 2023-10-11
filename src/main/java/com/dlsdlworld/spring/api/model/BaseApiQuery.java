package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
 * API 쿼리
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 12:24 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseApiQuery extends BaseModifiable {

    /**
     * 순서
     */
    @Column(nullable = false)
    private Short seq;

    /**
     * SQL
     */
    @Lob
    @Column(nullable = false)
    private String sqlText;

    /**
     * 메타포함 SQL
     */
    @Lob
    @Column(nullable = false)
    private String htmlText;

}
