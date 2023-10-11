package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 7:03 오후
 */
@Getter
@Setter
@Entity
@Table(name = Tables.RoleMenu)
public class MenuRole extends BaseMenuRole {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalMenuId")
    private HospitalMenu hospitalMenu;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;


}
