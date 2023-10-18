package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.types.SimpleAuthTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 간편인증
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseAuthSimple extends BasePersistable {

    /**
     * 암호설정일시
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * 간편인증타입
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.simpleAuthType, nullable = false)
    private SimpleAuthTypes simpleAuthType;

    /**
     * 암호
     */
    @Column(length = Columns.simplePwd, nullable = false)
    private String simplePwd;

    /**
     * 간편비밀번호 변경일시
     */
    @Column
    private LocalDateTime pwdChangedOn;

    /**
     * 비밀번호 시도횟수
     */
    @Column
    private Short pwdRetryCnt;

    /**
     * 계정 잠김일시
     */
    @Column
    private LocalDateTime accountLockedOn;

    /**
     * 세브란스의 간편인증 요구사항에 따른 모델링 변경으로 추가됨
     * 2020.05.29 by ricky
     */
    private String userAccnt;
}
