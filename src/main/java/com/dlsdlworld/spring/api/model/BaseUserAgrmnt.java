package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 4:44 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserAgrmnt extends BasePersistable {

    /**
     * 생성일시
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * 동의상태
     */
    @Column(nullable = false)
    private String agreeState;

    @Column(name = "table_nm")
    private String tableNm;

    @Column(name = "ref_id")
    private Long refId;

    @Column(name = "option_val")
    private String optionVal;
}
