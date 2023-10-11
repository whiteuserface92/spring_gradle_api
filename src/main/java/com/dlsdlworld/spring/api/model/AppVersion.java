package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.event.AppVersionListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AppVersion)
@EntityListeners(AppVersionListener.class)
public class AppVersion extends BaseAppVersion {

    @ManyToOne
    @JoinColumn(name = "app_platform_id")
    private AppPlatform appPlatform;

    @OneToOne(mappedBy = "appVersion", fetch = FetchType.LAZY)
    private AppFile appFile;

}
