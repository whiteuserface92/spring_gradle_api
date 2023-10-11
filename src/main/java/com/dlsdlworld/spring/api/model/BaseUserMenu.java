package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.MenuTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;


/**
 * 메뉴유형
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 4:55 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserMenu extends BaseModifiable {

    /**
     * 메뉴유형
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.menuType, nullable = false)
    private MenuTypes menuType;

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

    /**
     * 재정의메시지코드
     */
    @Column(length = Columns.ovrdMsgCd)
    private String ovrdMsgCd;

    /**
     * 재정의서비스주소
     */
    @Column(length = Columns.ovrdServiceUrl)
    private String ovrdServiceUrl;

    /**
     * 재정의이미지조수
     */
    @Column(length = Columns.ovrdImgUrl)
    private String ovrdImgUrl;
}
