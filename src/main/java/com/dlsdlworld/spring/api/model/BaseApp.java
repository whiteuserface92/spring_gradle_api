package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.AppStateTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * 앱관리
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 12:26 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseApp extends BaseModifiable {

    /**
     * 앱이름
     */
    @Column(length = Columns.appNm, nullable = false)
    private String appNm;

    /**
     * 앱단계
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.appState, nullable = false)
    private AppStateTypes appState;

    /**
     * 앱버전
     */
    @Column(length = Columns.appVer, nullable = false)
    private String appVer;

    /**
     * fcm키
     */
    @Column(length = Columns.fcmKey)
    private String fcmKey;

    /**
     * FIDO API 키
     */
    @Column(length = Columns.fidoApiKey)
    private String fidoApiKey;

    /**
     * 다중단말허용여부
     */
    @Column(nullable = false)
    private Boolean multiEnabled;

    /**
     * 제품코드
     */
    @Column(length = Columns.prodCd)
    private String prodCd;

    /**
     * 휴대폰인증 SMS자동완성을 위한 siteCode
     */
    @Column(length = Columns.niceSiteCd)
    private String niceSiteCd;

    /**
     * 휴대폰인증 SMS자동완성을 위한 sitePwd
     */
    @Column(length = Columns.niceSitePwd)
    private String niceSitePwd;
}
