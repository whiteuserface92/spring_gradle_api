package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseApp;
import com.dlsdlworld.spring.api.event.AppListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Plus 모델링 변경으로 수정
 */
@Getter
@Setter
@Entity
@Table(name = Tables.App)
@EntityListeners(AppListener.class)
public class App extends BaseApp {

    @OneToMany(mappedBy = "app")
    private Set<UserAuth> userAuths;

    /**
     * 병원
     */
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    /**
     * 메뉴역할
     */
    @OneToMany(mappedBy = "role")
    private Set<MenuRole> menuRoles;

    /**
     * 푸시요청
     */
    @OneToMany(mappedBy = "app")
    private Set<PushRequest> appPushRequests;

    @OneToMany(mappedBy = "app")
    private Set<AppDevice> appDevices;

    @OneToMany(mappedBy = "app")
    private Set<AppPlatform> appPlatforms;

}
