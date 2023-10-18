package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.types.AppDeploymentTypes;
import com.dlsdlworld.spring.api.types.PlatformTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * 앱관리
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseAppPlatform extends BaseModifiable {

    /**
     * 플랫폼타입
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.platformType, nullable = false)
    private PlatformTypes platformType;

    /**
     * 패키지명
     */
    @Column(length = Columns.pkgNm, nullable = false)
    private String pkgNm;

    /**
     * 배포타입
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.deployType, nullable = false)
    private AppDeploymentTypes deployType;

    /**
     * 해시키
     */
    @Column(length = Columns.hashKey)
    private String hashKey;

    /**
     * 스토어url
     */
    @Column(length = Columns.storeUrl, nullable = false)
    private String storeUrl;

    /**
     * ios 심사여부
     */
    @Column
    private Boolean iosProcessed;

}
