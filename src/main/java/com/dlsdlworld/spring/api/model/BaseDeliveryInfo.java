package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 택배사정보
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseDeliveryInfo implements Serializable {

    /**
     * 택배사코드
     */
    @Id
    @Column(length = Columns.deliveryCd, nullable = false)
    private String deliveryCd;

    /**
     * 택배사명
     */
    @Column(length = Columns.deliveryNm, nullable = false)
    private String deliveryNm;

    /**
     * 홈페이지url
     */
    @Column(length = Columns.homeUrl, nullable = false)
    private String homeUrl;

    /**
     * 배송추적url
     */
    @Column(nullable = false, length = Columns.deliveryUrl)
    private String deliveryUrl;

    /**
     * 일자
     */
    @Column(length = Columns.csTelNo)
    private String csTelNo;

    /** 생성일시*/
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /** 수정일시*/
    @Column
    private LocalDateTime updatedOn;

}
