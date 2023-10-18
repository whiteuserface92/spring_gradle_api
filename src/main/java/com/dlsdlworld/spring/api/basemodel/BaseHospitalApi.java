//package com.dlsdlworld.spring.api.model;
//
//import com.dlsdlworld.spring.api.types.ApiCallTypes;
//import com.dlsdlworld.spring.api.types.ApiFieldManipulationTypes;
//import com.dlsdlworld.spring.api.types.ApiTypes;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.MappedSuperclass;
//
///**
// * 병원별 API 설정
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseHospitalApi extends BaseModifiable {
//
//    /**
//     * api 유형
//     */
//    @Enumerated(EnumType.STRING)
//    @Column(length = Columns.apiType, nullable = false)
//    private ApiTypes apiType;
//
//    /**
//     * 접속방식
//     */
//    @Enumerated(EnumType.STRING)
//    @Column(length = Columns.callType, nullable = false)
//    private ApiCallTypes callType;
//
//    /**
//     * 룰체크사용여부
//     */
//    @Column(nullable = false)
//    private Boolean validatable;
//
//    /**
//     * 프로시져명
//     */
//    @Column(length = Columns.procNm)
//    private String procNm;
//
//    /**
//     * 커서명
//     */
//    @Column(length = Columns.cursorNm)
//    private String cursorNm;
//
//    /**
//     * 최대응답 로우수
//     */
//    @Column
//    private Integer maxRows;
//
//    /**
//     * 턱시도통신타입
//     */
//    @Enumerated(EnumType.STRING)
//    @Column(length = Columns.fmlType)
//    private ApiFieldManipulationTypes fmlType;
//
//    /**
//     * 미들웨어서비스명
//     */
//    @Column(length = Columns.remoteServiceNm)
//    private String remoteServiceNm;
//
//    /**
//     * 미티어타입
//     */
//    @Column(length = Columns.mediaType)
//    private String mediaType;
//
//    /**
//     * 웹서비스 url
//     */
//    @Column(length = Columns.serviceUrl)
//    private String webserviceUrl;
//
//    /**
//     * 이전 버전QAB 마이그 및 검증용 컬럼으로 사용
//     */
//    @Column(length = Columns.serviceUrl)
//    private String ovrdServiceUrl;
//}
