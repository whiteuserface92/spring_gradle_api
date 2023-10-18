package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseDevice;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = Tables.Device)
public class Device extends BaseDevice {

    /**
     * 사용자 단말기
     */
    @OneToMany(mappedBy = "device")
    private Set<UserDevice> userDevices;

}
