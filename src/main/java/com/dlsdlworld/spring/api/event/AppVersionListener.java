package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.model.AppVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : suyeon.you
 * Date : 2020-08-11
 * Time : 오후 12:14
 */
@Slf4j
@Component
public class AppVersionListener extends AppCacheListener {


    @PostPersist
    public void postPersist(AppVersion entity) {
        log.trace("AppVersionListener:{} post persist start", entity);
        saveCache(entity.getAppPlatform());
    }

    /**
     * 메뉴 수정 후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(AppVersion entity) {
        log.trace("AppVersionListener:{} post update start", entity);
        saveCache(entity.getAppPlatform());
    }

    /**
     * 메뉴 삭제 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostRemove
    public void postRemove(AppVersion entity) {

        deleteCache(entity.getAppPlatform());
    }

}
