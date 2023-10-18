package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.converter.ProductTypeConverter;
import com.dlsdlworld.spring.api.types.ProductTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 시스템 접근 로그
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseAppLog extends BasePersistable {

    /**
     * 제품분류
     */
    @Convert(converter = ProductTypeConverter.class)
    @Column(length = Columns.prodCd, nullable = false)
    private ProductTypes prodCd;

    /**
     * 서비스명
     */
    @Column(length = Columns.serviceNm, nullable = false)
    private String serviceNm;

    /**
     * 생성일시
     */
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    /**
     * 서비스IP
     */
    @Column(length = Columns.serviceIp, nullable = false)
    private String serverIp;

    /**
     * 서비스포트
     */
    @Column(nullable = false)
    private Integer serverPort;

    /**
     * 사용자계정
     */
    @Column(length = Columns.userAccnt, nullable = false)
    private String userAccnt;

    /**
     * 요청주소
     */
    @Column(length = Columns.serviceUrl, nullable = false)
    private String reqUrl;

    /**
     * 헤더정보
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String reqHeader;

    /**
     * 요청데이터
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String reqData;

    /**
     * 응답데이터
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String respData;

    /**
     * 처리시간
     */
    @Column(nullable = false)
    private Integer elapsedTime;

    /**
     * trace id
     */
    @Column(length = Columns.traceId, nullable = false)
    private String traceId;

    /**
     * 에러 발생 여부
     */
    @Column(nullable = false)
    private Boolean errorEnabled;

    /**
     * 앱로그 남기는 위치 코드 10.api, 20.UI
     */

    @Column(length = Columns.code)
    private String logCd;

    /**
     * 사용자의 IP
     */

    @Column(length = Columns.userIp)
    private String userIp;
}
