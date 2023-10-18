package com.dlsdlworld.spring.api.cachemodel;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

/**
 */
@Data
@NoArgsConstructor
@RedisHash("LoginLogCache")
public class LoginLogCache {

    /**
     * 식별자 : appPlatform Id
     */
    @Id
    private Long id;

    /**
     * platformType
     */
    @Indexed
    private String platformType;

    /**
     * pkgNm : 패키지 명
     */
    @Indexed
    private String pkgNm;

    /**
     * deployType : 배포 타입
     */
    @Indexed
    private String deployType;


    /**
     * 심사중인지 여부
     */
    private Boolean isProcessed;

    /**
     * 해시키
     */
    private String hashKey;

    /**
     * 스토어 Url
     */
    private String storeUrl;

    @Indexed
    private Long appId;

    @Indexed
    private Long hospitalId;

    private String appNm;

    private String appState;

    private String fcmKey;

    private String fidoApiKey;

    private String versionCd;

    private Boolean isRequired;

    private Boolean multiEnabled;

    private String prodCd;

    private String niceSiteCd;

    private String niceSitePwd;

    private String releaseNote;

    private LocalDateTime releaseOn;


    @Builder
    public LoginLogCache(Long id,
                         Long appId,
                         Long hospitalId,
                         String platformType,
                         String pkgNm,
                         String deployType,
                         String hashKey,
                         String storeUrl,
                         String appNm,
                         String appState,
                         String fcmKey,
                         String fidoApiKey,
                         Boolean multiEnabled,
                         String prodCd,
                         String niceSiteCd,
                         String niceSitePwd,
                         String versionCd,
                         String releaseNote,
                         LocalDateTime releaseOn,
                         Boolean isProcessed,
                         Boolean isRequired) {
        this.id = id;
        this.appId = appId;
        this.hospitalId = hospitalId;
        this.platformType = platformType;
        this.isProcessed = isProcessed;
        this.pkgNm = pkgNm;
        this.deployType = deployType;
        this.hashKey = hashKey;
        this.storeUrl = storeUrl;
        this.appNm = appNm;
        this.appState = appState;
        this.fcmKey = fcmKey;
        this.fidoApiKey = fidoApiKey;
        this.multiEnabled = multiEnabled;
        this.prodCd = prodCd;
        this.niceSiteCd = niceSiteCd;
        this.niceSitePwd = niceSitePwd;
        this.versionCd = versionCd;
        this.releaseNote = releaseNote;
        this.releaseOn = releaseOn;
        this.isRequired = isRequired;
    }
}
