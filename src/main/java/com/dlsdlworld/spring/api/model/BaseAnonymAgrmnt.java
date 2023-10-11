package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 비회원약관동의
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/11
 * Time : 20:15
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseAnonymAgrmnt extends BasePersistable {

    /**
     * 약관동의일시
     */
    @Column(nullable = false)
    private LocalDateTime agreedOn;

    /**
     * CI
     */
    @Column(length = Columns.ci, nullable = false)
    private String anonymCi;


    /**
     * 사용자명
     */
    @Column(length = Columns.userNm, nullable = false)
    private String userNm;
}

