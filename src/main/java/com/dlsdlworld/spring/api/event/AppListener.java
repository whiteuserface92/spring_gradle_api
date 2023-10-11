package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.exception.ConstraintViolationException;
import com.dlsdlworld.spring.api.model.App;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 */
@Slf4j
@Component
public class AppListener extends AppCacheListener {


    @PostPersist
    public void postPersist(App entity) {
        log.trace("AppListener:{} post persist start", entity);
        saveCache(entity);
    }

    /**
     * 메뉴 수정 후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(App entity) {
        log.trace("AppListener:{} post update start", entity);
        saveCache(entity);
    }

    /**
     * 메뉴 삭제 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostRemove
    public void postRemove(App entity) {
        if(CollectionUtils.isNotEmpty(entity.getAppPlatforms())){
            throw new ConstraintViolationException("app.platform");
        }
    }

}
