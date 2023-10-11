package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 전화번호안내
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 1:17 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BasePhoneBook extends BaseModifiable {

    /**
     * 전화번호명
     */
    @Column(length = Columns.phoneNm, nullable = false)
    private String phoneNm;

    /**
     * 전화번호
     */
    @Column(length = Columns.phoneNo, nullable = false)
    private String phoneNo;

    /**
     * 노드레벨
     */
    @Column(nullable = false)
    private Short level;

    /**
     * 표시순서
     */
    @Column(nullable = false, length = Columns.dispOrd)
    private String dispOrd;
}
