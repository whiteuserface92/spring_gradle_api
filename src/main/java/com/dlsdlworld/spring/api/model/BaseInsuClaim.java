package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.converter.ClaimStatusTypeConverter;
import com.dlsdlworld.spring.api.types.ClaimStatusTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 실손청구이력
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuClaim extends BasePersistable {

    /**
     * 뚝딱서비스를 사용하는 사용자 ID(병원-환자번호)
     * 초기버전은 병원에서 뚝딱으로 넘어온 관계로 환자번호를 id로 사용했음
     */
    @Column(length = Columns.patientNo)
    private String patientNo;

    /**
     * 병원접수키(병원코드,신청일자,환자번호,진료과,순번 조합)
     * 진료영수증 단위의 Unique값
     * 병원코드+환자번호+영수증번호++ 병원별 항목 조합하여 사용
     */
    @Column(length = Columns.claimKey)
    private String claimKey;

    /**
     * 진료영수증에 부여되는 병원 영수증번호
     */
    @Column(length = Columns.billNo)
    private String billNo;

    /**
     * 청구진행상태코드
     */
    @Convert(converter = ClaimStatusTypeConverter.class)
    @Column(length = Columns.claimStatus, nullable = false)
    private ClaimStatusTypes claimStatus;

    /**
     * 병원접수번호
     */
    @Column(length = Columns.cureReceiptNo)
    private String cureReceiptNo;

    /**
     * 병원에서 사용하는 진료과목 코드
     */
    @Column(length = Columns.cureDeptCd)
    private String cureDeptCd;

    /**
     * 진료일자
     */
    private LocalDate curedOn;

    /**
     * 병원, 키오스크 등 유입 채널에 따른 구분(1:키오스크, 2:병원직접, 3:제휴앱 등)
     */
    @Column(length = Columns.channelCd, nullable = false)
    private String channelCd;

    /**
     * 입원통원구분(외래:O, 입원:I)
     */
    @Column(length = Columns.ioType, nullable = false)
    private String ioType;

    /**
     * 보험청구일시
     */
    @Column(nullable = false)
    private LocalDateTime claimedOn;

    /**
     * 사고(진료발생)일자
     */
    private LocalDateTime accidentedOn;

    /**
     * 뚝딱청구 완료 시 부여되는 보험사 청구접수번호
     */
    @Column(length = Columns.claimReceiptNo)
    private String claimReceiptNo;

    /**
     * 앱이 사용되는 국가의 표준국가코드 : KOR
     */
    @Column(length = Columns.countryCd)
    private String countryCd;

    /**
     * 통화단위 : KRW
     */
    @Column(length = Columns.currencyCd)
    private String currencyCd;

    /**
     * 진료영수금액(통화코드에 따른 금액임)
     */
    private BigDecimal receiptAmt;

    /**
     * 청구방법 01:EDI, 02:팩스, 03:EDI이미지
     */
    @Column(nullable = false, length = Columns.claimType)
    private String claimType;

    /**
     * 본인CI 정보
     * 과거데이터의 호환을 위해 존재함
     */
    @Column(length = Columns.ci)
    private String myCi;

    /**
     * 접수결과회신방법
     * 보험사로부터 접수결과를 수신받는 방법을 설정
     * vertical bar로 구분하여 멀티선택값을 저장한다.
     * 예시) 문자|유선|메일
     */
    @Column(length = Columns.replyMethod)
    private String replyMethod;

    /**
     * 생성일자
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * 청구 오류메세지
     */
    private String logMsg;

    /**
     * 병원코드(요양기관코드) 과거자료 보관용
     */
    @Column(length = Columns.hospitalCd)
    private String hospitalCd;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
    }
}
