package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 공지사항
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : kyunghun.kim
 * Date : 2020/04/29
 * Time : 12:29 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseNotice extends BaseModifiable {

    /**
     * 제목
     */
    @Column(length = Columns.title, nullable = false)
    private String title;

    /**
     * 공지내용
     */
    @Column(length = Columns.contents, nullable = false)
    private String contents;

    /**
     * 사용여부
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 게시시작일시
     */
    @Column(nullable = false)
    private LocalDateTime strDttm;

    /**
     * 게시종료일
     */
    @Column(nullable = false)
    private LocalDate endDt;

    /**
     * 병원코드
     */
    @Column(length = Columns.hospitalCd, nullable = false)
    private String hospitalCd;

}
