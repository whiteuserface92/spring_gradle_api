package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/30
 * Time : 6:31 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseSupplier extends BaseModifiable {

    /**
     * 공급사유형
     * 1:상품유통(당사 서비스를 통해 주문대행)
     * 2:분석서비스 이용요금(질환분석,생체나이등 컨텐츠 제공)
     * 3:기타..
     * 서비스 추가시 지속적으로 정의
     */
    @Column(nullable = false, length = Columns.supplierType)
    private String supplierType;

    /**
     * 사용여
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 공급사관리용코드(코드관리 필요시 사용)
     */
    @Column(length = Columns.supplierCd)
    private String supplierCd;

    /**
     * 공급사명
     */
    @Column(nullable = false, length = Columns.supplierNm)
    private String supplierNm;

    /**
     * 대표자성명
     */
    @Column(nullable = false, length = Columns.repNm)
    private String repNm;

    /**
     * 사업자번호
     */
    @Column(nullable = false, length = Columns.bizNo)
    private String bizNo;

    /**
     * 대표전화번호
     */
    @Column(nullable = false, length = Columns.phoneNo)
    private String phoneNo;

    /**
     * 우편번호
     */
    @Column(length = Columns.postNo)
    private String postNo;

    /**
     * 사업장도로명주소
     */
    @Column(length = Columns.addr1)
    private String addr1;

    /**
     * 사업장상세주소
     */
    @Column(length = Columns.addr2)
    private String addr2;

    /**
     * 매출에 대한 정산방법
     * 1:선매입
     * 2:매월말정산 등
     */
    @Column(length = Columns.calculateType)
    private String calculateType;

    /**
     * 서비스설명
     */
    @Column(length = Columns.memo)
    private String memo;

    /**
     * 결제수단정책
     */
    @Column(length = Columns.payType)
    private String payType;

    /**
     * 환불허용여부
     */
    @Column(nullable = false)
    private Boolean refundEnabled;

    /**
     * 주문취소허용여부
     */
    @Column(nullable = false)
    private Boolean cancelEnabled;

    /**
     * 마일리지 적립정책(개별,카테고리별,전체 등)
     */
    @Column
    private String mileageType;
}
