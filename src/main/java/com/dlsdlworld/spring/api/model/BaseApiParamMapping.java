package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : hansik.shin
 * Date : 2020/05/13
 * Time : 11:09 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseApiParamMapping extends BaseModifiable {

    /**
     * 매핑용도설명
     */
    @Column(length = Columns.mapMemo)
    private String mapMemo;
}
