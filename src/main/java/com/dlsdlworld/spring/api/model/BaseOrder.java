package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 상품주문
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseOrder extends BaseModifiable {

    /**
     * 발급수수료(증명)/컨텐츠제공(건강분석등)/상품판매
     */
    @Column(length = Columns.goodsType, nullable = false)
    private String goodsType;

    /**
     * 주문상태
     */
    @Column(length = Columns.goodsStatus, nullable = false)
    private String orderStatus;

    /**
     * 주문명
     */
    @Column(length = Columns.orderNm, nullable = false)
    private String orderNm;

    /**
     * 주문일시
     */
    @Column(nullable = false)
    private LocalDateTime orderedOn;

    /**
     * 상품합계(부가세별도)
     */
    @Column(nullable = false)
    private Integer goodsTot;

    /**
     * 상품부가세
     */
    @Column(nullable = false)
    private Integer goodsVatTot;

    /**
     * 대행수수료합계(VAT별도)
     */
    @Column(nullable = false)
    private Integer feeTot;

    /**
     * 대행수수료부가세
     */
    @Column(nullable = false)
    private Integer feeVatTot;

    /**
     * 배송비(VAT포함)
     */
    @Column(nullable = false)
    private Integer deliveryFee;

    /**
     * 할인액
     */
    @Column(nullable = false)
    private Integer discountAmt;

    /**
     * 환자번호
     */
    @Column(length = Columns.patientNo)
    private String patientNo;

    /**
     * 성명
     */
    @Column(length = Columns.userNm, nullable = false)
    private String userNm;

    /**
     * 휴대폰번호
     */
    @Column(length = Columns.homePhone)
    private String homePhone;

    /**
     * 전화번호
     */
    @Column(length = Columns.cellPhone, nullable = false)
    private String cellPhone;

    /**
     * 주문메모
     */
    @Column(length = Columns.memo)
    private String memo;

    /**
     * 메타포뮬러 상품매출의 경우 MD를 아웃소싱할 경우 판매수수료를 지급한다.
     */
    @Column
    private Long mdUserId;

}
