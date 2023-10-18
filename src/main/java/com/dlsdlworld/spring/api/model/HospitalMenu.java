package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseHospitalMenu;
import com.dlsdlworld.spring.api.event.HospitalMenuListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 */
@Getter
@Setter
@Entity
@EntityListeners(HospitalMenuListener.class)
@Table(name = Tables.HospitalMenu)
public class HospitalMenu extends BaseHospitalMenu {

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private HospitalMenu parent;


    @OneToMany(mappedBy = "hospitalMenu")
    private Set<MenuRole> menuRoles = new HashSet<MenuRole>();

    @OrderBy("disp_ord ASC")
    @OneToMany(mappedBy = "parent")
    private Set<HospitalMenu> children;


    @OneToMany(mappedBy = "hospitalMenu")
    private Set<HospitalMenuCstm> hospitalMenuCstms = new HashSet<HospitalMenuCstm>();
}
