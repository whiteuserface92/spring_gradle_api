package com.dlsdlworld.spring.api.cache;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 레디스 로그 저장용 객체로 ID 객체는 사용하지 않음
 */
@Data
@NoArgsConstructor
@RedisHash("AppLogCache")
public class AppLogCache implements Serializable {

    /**
     * 식별자 : AppLog Id , server_ip는 있으나, localIp가 없다 이것을 찾을 필요가 있지 않나?
     * clientIp라고 해서 로그 남기는 로직이 필요
     * 다른캐시는 디비에 있는 저장후 캐시가 되나, Log 캐시는 Redis에 입력된 후에 디비로 이관 처리 된다.
     */
    @Id     // ID 객체는 로그라 필요 없음
    private String uuid;

    private String prodCd;

    /**
     * 서비스명
     */
    private String serviceNm;

    /**
     * 생성일시
     */
     private LocalDateTime createdOn;

     /**
     * 서비스IP
     */
    private String serverIp;

    /**
     * 서비스포트
     */
    private Integer serverPort;

    /**
     * 사용자계정
     */
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
