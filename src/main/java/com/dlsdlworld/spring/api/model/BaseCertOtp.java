package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 증명일회용암호
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCertOtp extends BasePersistable {

    /**
     * 일회용키
     */
    @Column(length = Columns.otpKey, nullable = false)
    private String otpKey;

    /**
     * 만료일시
     */
    @Column(nullable = false)
    private LocalDateTime expiredOn;

    /**
     * 휴대폰번호
     */
    @Column(length = Columns.phoneNo, nullable = false)
    private String phoneNo;

    /**
     * 사용여부
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 링크url
     */
    @Column(length = Columns.linkUrl)
    private String linkUrl;

}
