package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * PG결제요청
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BasePaymentLog extends BasePersistable {

    /**
     * 결제요청명
     */
    @Column(length = Columns.payTitle, nullable = false)
    private String payTitle;

    /**
     * 결제요청금액=상품합계(V별도)+부가세+수수료(V포함)+배송비(V포함)-할인금액(마일리지차감액)
     */
    @Column(nullable = false)
    private Integer payTot;

    /**
     * 메인결제수단
     */
    @Column(length = Columns.payType, nullable = false)
    private String payType;

    /**
     * 서브결제수단(포인트 결제등)
     */
    @Column(length = Columns.subPayType, nullable = false)
    private String subPayType;

    /**
     * 현금영수증신청여부
     */
    @Column(nullable = false)
    private Boolean cashBillEnabled;

    /**
     * 결제응답코드(결제시스템에서 정의, PG사마다 다를수 있음)
     */
    @Column(length = Columns.payRetCd)
    private String payRetCd;

    /**
     * 결제요청일시
     */
    @Column(nullable = false)
    private LocalDateTime payRequestedOn;

    /**
     * 결제완료일시
     */
    private LocalDateTime payFinishedOn;

    /**
     * PG사 결제전문 확인키(레몬케어4.0결제서버와 연결Key)
     */
    @Column(length = Columns.pgTranKey)
    private String pgTranKey;

    /**
     * 승인카드사명
     */
    @Column(length = Columns.cardBuyerNm)
    private String cardBuyerNm;

    /**
     * 결제카드번호
     */
    @Column(length = Columns.cardNo)
    private String cardNo;

    /**
     * 승인일시
     */
    private LocalDateTime approvedOn;

    /**
     * 승인번호
     */
    @Column(length = Columns.approvalNo)
    private String approvalNo;

    /**
     * 할부개월수
     */
    private Integer installmentCnt;

    /**
     * 취소여부
     */
    @Column(nullable = false)
    private Boolean cancelEnabled;

    /**
     * 취소요청일시
     */
    private LocalDateTime cancelRequestedOn;

    /**
     * 취소일시
     */
    private LocalDateTime canceledOn;

    /**
     * 취소응답코드
     */
    private String cancelRetCd;

    /**
     * 취소응답메세지
     */
    private String cancelRetMsg;

    /**
     * 생성일시
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * 수정일시
     */
    @Column
    private LocalDateTime updatedOn;


    /**
     * 트랜잭션 식별자
     */
    @Column
    private String reqTranKey;


}
