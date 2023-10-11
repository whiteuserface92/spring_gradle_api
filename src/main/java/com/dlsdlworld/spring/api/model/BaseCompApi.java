package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.IOTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * API 정의
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCompApi extends BaseCreatable {

    /**
     * 기관구분
     */
    @Column(length = Columns.compType, nullable = false)
    private String compType;

    /**
     * 기관id
     */
    @Column(nullable = false)
    private Long compId;

    /**
     * 송수신전문구분
     */
    @Enumerated(value = EnumType.STRING)
    @Column(length = Columns.ioType, nullable = false)
    private IOTypes ioType;

    /**
     * 헤더길이포함여부
     */
    @Column(nullable = false)
    private Boolean headerEnabled;

    /**
     * 인터페이스설명
     */
    @Column(length = Columns.interfaceDesc)
    private String interfaceDesc;

//    /**
//     * tcp connection 재사용 여부
//     */
//    @Column(nullable = false)
//    private Boolean tcpReuseEnabled;
//
//    /**
//     * 전문 문자셋
//     */
//    @Column(nullable = false, length = Columns.characterSet)
//    private String characterSet;
//
//    /**
//     * 헤더 필드 카운트
//     */
//    @Column(nullable = false)
//    private Short headerCnt;
//
//    /**
//     * id 필드명 배열
//     */
//    @Column(length = Columns.fieldIds)
//    private String fieldIds;
}

