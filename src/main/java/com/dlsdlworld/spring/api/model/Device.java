package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/13
 * Time : 14:49
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
