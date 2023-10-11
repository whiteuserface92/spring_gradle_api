package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/30
 * Time : 8:17 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCertGoods extends BasePersistable {

    /**
     * 환자번호
     */
    @Column(length = Columns.patientNo, nullable = false)
    private String patientNo;

    /**
     * 제증명서식코드
     */
    @Column(length = Columns.certCd, nullable = false)
    private String certCd;

    /**
     * 제증명서식명
     */
    @Column(length = Columns.certNm, nullable = false)
    private String certNm;

    /**
     * 제증명문서번호
     */
    @Column(length = Columns.certDocNo, nullable = false)
    private String certDocNo;

    /**
     * 제증명서상태
     */
    @Column(length = Columns.certStatus, nullable = false)
    private String certStatus;

    /**
     * 결제일시
     */
    private LocalDateTime paydOn;

    /**
     * 발행매수
     */
    @Column(nullable = false)
    private Integer issueCnt;

    /**
     * 진료일자
     */
    private LocalDateTime caredOn;

    /**
     * 진료과코드
     */
    @Column(length = Columns.careDeptCd)
    private String careDeptCd;

    /**
     * 진료과명
     */
    @Column(length = Columns.careDeptNm)
    private String careDeptNm;

    /**
     * 의사명
     */
    @Column(length = Columns.dockerNm)
    private String doctorNm;

    /**
     * 작성일자
     */
    private LocalDate writedOn;

    /**
     * 발급용도코드
     */
    @Column(length = Columns.careDeptCd)
    private String useCd;

    /**
     * 진료구분
     */
    @Column(length = Columns.careDeptCd)
    private String treatType;

    /**
     * 접수번호
     */
    @Column(length = Columns.careDeptCd)
    private String receiptNo;

    /**
     * 영수증번호
     */
    @Column(length = Columns.careDeptCd)
    private String billNo;

    /**
     * 유효시작일
     */
    private LocalDate startedOn;

    /**
     * 유효종료일
     */
    private LocalDate endedOn;

    /**
     * 진료비수납코드
     */
    @Column(length = Columns.careDeptCd)
    private String treatReceiptCd;

    /**
     * 진료비유형코드
     */
    @Column(length = Columns.careDeptCd)
    private String treatTypeCd;

    /**
     * 진료비보조코드
     */
    @Column(length = Columns.careDeptCd)
    private String treatSubCd;

    /**
     * 진료비항목코드
     */
    @Column(length = Columns.careDeptCd)
    private String treatItemsCd;

    /**
     * 출력회수
     */
    @Column(nullable = false)
    private Short successCnt;

    /**
     * 출력실패부수
     */
    @Column(nullable = false)
    private Short failCnt;

    /**
     * 입력일시
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * 수정일시
     */
    @Column
    private LocalDateTime updatedOn;

    /**
     * 제증명서 미리보기 파일
     */
    @Column(name = "preview_file_id")
    private Long previewFileId;


}
