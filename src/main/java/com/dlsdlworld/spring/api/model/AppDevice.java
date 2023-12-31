package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseAppDeivce;
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
@Table(name = Tables.AppDevice)
public class AppDevice extends BaseAppDeivce {

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    private App app;

    /**
     * push_token은 사용자가 존재하기 전에 생성될 수 있으므로 null을 허용한다
     */
    @ManyToOne
    @JoinColumn(name = "user_device_id")
    private UserDevice userDevice;

}
