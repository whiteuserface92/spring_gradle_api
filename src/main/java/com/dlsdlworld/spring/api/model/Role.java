package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.Role)
public class Role extends BaseRole {

    @OneToMany(mappedBy = "role")
    private Set<RolePrivilege> rolePrivileges;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<MenuRole> menuRoles;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;
}
