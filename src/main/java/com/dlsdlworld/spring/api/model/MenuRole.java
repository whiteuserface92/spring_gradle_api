package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseMenuRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
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
