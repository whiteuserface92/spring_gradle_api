package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 제증명상품정보
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCertInfo implements Serializable {

    /**
     * BaseGoods와 1:1 관계이므로 자동증가를 사용하지 않는다.
     */
    @Id
    private Long certInfoId;

    /**
     * 상병코드여부
     */
    @Column(nullable = false)
    private Boolean diagEnabled;

    /**
     * 내보내기_PDF여부
     */
    @Column(nullable = false)
    private Boolean pdfEnabled;

    /**
     * 내보내기_PDF_암호여부
     */
    @Column(nullable = false)
    private Boolean pdfEncEnabled;

    /**
     * MobileFax 사용여부
     */
    @Column(nullable = false)
    private Boolean faxEnabled;

    /**
     * SNS공유여부
     */
    @Column(nullable = false)
    private Boolean snsShareEnabled;

    /**
     * 메일첨부허용여부
     */
    @Column(nullable = false)
    private Boolean attachEnabled;

    /**
     * 바코드여부
     */
    @Column(nullable = false)
    private Boolean barcode2dEnabled;

    /**
     * 바코드속성
     */
    @Column(length = Columns.barcode2dType)
    private String barcode2dType;

    /**
     * 제증명문서의 유효일수(14일,90일 등)
     * 서식별로 달라지기 때문에 상품에서 관리
     */
    @Column(nullable = false)
    private Short expireDays;

    /**
     * 데이터유통 형태의 서비스에 대한 수수료정책코드
     * 1:건별수수료(상품주문개수에 따라 부과)
     * 2:상품별수수료(상품종류에 따라 부과)
     */
    @Column(nullable = false, length = Columns.feePolicyCd)
    private String feePolicyCd;

    /**
     * 대행수수료(VAT별도)
     */
    @Column(nullable = false)
    private Integer fee;

    /**
     * 수수료부가세
     */
    @Column(nullable = false)
    private Integer feeVat;

    /**
     * 검색옵션
     */
    @Column(length = Columns.searchOpt)
    private String searchOpt;

    /**
     * 서식이름
     */
    @Column(length = Columns.templateNm)
    private String templateNm;

    /**
     * 문서확인번호사용여부
     */
    @Column(nullable = false)
    private Boolean docVerifyEnabled;

    /**
     * 타임스템프사용여부
     */
    @Column(nullable = false)
    private Boolean timestampEnabled;

    /**
     * 타임스템프위치X
     */
    @Column(name = "timestamp_x")
    private Integer timestampX;

    /**
     * 타임스템프위치Y
     */
    @Column(name = "timestamp_y")
    private Integer timestampY;

    /**
     * 서명URI여부
     */
    @Column(nullable = false)
    private Boolean signUriEnabled;

    /**
     * 용도구분코드 사용여부
     */
    @Column(nullable = false)
    private Boolean useCdEnabled;

}
