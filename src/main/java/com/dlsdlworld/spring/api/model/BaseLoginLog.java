package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.AuthTypes;
import com.dlsdlworld.spring.api.types.LoginLogTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 로그인이력
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseLoginLog extends BasePersistable {

    /**
     * 기록일시
     */
    @Column(nullable = false)
    private LocalDateTime logedOn;

    /**
     * 인증유형
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.authType, nullable = false)
    private AuthTypes authType;

    /**
     * 로그인로그타입
     */
    @Column(length = Columns.logType, nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginLogTypes loginType;

    /**
     * 사용자계정
     */
    @Column(length = Columns.userAccnt)
    private String userAccnt;
    
    /**
     * 로그인서비스명
     */
    @Column(length = Columns.serviceNm, nullable = false)
    private String serviceNm;

    /**
     * 앱식별자
     */
    @Column
    private Long appId;

    /**
     * 앱버전(이력버전)
     */
    @Column(length = Columns.appVer)
    private String appVer;

    /**
     * 단말기식별자
     */
    @Column(length = Columns.uuid)
    private String deviceUuid;
}
