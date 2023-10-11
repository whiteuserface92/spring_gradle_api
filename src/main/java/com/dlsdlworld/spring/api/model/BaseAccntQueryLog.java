package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 *
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : sungjin.park
 * Date : 2020/06/30
 * Time : 7:23 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseAccntQueryLog extends BasePersistable {

    /**
     * 업무구분
     */
    @Column(length = Columns.bizCd, nullable = false)
    private String bizCd;

    /**
     * 은행코드
     */
    @Column(length = Columns.bankCd, nullable = false)
    private String bankCd;

    /**
     * 계좌번호해쉬
     */
    @Column(length = Columns.accntHash, nullable = false)
    private String accntHash;

    /**
     * 예금주 이름
     */
    @Column(length = Columns.ownerNm, nullable = true)
    private String ownerNm;

    /**
     * 조회시작일시
     */
    @Column(nullable = false)
    private LocalDateTime startedOn;

    /**
     * 조회응답일시
     */
    @Column(nullable = true)
    private LocalDateTime endedOn;

    /**
     * 응답코드
     */
    @Column(length = Columns.responseCd)
    private String responseCd;

    /**
     * 사용자ID
     */
    @Column(nullable = false)
    private Long userId;

}
