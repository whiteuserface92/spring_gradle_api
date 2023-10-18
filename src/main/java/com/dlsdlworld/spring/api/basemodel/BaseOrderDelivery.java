//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//
///**
// * 주문배송관리
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseOrderDelivery extends BasePersistable {
//
//    /**
//     * 택배사코드
//     */
//    @Column(length = Columns.deliveryCd, nullable = false)
//    private String deliveryCd;
//
//    /**
//     * 운송장번호
//     */
//    @Column(length = Columns.invoiceNo, nullable = false)
//    private String invoiceNo;
//
//    /**
//     * 수취인명
//     */
//    @Column(length = Columns.rcvNm, nullable = false)
//    private String rcvNm;
//
//    /**
//     * 수취인전화번호
//     */
//    @Column(length = Columns.rcvTelNo)
//    private String rcvTelNo;
//
//    /**
//     * 수취인휴대전화
//     */
//    @Column(length = Columns.rcvHpNo, nullable = false)
//    private String rcvHpNo;
//
//    /**
//     * 배송지우편번호
//     */
//    @Column(length = Columns.postNo, nullable = false)
//    private String postNo;
//
//    /**
//     * 배송지도로명주소
//     */
//    @Column(length = Columns.addr1, nullable = false)
//    private String addr1;
//
//    /**
//     * 배송지지번주소
//     */
//    @Column(length = Columns.addr2, nullable = false)
//    private String addr2;
//
//}
