package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.types.PlatformTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * 사용자단말기
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseDevice extends BaseCreatable {

    /**
     * 기기식별번호
     */
    @Column(length = Columns.uuid, nullable = false)
    private String uuid;

    /**
     * 플랫폼 타입
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.platformType, nullable = false)
    private PlatformTypes platformType;

    /**
     * 버전정보
     */
    @Column(length = Columns.deviceVer, nullable = false)
    private String deviceVer;

    /**
     * 모델명
     */
    @Column(length = Columns.modelNm, nullable = false)
    private String modelNm;


}
