package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 보험전문정의
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : kyunghun.kim
 * Date : 2020/04/28
 * Time : 3:41 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInterface extends BasePersistable {

    /**
     * 인터페이스코드
     */
    @Column(length = Columns.interfaceCd, nullable = false)
    private String interfaceCd;

    /**
     * 인터페이스명
     */
    @Column(length = Columns.interfaceNm, nullable = false)
    private String interfaceNm;

    /**
     * 송수신전문구분
     */
    @Column(length = Columns.ioType, nullable = false)
    private String ioType;

    /**
     * 설명
     */
    @Column(length = Columns.interfaceDesc)
    private String interfaceDesc;

    /** 
     * 호출url
     */
    @Column(length = Columns.url)
    private String url;

}
