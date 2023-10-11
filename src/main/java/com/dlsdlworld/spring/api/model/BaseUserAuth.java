package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.AuthTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 사용자별인증관리
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserAuth extends BasePersistable {

    /**
     * 최근인증방법
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.lastAuthType, nullable = false)
    private AuthTypes lastAuthType;

    /**
     * 최근로그인일시
     */
    @Column(nullable = false)
    private LocalDateTime lastLoggedOn;
}
