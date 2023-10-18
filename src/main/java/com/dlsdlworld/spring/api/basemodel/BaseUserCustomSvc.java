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
// * 환자맞춤서비스
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseUserCustomSvc extends BasePersistable {
//
//    /**
//     * 대시보드종류
//     */
//    @Column(length = Columns.dashboardType, nullable = false)
//    private String dashboardType;
//
//    /**
//     * 내일정표시값
//     */
//    @Column(length = Columns.dispSchedule, nullable = false)
//    private String dispSchedule;
//
//    /**
//     * 진료과
//     */
//    @Column(length = Columns.treatDeptNm)
//    private String treatDeptNm;
//
//    /**
//     * 담당의사명
//     */
//    @Column(length = Columns.doctorNm)
//    private String doctorNm;
//
//    /**
//     * 표시금액
//     */
//    @Column
//    private Integer dispAmt;
//
//    /**
//     * 표시내용
//     */
//    @Column(length = Columns.dispContent)
//    private String dispContent;
//
//    /**
//     * 표시내용예비1
//     */
//    @Column(length = Columns.dispContent1)
//    private String dispContent1;
//
//    /**
//     * 표시내용예비2
//     */
//    @Column(length = Columns.dispContent2)
//    private String dispContent2;
//
//    /**
//     * 링크url
//     */
//    @Column(length = Columns.linkUrl)
//    private String linkUrl;
//
//    /**
//     * 입력일시
//     */
//    @Column(nullable = false)
//    private LocalDateTime createdOn;
//
//}
