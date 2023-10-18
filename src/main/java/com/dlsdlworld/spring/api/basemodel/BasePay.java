//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//
///**
// * 결제연동설정
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BasePay extends BaseModifiable {
//
//    /**
//     * 결제구분
//     */
//    @Column(length = Columns.payType, nullable = false)
//    private String payType;
//
//    /**
//     * 단말기번호
//     */
//    @Column(length = Columns.catId, nullable = false)
//    private String catId;
//
//    /**
//     * 하이패스번호
//     */
//    @Column(length = Columns.hiPassId, nullable = false)
//    private String hiPassId;
//
//    /**
//     * 사업자번호
//     */
//    @Column(length = Columns.partnerTaxId, nullable = false)
//    private String partnerTaxId;
//
//    /**
//     * 파트너코드
//     */
//    @Column(length = Columns.partnerCd, nullable = false)
//    private String partnerCd;
//
//    /**
//     * 결제시작시간
//     */
//    @Column(length = Columns.payAllowStart, nullable = false)
//    private String payAllowStart;
//
//    /**
//     * 결제종료시간
//     */
//    @Column(length = Columns.payAllowEnd, nullable = false)
//    private String payAllowEnd;
//
//}
