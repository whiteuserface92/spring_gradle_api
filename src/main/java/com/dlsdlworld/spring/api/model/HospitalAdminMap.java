package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseHospitalAdminMap;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.HospitalAdminMap)
public class HospitalAdminMap extends BaseHospitalAdminMap {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAdmin userAdmin;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

}
