package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 그룹소속병원
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 12:47 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseGroupHospital extends BaseModifiable {

    /**
     * 재정의 이미지 주소
     */
    @Column(length = Columns.ovrdImgUrl)
    private String ovrdImgUrl;

    /**
     * 표시순서
     */
    @Column(nullable = false, length = Columns.dispOrd)
    private String dispOrd;
}
