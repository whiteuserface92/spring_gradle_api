package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuFax extends BasePersistable {


	@Column(nullable = false)
	private String receiptNo;		// 접수번호 FAX 발송 접수 번호 ( 팝빌 리턴값 )

	@Column(nullable = false)
	private String orgReceiptNo;		// 접수번호 FAX 발송 접수 번호 ( 팝빌 리턴값 )

	@Column(nullable = false)
	private String requestNo;		// 전송요청번호 보험청구 ID 값

	@Column(nullable = false)
	private String orgRequestNo;		// 전송요청번호 보험청구 ID 값

	@Column(nullable = true)
	private String state;			// 전송상태 코드 "0 : (접수) / 1 : (변환중) / 2 : (팩스 전송중) / 3 : (처리완료) / 4 : (예약건 취소) 중 반환
									// ※ 전송상태 코드 상태 값은 작아지지 않음
									// └ 예) 3 => 2?변경 X"

	@Column(nullable = true)
	private String resultCd;			// 전송결과 코드 상태코드가 3 인 경우, result 값이 존재[참고] 팩스 전송 코드 테이블

	@Column(nullable = true)
	private String faxTitle;			// 팩스제목

	@Column(nullable = true)
	private String sendNo;			// 발신번호

	@Column(nullable = true)
	private String sendDtTxt;			// 전송일시 형식 : yyyyMMddHHmmss

	@Column(nullable = true)
	private String senderNm;		// 발신자명

	@Column(nullable = true)
	private String reserveDtTxt;		// 예약일시 형식 : yyyyMMddHHmmss

	@Column(nullable = true)
	private String resultDtTxt;		// 전송결과 수신일시 형식 : yyyyMMddHHmmss

	@Column(nullable = true)
	private String corpNo;			// 팝빌회원 사업자번호

	@Column(nullable = true)
	private String eventType;		// 이벤트 유형 "RESULT" : (문자 전송 결과) / "MANUAL" : (커넥트 수동 재실행) 중 반환

	@Column(nullable = true)
	private String eventDtTxt;			// 이벤트 실행일시 형식 : yyyyMMddHHmmss

	@Column(nullable = true)
	private String receiveNo;		// 수신번호

	@Column(nullable = true)
	private String receiveNm;		// 수신자명
	
	@Column(nullable = true)
	private String receiptDtTxt;		// 접수일시 형식 : yyyyMMddHHmmss

	@Column(nullable = true)
	private Integer serialNo;		// 일련번호

	@Column(nullable = true)
	private Integer sendPageCnt;		// 전체 페이지수

	@Column(nullable = true)
	private Integer successPageCnt;	// 성공 페이지수

	@Column(nullable = true)
	private Integer failPageCnt;		// 실패 페이지수

	@Column(nullable = true)
	private Integer refundPageCnt;	// 환불 페이지수

	@Column(nullable = true)
	private Integer cancelPageCnt;	// 취소 페이지수

	@Column(nullable = true)
	private Integer chargePageCnt;	// 과금 페이지수

	@Column(nullable = true)
	private String tiffFileSizeTxt;	// 변환된 팩스 파일 사이즈 단위 : byte

	@Column(nullable = true)
	private Long userId;

	@Column(nullable = true)
	private Long payOrderId;

	@Column(nullable = true)
	private String payReqTranKey;

	@Column(nullable = true)
	private Long appId;				//	앱 id

	@Column(nullable = false)
	private String claimKey;			//	claim key

	@Column(nullable = false)
	private String sendFileType;		//	fax 발송 파일별 타입
}
