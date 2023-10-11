package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 위젯관리
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : kyunghun.kim
 * Date : 2020/04/29
 * Time : 12:29 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseWidget extends BasePersistable {

    /**
     * 제품명
     */
    @Column(length = Columns.prodCd, nullable = false)
    private String prodCd;

    /**
     * 위젯명
     */
    @Column(length = Columns.widgetName, nullable = false)
    private String widgetName;

    /**
     * 제목메세지key
     */
    @Column(length = Columns.titleCd, nullable = false)
    private String titleCd;

    /**
     * 사용여부
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 사용조건
     */
    @Column(length = Columns.useCondition, nullable = false)
    private String useCondition;

}
