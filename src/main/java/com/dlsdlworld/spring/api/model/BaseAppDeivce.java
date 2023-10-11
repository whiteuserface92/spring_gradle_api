package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.AuthTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * 앱단말기 정보
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 12:29 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseAppDeivce extends BaseModifiable {

    /**
     * 푸시토큰
     */
    @Column(length = Columns.pushToken)
    private String pushToken;

    /**
     * 최근인증방법
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.lastAuthType, nullable = false)
    private AuthTypes lastAuthType;

}
