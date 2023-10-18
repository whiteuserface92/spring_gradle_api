package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BasePrivilege;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.Privilege)
public class Privilege extends BasePrivilege {

    @OneToMany(mappedBy = "privilege")
    private Set<RolePrivilege> rolePrivileges;
}
