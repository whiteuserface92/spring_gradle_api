package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.event.AppPlatformListener;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AppPlatform)
@EntityListeners(AppPlatformListener.class)
public class AppPlatform extends BaseAppPlatform {
    /**
     * 앱
     */
    @ManyToOne
    @JoinColumn(name = "app_id")
    private App app;

    @OrderBy("version_cd DESC")
    @OneToMany(mappedBy = "appPlatform")
    private Set<AppVersion> appVersions;

}
