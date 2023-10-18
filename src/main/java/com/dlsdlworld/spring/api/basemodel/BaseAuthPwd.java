package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 비밀번호 인증
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseAuthPwd extends BasePersistable {

    /**
     * 암호알고리즘
     */
    @Column(length = Columns.hashAlgo)
    private String hashAlgo;

    /**
     * 해시솔트
     */
    @Column(length = Columns.hashSalt)
    private String hashSalt;

    /**
     * 암호
     */
    @Column(length = Columns.pwd, nullable = false)
    private String pwd;

    /**
     * 암호변경일시
     */
    @Column
    private LocalDateTime pwdChangedOn;

    /**
     * 로그인시도횟수
     */
    private Short loginRetries;

    /**
     * 병원코드
     */
    @Column(length = Columns.hospitalCd, nullable = false)
    private String hospitalCd;

    /**
     * 사용자계정
     */
    @Column(length = Columns.userAccnt, nullable = false)
    private String userAccnt;

    /**
     * 계정잠금일시
     */
    @Column
    private LocalDateTime accountLockedOn;
}
