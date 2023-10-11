package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.event.BaseCommonCodeListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;


/**
 * 공통코드관리
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(BaseCommonCodeListener.class)
public abstract class BaseCommonCode extends BaseModifiable {

    /**
     * 분류코드
     */
    @Column(name = "code_cls", length = Columns.codeCls, nullable = false)
    private String codeCls;

    /**
     * 코드타입
     */
    @Column(name = "code_type", length = Columns.codeType, nullable = false)
    private String codeType;

    /**
     * 코드
     */
    @Column(name = "code", length = Columns.code, nullable = false)
    private String code;

    /**
     * 코드명
     */
    @Column(length = Columns.codeNm, nullable = false)
    private String codeNm;

    /**
     * 코드영문명
     */
    @Column(length = Columns.codeNmEng)
    private String codeNmEng;

    /**
     * 코드설명
     */
    @Column(length = Columns.codeDesc)
    private String codeDesc;

    /**
     * 표시순서
     */
    @Column(nullable = false, length = Columns.dispOrd)
    private String dispOrd;

    /**
     * 참조값
     */
    @Column(length = Columns.refVal)
    private String refVal;
}
