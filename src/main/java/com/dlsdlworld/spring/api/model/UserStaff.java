package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
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
