package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 생체인증 설정정보 관리
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 12:43 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseFidoSet extends BaseModifiable {

    /**
     * 식별코드
     */
    @Column(length = Columns.tenantCd, nullable = false)
    private String tenantCd;

    /**
     * api키
     */
    @Column(length = Columns.apiKey, nullable = false)
    private String apiKey;
}
