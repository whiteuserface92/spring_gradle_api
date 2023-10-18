package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 생체인증 설정정보 관리
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
