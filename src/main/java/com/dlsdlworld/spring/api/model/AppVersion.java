package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.event.AppVersionListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : hskim
 * Date : 2020/07/06
 * Time : 17:46 오후
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
