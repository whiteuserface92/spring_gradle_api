package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseUserDevice;
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
@Table(name = Tables.UserDevice)
public class UserDevice extends BaseUserDevice {

    /**
     * 사용자
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;
}
