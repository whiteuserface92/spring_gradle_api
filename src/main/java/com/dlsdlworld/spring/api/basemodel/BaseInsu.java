//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//import java.time.LocalDate;
//
///**
// * 보험사정보
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseInsu extends BaseModifiable {
//
//    /**
//     * 보험사코드
//     */
//    @Column(length = Columns.insuCompCd, nullable = false)
//    private String insuCompCd;
//
//    /**
//     * 보험사명
//     */
//    @Column(length = Columns.insuCompNm, nullable = false)
//    private String insuCompNm;
//
//    /**
//     * 보험사표시(짦은이름)
//     */
//    @Column(length = Columns.insuDispNm, nullable = false)
//    private String insuDispNm;
//
//    /**
//     * I:손해보험사, L:생명보험사
//     */
//    @Column(length = Columns.insuCompType, nullable = false)
//    private String insuCompType;
//
//    /**
//     * 전문교환방식(TCP,JSON)
//     */
//    @Column(length = Columns.ifType, nullable = false)
//    private String ifType;
//
//    /**
//     * 서비스시작여부
//     */
//    @Column(nullable = false)
//    private Boolean enabled;
//
//    /**
//     * 서비스개시일
//     */
//    @Column
//    private LocalDate openedOn;
//
//    /**
//     * 정렬순서-손보,생보별로 순번부여
//     */
//    @Column(nullable = false)
//    private String dispOrd;
//
//    /**
//     * 홈페이지 URL
//     */
//    @Column
//    private String homeUrl;
//
//    /**
//     * 로고URL
//     */
//    @Column
//    private String logoUrl;
//
//    /**
//     * 팩스번호
//     */
//    @Column
//    private String faxNo;
//
//    /**
//     * 콜센터전화번호
//     */
//    @Column
//    private String csPhoneNo;
//
//    @Column
//    private String claimType;
//}
