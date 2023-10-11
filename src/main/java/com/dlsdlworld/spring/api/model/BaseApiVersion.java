package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * API 정의
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/11
 * Time : 20:15
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseApiVersion extends BaseCreatable {

    /**
     * api ver
     */
    @Column(length = Columns.apiVer, nullable = false)
    private String apiVer;

    /**
     * 버전을 포함한 최종 서비스 URL
     * 기존 API정의에 있던 service_url을 버전으로 분리하면서 버전이 url을 갖는다
     */
    @Column(length = Columns.serviceUrl, nullable = false)
    private String serviceUrl;

    /**
     * 버전설명
     */
    @Column(length = Columns.versionDesc)
    private String versionDesc;

}

