//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.AttributeOverride;
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//
///**
// */
//@Getter
//@Setter
//@MappedSuperclass
//@AttributeOverride(name = "id", column = @Column(name = "hospital_id"))
//public abstract class BaseHospitalCert extends BaseModifiable {
//
//    /**
//     * 사용여부
//     */
//    @Column(nullable = false)
//    private Boolean enabled;
//
//    /**
//     * 프린터출력제어여부
//     */
//    @Column(nullable = false)
//    private Boolean prtEnabled;
//
//    /**
//     * 프린터출력매수제한용
//     */
//    @Column(nullable = false)
//    private Short prtCnt;
//
//    /**
//     * 검증URL
//     */
//    @Column(nullable = false, length = Columns.verifyUrl)
//    private String verifyUrl;
//
//    /**
//     * 이메일서버사용여부
//     */
//    @Column(nullable = false)
//    private Boolean mailEnabled;
//
//    /**
//     * 메일서버주소
//     */
//    @Column(length = Columns.mailSvrUrl)
//    private String mailSvrUrl;
//
//    /**
//     * 메일서버포트
//     */
//    private Short mailSvrPort;
//
//    /**
//     * 발신자이메일
//     */
//    @Column(length = Columns.email)
//    private String senderEmail;
//
//    /**
//     * 발신자이메일암호
//     */
//    @Column(length = Columns.pwd)
//    private String senderEmailPwd;
//
//    /**
//     * 메일인증사용여부
//     */
//    @Column(nullable = false)
//    private Boolean mailAuthEnabled;
//
//    /**
//     * 이메일인증사용여부
//     */
//    @Column(nullable = false)
//    private Boolean mailTlsEnabled;
//
//    /**
//     * 모바일팩스사용여부
//     */
//    @Column(nullable = false)
//    private Boolean mobilefaxEnabled;
//
//    /**
//     * 고객코드(모바일앱에서 직접 전송하기위해 3rd party 제품의 고객별 접속코드)
//     */
//    @Column(length = Columns.mobilefaxCustCd)
//    private String mobilefaxCustCd;
//
//    /**
//     * 증명서 발급비용에 대한 결제수수료를 공제하고 정산할 경우 true로 설정함
//     */
//    @Column(nullable = false)
//    private Boolean pgFeeEnabled;
//
//    /**
//     * 파일암호
//     */
//    @Column(length = Columns.filePwd)
//    private String filePwd;
//
//    /**
//     * 인증서암호
//     */
//    @Column(length = Columns.certPwd)
//    private String certPwd;
//
//    /**
//     * PDF파일암호
//     */
//    @Column(length = Columns.pdfPwd)
//    private String pdfPwd;
//
//    /**
//     * PDF서명정책
//     */
//    @Column(length = Columns.pdfSignType)
//    private String pdfSignType;
//
//    /**
//     * 바코드서명정책
//     */
//    @Column(length = Columns.barcode2dSignType)
//    private String barcode2dSignType;
//
//}
