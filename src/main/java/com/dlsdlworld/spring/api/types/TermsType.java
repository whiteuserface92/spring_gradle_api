package com.dlsdlworld.spring.api.types;

/**
 * 4.0에서 사용하던 Type 정보 복사
 */
public enum TermsType {
	
    /**
     * 가입 14세미만
     */
    JOIN_CRIMINAL_MINORS,

    /**
     * 가입 성인
     */
    JOIN_ADULT,
    
    /**
     * 병원 이용약관 (필수)
     */
    HOSPITAL_TERMS,

    /**
     * 병원 개인정보 수집 및 이용 목적
     */
    HOSPITAL_PERSONAL_INFORMATION_ITEM,

    /**
     * 병원 개인정보 수집 및 이용 (필수)
     */
    HOSPITAL_PERSONAL_INFORMATION,

    /**
     * 개인정보 수집 및 이용 항목
     */
    HOSPITAL_PERSONAL_USE_INFORMATION,

    /**
     * 개인정보 이용 및 보유기간
     */
    HOSPITAL_PERSONAL_USE_RETENTION,

    /**
     * 개인정보 마케팅 활용
     */
    HOSPITAL_PERSONAL_MARKETING,

    /**
     * 병원 개인정보 제 3자 제공
     */
    HOSPITAL_PERSONAL_INFORMATION_THIRD_PARTY,

    /**
     * 전자금융거래 이용
     */
    PAY_USE_INFORMATION,

    /**
     * 전자금융거래(대리결제용)
     */
    PAY_PROXY_INFORMATION,

    /**
     * 전자처방전
     */
    PHARM,

    /**
     * 전자처방전 FAX 전송
     */
    PHARM_FAX,

    /**
     * 환자권리
     */
    PATIENT_RIGHTS,

    /**
     * 환자의무
     */
    PATIENT_OBLIGATIONS,

    /**
     * 환자 권리와 의무
     */
    PATIENT_RIGHTS_OBLIGATIONS,

    /**
     * 환자 동의서 일반
     */
    PATIENT_GENERAL,

    /**
     * 하이패스 결제 일반
     */
    HIPASS_PAY,

    /**
     * 바이오에이지 이용약관
     */
    BIOAGE,

    /**
     * BNK 서비스 약관
     */
    SERVICE_BNK,

    /**
     * PAYFAN 서비스 약관
     */
    SERVICE_PAYFAN,

    /**
     * PAYFAN 개인정보 이용야관
     */
    SERVICE_PAYFAN_PRIVACY,

    /**
     * PAYFAN 3자 정보 제공 동의
     */
    SERVICE_PAYFAN_OTHERS,

    /**
     * 알림톡 비회원 결제 약관 동의
     */
    SERVICE_ANONYMOUS_PAYMENT,

    /**
     * 카카오톡 비회원 약관 동의
     */
    SERVICE_ANONYMOUS_KAKAOTALK,

    /**
     * 보험금 청구를 위한 개인(신용)정보 처리 동의
     */
    INSU_PERSONAL_INFORMATION,

    /**
     * 서비스 이용 약관
     */
    CARE_TERMS,

    /**
     * 고유식별 정보 처리
     */
    CARE_PERSONAL_INFORMATION,

    /**
     * 보험금청구를 위한 진료정보 전송 동의(사용안함)
     */
    INSU_SEND,

    /**
     * 보험금청구를 위한 개인(신용)정보 및 진료정보 제공 => (필수)보험금 청구를 위한 진료정보 제3자 제공 동의
     */
    INSU_OFFER,

    /**
     * 고유식별정보 및 기타정보 처리 동의 => (필수)민감정보 및 진료정보 처리 동의
     */
    INSU_PROCESS,

    /**
     * (선택)마케팅 활용 동의
     */
    CARE_MARKETING,

    /**
     * (필수)개인(신용)정보 수집 및 이용 동의
     */
    INSU_USE,

    /**
     * 고유식별 정보 처리
     */
    HOSPITAL_PATIENT_ID,

    /**
     * (필수)개인(신용)정보 수집 및 이용 동의
     */
    CARE_USE,

    /**
     * (필수)고유식별정보 처리 동의
     */
    INSU_UI_PROCESS,

    /**
     * (필수)민감정보 및 진료정보 처리 동의
     */
    INSU_CERT_PROCESS,

    /**
     * 민감정보 및 진료정보 처리 동의
     */
    CARE_PROCESS,

    /**
     * 고유식별정보 처리 동의
     */
    HOSPITAL_PROCESS

}
