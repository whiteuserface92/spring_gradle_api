package com.dlsdlworld.spring.api.types;

import org.springframework.http.HttpStatus;

/**

 */
public enum ErrorTypes {

	/**
	 * 정의되지 않은 서버에러
	 */
	INTERNAL_SERVER("-00001", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 리소스를 찾을 수 없음
	 */
	, RESOURCE_NOT_FOUND("-00002", HttpStatus.NOT_FOUND)

	/**
	 * 요청을 수락할 수 없음
	 */
	, REQUEST_NOT_ACCEPTABLE("-00003", HttpStatus.NOT_ACCEPTABLE)

	/**
	 * 응답 json을 생성할 수 없음
	 */
	, HTTP_MESSAGE_NOT_WRITABLE("-00004", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 요청 대기시간 초과
	 */
	, REQUEST_TIME_OUT("-00005", HttpStatus.REQUEST_TIMEOUT)

	/**
	 * 캐시처리 에러
	 */
	, CACHE_OPERATION("-00006", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 캐시 읽기 에러
	 */
	, CACHE_READ("-00007", HttpStatus.NOT_FOUND)

	/**
	 * 프레임워크 설정 오류
	 */
	, FRAMEWORK_CONFIG("-00008", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 사용자 비밀번호 오류
	 */
	, INVALID_USER_CREDENTIALS("-00009", HttpStatus.UNAUTHORIZED)

	/**
	 * 권한 부족 오류
	 */
	, INSUFFICIENT_PRIVILEGES("-00010", HttpStatus.FORBIDDEN)

	/**
	 * 잘못된 요청 파라미터 오류
	 */
	, INVALID_REQUEST_PARAMETER("-00011", HttpStatus.BAD_REQUEST)

	/**
	 * fcm키를 찾을 수 없음
	 */
	, FCM_KEY_NOT_FOUND("-00012", HttpStatus.NOT_FOUND)

	/**
	 * 잘못된 푸시 응답 오류
	 */
	, INVALID_PUSH_RESPONSE("-00013", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 잘못된 tcp 메시지 오류
	 */
	, INVALID_TCP_MESSAGE("-00014", HttpStatus.BAD_REQUEST)

	/**
	 * 잘못된 tcp 메시지 설정 오류
	 */
	, INVALID_TCP_MESSAGE_CONFIG("-00015", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * tcp 메시지 직렬화 오류(송신)
	 */
	, TCP_MESSAGE_SERILIZE("-00016", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * tcp 메시지 역직렬화 오류(수신)
	 */
	, TCP_MESSAGE_DESERILIZE("-00017", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 필드값 제약조건 위반
	 */
	, FIELD_CONSTRAINT_VIOLATION("-00018", HttpStatus.BAD_REQUEST)

	/**
	 * 해당 요청이 지원되지 않음
	 */
	, OPERATION_NOT_SUPPORTED("-00019", HttpStatus.NOT_IMPLEMENTED)

	/**
	 * 설정 파일을 찾을 수 없음
	 */
	, FIELD_CONFIG_NOT_FOUND("-00020", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 메시지 설정을 찾을 수 없음
	 */
	, MESSAGE_CONFIG_NOT_FOUND("-00021", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * api 정보가 올바르지 않음
	 */
	, INVALID_API_INFO("-00022", HttpStatus.BAD_REQUEST)

	/**
	 * api 파라미터 정보가 올바르지 않음
	 */
	, INVALID_API_PARAM("-00023", HttpStatus.BAD_REQUEST)

	/**
	 * api를 찾을 수 없음
	 */
	, API_NOT_FOUND("-00024", HttpStatus.NOT_FOUND)

	/**
	 * api 수행 에러
	 */
	, API_CALL("-00025", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * api 파라미터 찾을 수 없음
	 */
	, API_PARAM_NOT_FOUND("-00026", HttpStatus.NOT_FOUND)

	/**
	 * 제약조건 위반
	 */
	, CONSTRAINT_VIOLATION("-00027", HttpStatus.BAD_REQUEST)

	/**
	 * 인증토큰만료
	 */
	, TOKEN_EXPIRED("-00028", HttpStatus.UNAUTHORIZED)

	/**
	 * 비밀번호 불일치
	 */
	, PASSWORD_MISMATCH("-00029", HttpStatus.UNAUTHORIZED)

	/**
	 * 비밀번호 확인 불일치
	 */
	, PASSWORD_CONFIRM_MISMATCH("-00030", HttpStatus.BAD_REQUEST)

	/**
	 * 파라미터 널 또는 공백
	 */
	, PARAM_NOTEMPTY("-00031", HttpStatus.BAD_REQUEST)

	/**
	 * 파라미터 널
	 */
	, PARAM_NOTNULL("-00032", HttpStatus.BAD_REQUEST)

	/**
	 * 파라미터 최소값
	 */
	, PARAM_MIN("-00033", HttpStatus.BAD_REQUEST)

	/**
	 * 파라미터 최대값
	 */
	, PARAM_MAX("-00034", HttpStatus.BAD_REQUEST)

	/**
	 * 잘못된 api url
	 */
	, INVALID_API_URL("-00035", HttpStatus.BAD_REQUEST)

	/**
	 * 병원 api 없음
	 */
	, HOSPITAL_API_NOT_FOUND("-00036", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 엔티티 저장 에러
	 */
	, ENTITY_SAVE("-00037", HttpStatus.BAD_REQUEST)

	/**
	 * 잘못된 파라미터
	 */
	, ILLEGAL_ARGUMENT("-00038", HttpStatus.BAD_REQUEST)

	/**
	 * api param validation error
	 */
	, API_PARAM_CONSTRAINT_VIOLATION("-00039", HttpStatus.BAD_REQUEST)

	/**
	 * 지원하지 않는 제약조건
	 */
	, UNSUPPORTED_CONSTRAINT_VIOLATION("-00040", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 잘못 설정된 파라미터 타입
	 */
	, WRONG_FIELD_CONFIG("-00041", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 이메일 전송 실패
	 */
	, EMAIL_SEND_FAILED("-00042", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 로그 쓰기 에러
	 */
	, LOG_WRITE_FAILED("-00043", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 사용자 api 없음
	 */
	, USER_API_NOT_FOUND("-00044", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * API 응답이 유효하지 않음
	 */
	, INVALID_API_RESPONSE("-00045", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 사용자 계정이 이미 등록됨
	 */
	, USER_ACCOUNT_ALREADY_EXISTS("-00046", HttpStatus.CONFLICT)

	/**
	 * 보험사 API 정보가 없음
	 */
	, INSU_API_NOT_FOUND("-00047", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 잘못된 API 응답 요청
	 */
	, INVALID_API_RESPONSE_BODY_TYPE("-00048", HttpStatus.BAD_REQUEST)

	/**
	 * API의 응답 파라미터가 없음
	 */
	, API_RESPONSE_PARAMS_NOT_FOUND("-00049", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * API 필수 파라미터 값이 없음
	 */
	, API_PARAM_VALUE_NOT_FOUND("-00050", HttpStatus.BAD_REQUEST)

	/**
	 * 인증 실패
	 */
	, AUTHORIZED_FAIL("-00051", HttpStatus.UNAUTHORIZED)

	/**
	 * 환자정보를 찾을 수 없음
	 */
	, PATIENT_INFO_NOT_FOUND("-00052", HttpStatus.NOT_FOUND)

	/**
	 * 제증명서 조회 중 오류 발생
	 */
	, CERTIFICATE_SEARCH_FAIL("-00053", HttpStatus.BAD_REQUEST)

	/**
	 * 제증명서 조회 중 오류 발생
	 */
	, CERTIFICATE_PRODUCT_NOT_FOUND("-00054", HttpStatus.NOT_FOUND)

	/**
	 * 제증명서 상품 준비 중 오류
	 */
	, CERTIFICATE_PRODUCT_PREPARE_FAIL("-00055", HttpStatus.BAD_REQUEST)

	/**
	 * 잘못된 API 메시지
	 */
	, ILLEGA_API_MESSAGE("-00056", HttpStatus.BAD_REQUEST)

	/**
	 * 주문정보 찾기 오류
	 */
	, ORDER_NOT_FOUND("-00057", HttpStatus.NOT_FOUND)

	/**
	 * 발급제증명서 부족
	 */
	, ISSUED_CERT_LACK_FAIL("-00058", HttpStatus.BAD_REQUEST)

	/**
	 * 메일 주소 미입력
	 */
	, MAIL_ADDR_NOT_ENTER("-00059", HttpStatus.BAD_REQUEST)

	/**
	 * 내보내기 암호 미입력
	 */
	, EXPORT_PASSWORD_REQUIRED("-00060", HttpStatus.BAD_REQUEST)

	/**
	 * 내보내기 처리 실패
	 */
	, EXPORT_PROCESS_FAIL("-00061", HttpStatus.BAD_REQUEST)

	/**
	 * 문서확인번호 검증 실패
	 */
	, CERT_DOC_VERIFY_FAIL("-00062", HttpStatus.BAD_REQUEST)

	/**
	 * 문서열람키 정보 실패
	 */
	, CERT_DOC_OPEN_KEY_FAIL("-00063", HttpStatus.BAD_REQUEST)

	/**
	 * 문서열람키 정보 실패
	 */
	, CERT_HOSPITAL_API_CALL_FAIL("-00064", HttpStatus.BAD_REQUEST)

	/**
	 * 결제 진행 중 오류 발생
	 */
	, PAYMENT_TRANSACTION_FAIL("-00065", HttpStatus.BAD_REQUEST)

	/**
	 * 주문 정보 생성 중 오류 발생
	 */
	, CERT_ORDER_CREATE_FAIL("-00066", HttpStatus.BAD_REQUEST)

	/**
	 * 잘못정의된 API
	 */
	, INVALID_API_SPEC("-00067", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 제증명 트랜잭션 메시지
	 */
	, CERT_TRANS_MSG("-00068", HttpStatus.PRECONDITION_FAILED)

	/**
	 * 역할명으로 조회 실패
	 */
	, ROLE_NOT_FOUND("-00069", HttpStatus.BAD_REQUEST)

	/**
	 * 디바이스 식별자 충돌
	 */
	, DEVICE_UUID_CONFLICT("-00070", HttpStatus.BAD_REQUEST)

	/**
	 * 앱을 찾을 수 없음
	 */
	, APP_NOT_FOUND("-00071", HttpStatus.BAD_REQUEST)

	/**
	 * 회원가입을 시도했으나 사용자가 이미 존재함
	 */
	, USER_ALREADY_REGISTERED("-00072", HttpStatus.CONFLICT)

	/**
	 *
	 */
	, INVALID_API_RESPONSE_TYPE("-00073", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 병원정보를 찾을 수 없음
	 */
	, HOSPITAL_NOT_FOUND("-00074", HttpStatus.INTERNAL_SERVER_ERROR)

	/**
	 * 제증명서 열람용 암호를 찾을 수 없음
	 */
	, CERT_PASS_NOT_FOUND("-00075", HttpStatus.NOT_FOUND)

	/**
	 * 헤더정보에 필수값이 없는 경우( ex)Authorization, Bearer accessToken)
	 */
	, INVALID_HEADER("-00076", HttpStatus.UNAUTHORIZED)

	/**
	 * 인증 실패
	 */
	, GRANT_FAILED("-00077", HttpStatus.UNAUTHORIZED)

	/**
	 * 인증 실패
	 */
	, BANK_ACCOUNT_NAME_FAILED("-00068", HttpStatus.BAD_REQUEST)

	/**
	 * 병원 ID 이미 등록됨
	 */
	, HOSPITAL_ID_ALREADY_EXISTS("-00076", HttpStatus.CONFLICT)

	/**
	 * 인증토큰을 찾을 수 없음
	 */
	, AUTH_TOKEN_NOT_FOUND("-00077", HttpStatus.UNAUTHORIZED)

	/**
	 * 잘못된 인증 토큰
	 */
	, INVALID_AUTH_TOKEN("-00078", HttpStatus.BAD_REQUEST)

	/**
	 * 사용자 계정이 없음
	 */
	, USER_ACCOUNT_NOT_FOUND("-00079", HttpStatus.BAD_REQUEST)

	/**
	 * 제증명서 조회 중 오류 발생
	 */
	, TO_CLAIM_TREATMENT_SEARCH_FAIL("-00080", HttpStatus.BAD_REQUEST)
	/**
	 * 제증명서 조회 중 오류 발생
	 */
	, TO_CLAIM_TREATMENT_COUNT_EXCEED("-00081", HttpStatus.BAD_REQUEST)

	/**
	 * 환불시 주문, 청구목록 상태 체크
	 */
	, REFUND_STATUS("-00082", HttpStatus.BAD_REQUEST)

	;

	/**
	 * 에러코드
	 */
	final private String code;

	final private HttpStatus httpStatus;

	/**
	 * 생성자
	 *
	 * @param code
	 */
	ErrorTypes(String code, HttpStatus httpStatus) {
		this.code = code;
		this.httpStatus = httpStatus;
	}

	/**
	 * 코드조회
	 * @return
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * HttpStatus 조회
	 * @return
	 */
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	@Override
	public String toString() {
		return "ErrorTypes{" +
			"code='" + code + '\'' +
			", httpStatus=" + httpStatus +
			"} " + super.toString();
	}
}
