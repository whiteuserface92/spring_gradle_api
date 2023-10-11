package com.dlsdlworld.spring.api.model;

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
@Table(name = Tables.Group)
public class Group extends BaseGroup {

    @OneToMany(mappedBy = "group")
    private Set<GroupHospital> groupHospitals;

}
