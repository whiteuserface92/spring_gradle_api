package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.model.AppPlatform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 */
@Slf4j
@Component
public class AppPlatformListener extends AppCacheListener {



    @PostPersist
    public void postPersist(AppPlatform entity) {
        log.trace("AppPlatformListener:{} post persist start", entity);
        saveCache(entity);
    }

    /**
     * 메뉴 수정 후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(AppPlatform entity) {
        log.trace("AppPlatformListener:{} post update start", entity);
        saveCache(entity);
    }

    /**
     * 메뉴 삭제 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostRemove
    public void postRemove(AppPlatform entity) {
       deleteCache(entity);  
    }


}
