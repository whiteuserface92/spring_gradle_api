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
@Table(name = Tables.UserAdmin)
public class UserAdmin extends BaseUserAdmin {

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(mappedBy = "userAdmin")
    private Set<HospitalAdminMap> hospitalAdminMaps;

}
