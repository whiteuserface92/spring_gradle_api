package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 7:06 오후
 */
@Getter
@Setter
@Entity
@Table(name = Tables.UserStaff)
public class UserStaff extends BaseUserStaff {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 관리자 조회 시 사용
     */
    @Transient
    private Set<Role> Roles;
}
