package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : seungseuk.park
 * Date : 2020/07/30
 * Time : 4:06 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserBankAccnt extends BasePersistable {

    /**
     * 은행코드
     */
    @Column(nullable = false)
    private String bankCd;

    /**
     * 계좌번호
     */
    @Column(nullable = false)
    private String bankAccnt;

    /**
     * 생성일자
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;
}
