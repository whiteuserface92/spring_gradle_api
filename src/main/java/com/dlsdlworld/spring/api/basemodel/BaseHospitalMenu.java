package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.types.MenuTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import static com.dlsdlworld.spring.api.basemodel.Columns.serviceUrl;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospitalMenu extends BaseModifiable {

    /**
     * 표시순서
     */
    @Column(nullable = false)
    private String dispOrd;

    /**
     * 사용여부
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 노드레벨
     */
    @Column(nullable = false)
    private Short level;

    /**
     * 메뉴타입
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.menuType, nullable = false)
    private MenuTypes menuType;

    /**
     * 재정의이미지주소코드
     */
    @Column(length = serviceUrl)
    private String ovrdImgUrl;

    /**
     * 재정의메뉴명코드
     */
    @Column(length = Columns.ovrdNameCd)
    private String ovrdNameCd;

    /**
     * 재정의서비스주소코드
     */
    @Column(length = serviceUrl)
    private String ovrdServiceUrl;

    /**
     * 제품구분
     */
    @Column(length = Columns.prodCd, nullable = false)
    private String prodCd;

    /**
     * 메뉴 접근 권한이 부서 리스트를 ,로 분류해서 넣은값
     * 개인별 메뉴 조회시 deptWhiteList 공백이면 모든 사용자 접근 가능하며
     * 입력되어 있으면 해당 부서 사용자만 접근 가능하다
     */
    @Column(length = Columns.codeDesc, nullable = false)
    private String deptWhiteList;

}
