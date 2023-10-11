package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Project lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer ricky
 * Date 2020/01/12
 * Time 7:06 오후
 */
@Getter
@Setter
@Entity
@Table(name = Tables.User)
public class User extends BaseUser {

    @OneToMany(mappedBy = "user")
    private Set<UserAuth> userAuths;

    @OneToMany(mappedBy = "user")
    private Set<UserStaff> userStaffs;

    @OneToMany(mappedBy = "user")
    private Set<UserDevice> userDevices;

//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "user")
    private Set<UserPatient> userPatients;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles;

    @OneToMany(mappedBy = "user")
    private Set<PushRequest> pushRequests;

    @OneToMany(mappedBy = "user")
    private Set<UserMenu> userMenus;

    @OneToMany(mappedBy = "user")
    private Set<UserConfig> userConfigs;
}
