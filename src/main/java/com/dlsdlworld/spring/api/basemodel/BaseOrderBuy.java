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
// * 발주관리
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseOrderBuy extends BasePersistable {
//
//    /**
//     * 발주일시
//     */
//    @Column(nullable = false)
//    private LocalDateTime buyDttm;
//
//    /**
//     * 발주상태
//     */
//    @Column(length = Columns.buyStatus, nullable = false)
//    private String buyStatus;
//
//    /**
//     * 발주금액(V별도)
//     */
//    @Column(nullable = false)
//    private Integer buyTot;
//
//    /**
//     * 부가세
//     */
//    @Column(nullable = false)
//    private Integer vatTot;
//
//    /**
//     * 정산완료일
//     */
//    @Column
//    private LocalDateTime closedOn;
//
//}
