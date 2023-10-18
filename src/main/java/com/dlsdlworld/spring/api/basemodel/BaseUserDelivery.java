//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//import java.time.LocalDateTime;
//
///**
// * 배송지관리
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseUserDelivery extends BasePersistable {
//
//    /**
//     * 배송지명
//     */
//    @Column(length = Columns.deliveryNm, nullable = false)
//    private String deliveryNm;
//
//    /**
//     * 우편번호
//     */
//    @Column(length = Columns.postNo, nullable = false)
//    private String postNo;
//
//    /**
//     * 도로명주소
//     */
//    @Column(length = Columns.addr1, nullable = false)
//    private String addr1;
//
//    /**
//     * 지번주소
//     */
//    @Column(length = Columns.addr2, nullable = false)
//    private String addr2;
//
//    /**
//     * 입력일시
//     */
//    @Column(nullable = false)
//    private LocalDateTime createdOn;
//
//    /**
//     * 수정일시
//     */
//    @Column
//    private LocalDateTime updatedOn;
//
//}
