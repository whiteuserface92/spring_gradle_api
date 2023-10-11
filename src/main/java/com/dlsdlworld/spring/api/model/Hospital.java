package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Plus 모델링 변경으로 수정
 */
@Getter
@Setter
@Entity
@Table(name = Tables.Hospital)
public class Hospital extends BaseHospital {

    /**
     * menus
     */
    @OneToMany(mappedBy = "hospital")
    private Set<HospitalMenu> hospitalMenus;

    /**
     * groups
     */
    @OneToMany(mappedBy = "hospital")
    private Set<GroupHospital> groupHospitals;

    /**
     * userPatients
     * */
    @OneToMany(mappedBy = "hospital")
    private Set<UserPatient> userPatients;

    @OneToMany(mappedBy = "hospital")
    private Set<FidoSet> fidoSets;

    /**
     * apps
     */
    @OneToMany(mappedBy = "hospital")
    private Set<App> apps;

}
