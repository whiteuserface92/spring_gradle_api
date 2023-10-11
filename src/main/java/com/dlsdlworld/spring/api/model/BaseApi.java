package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.ApiBodyTypes;
import com.dlsdlworld.spring.api.types.ApiMethodTypes;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * API 정의
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseApi extends BaseModifiable {

    /**
     * api 논리명
     */
    @Column(length = Columns.apiNm, nullable = false)
    private String apiNm;

    /**
     * 설명
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String apiDesc;

    /**
     * '/api/get_charge'
     */
    @Column(length = Columns.apiUrl, nullable = false)
    private String apiUrl;

    /**
     * 예제주소
     */
    @Column(length = Columns.exampleUrl)
    private String exampleUrl;

    /**
     * 메소드타입
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.methodType, nullable = false)
    private ApiMethodTypes methodType;

    /**
     * 응답타입
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.reqBodyType, nullable = false)
    private ApiBodyTypes reqBodyType;

    /**
     * 요청타입
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.resBodyType, nullable = false)
    private ApiBodyTypes resBodyType;

}

