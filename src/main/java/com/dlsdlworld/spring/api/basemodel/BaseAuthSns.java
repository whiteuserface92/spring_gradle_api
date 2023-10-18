package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.types.SnsTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 소셜인증
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseAuthSns extends BasePersistable {

    /**
     * 소셜타입
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.snsKey, nullable = false)
    private SnsTypes snsType;

    /**
     * 소셜계정
     */
    @Column(length = Columns.snsAccnt, nullable = false)
    private String snsAccnt;

    /**
     * 소셜인증키
     */
    @Column(length = Columns.snsKey, nullable = false)
    private String snsKey;

    /**
     * 최근인증일시
     */
    @Column(nullable = false)
    private LocalDateTime loggedOn;
}
