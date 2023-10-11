package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 개인정보변경이력
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : kyunghun.kim
 * Date : 2020/04/29
 * Time : 12:29 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserChgLog extends BasePersistable {

    /**
     * 사용자ID
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 변경구분코드
     */
    @Column(length = Columns.chgCd, nullable = false)
    private String chgCd;

    /**
     * 발생일시
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * 변경전내용
     */
    @Column(length = Columns.beforeText, nullable = false)
    private String beforeText;

    /**
     * 변경후내용
     */
    @Column(length = Columns.afterText, nullable = false)
    private String afterText;

}
