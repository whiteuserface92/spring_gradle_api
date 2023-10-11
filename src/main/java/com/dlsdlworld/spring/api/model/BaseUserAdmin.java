package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 사용자어드민관리
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : hskim
 * Date : 2020/08/11
 * Time : 2:28 오후
 */
@Getter
@Setter
@MappedSuperclass
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public abstract class BaseUserAdmin extends BasePersistable{

    @Column(length = Columns.adminStatus ,nullable = false)
    private String adminStatus;

    @Column(nullable = false)
    private Integer adminLevel;

    @Column(nullable = false)
    private LocalDateTime appliedOn;

    @Column
    private LocalDateTime approvedOn;

}
