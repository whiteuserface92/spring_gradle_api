package com.dlsdlworld.spring.api.param;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Slf4j
//@Builder
public class AppLogCacheParam {

    @NotEmpty
    private String prodCd;

    /**
     * 서비스명
     */
    @NotEmpty
    private String serviceNm;

    /**
     * 생성일시
     */
     private LocalDateTime createdOn;

     /**
     * 서비스IP
     */
     @NotEmpty
    private String serverIp;

    /**
     * 서비스포트
     */
    private Integer serverPort;

    /**
     * 사용자계정
     */
    @NotEmpty
    private String userAccnt;

    /**
     * 요청주소
     */
     private String reqUrl;

     /**
     * 헤더정보
     */
    private String reqHeader;

    /**
     * 요청데이터
     */
    private String reqData;

    /**
     * 응답데이터
     */
    private String respData;

    /**
     * 처리시간
     */
    private Integer elapsedTime;

    /**
     * trace id
     */
    private String traceId;

    /**
     * 에러 발생 여부
     */

    private Boolean errorEnabled;


    private Long userId;

    /**
     * 20
     * 앱로그 남기는 위치 코드 10.api, 20.UI , 30.QAB
     */
    private String logCd;

    /**
     * 사용자의 IP 40
     */
    private String userIp;

}
