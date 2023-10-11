package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 사용자관리
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : kyunghun.kim
 * Date : 2020/07/01
 * Time : 2:28 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserInactiveLog extends BasePersistable {

    /* 사용자id */
    private Long userId;

    /* 비활성종류 */
    @Column(length = Columns.inactiveType)
    private String inactiveType;

    /* 비활성일시 */
    private LocalDateTime inactivedOn;

    /* 활성일시 */
    private LocalDateTime activedOn;

    /* 탈퇴사유코드 */
    @Column(length = Columns.withdrawalType)
    private String withdrawalType;

    /* 탈퇴사유 */
    @Column(length = Columns.withdrawalDesc)
    private String withdrawalDesc;

}
