package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 본인인증이력
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : kyunghun.kim
 * Date : 2020/04/28
 * Time : 18:00 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCiLog extends BasePersistable {

    /**
     * 본인인증방법
     */
    @Column(length = Columns.authMethod, nullable = false)
    private String authMethod;

    /**
     * 인증키
     */
    @Column(length = Columns.authKey, nullable = false)
    private String authKey;

    /**
     * 인증일시
     */
    @Column(nullable = false)
    private LocalDateTime authDttm;

    /**
     * 사용자id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 병원코드
     */
    @Column(name = "hospital_cd", length = Columns.hospitalCd)
    private String hospitalCd;
}
