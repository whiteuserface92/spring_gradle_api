package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * API 데이터소스
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 12:15 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseApiDataSource extends BaseModifiable {

    /**
     * 데이터소스명
     */
    @Column(length = Columns.srcNm, nullable = false)
    private String srcNm;
}
