package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseFidoSet;
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
@Table(name = Tables.FidoSet)
public class FidoSet extends BaseFidoSet {

    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id", nullable = false)
    private Hospital hospital;

}
