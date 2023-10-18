package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseUserPatient;
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
@Table(name = Tables.UserPatient)
public class UserPatient extends BaseUserPatient {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;
}
