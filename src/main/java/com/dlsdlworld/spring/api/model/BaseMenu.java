package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 메뉴정의
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseMenu extends BaseModifiable {

    /**
     * 메뉴명코드
     */
    @Column(length = Columns.nameCd, nullable = false)
    private String nameCd;

    /**
     * 메뉴설명
     */
    @Column(length = Columns.menuDesc)
    private String menuDesc;

    /**
     * 서비스주소
     */
    @Column(length = Columns.serviceUrl)
    private String serviceUrl;

    /**
     * 이미지주소코드
     */
    @Column(length = Columns.imgUrlCd, nullable = false)
    private String imgUrlCd;
}
