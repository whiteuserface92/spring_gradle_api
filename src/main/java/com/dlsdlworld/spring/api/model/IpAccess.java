package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = Tables.IpAccess)
public class IpAccess extends BaseIpAccess {

    /**
     * 사용자
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
