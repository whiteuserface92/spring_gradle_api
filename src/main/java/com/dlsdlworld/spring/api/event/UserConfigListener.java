package com.dlsdlworld.spring.api.event;


import com.dlsdlworld.spring.api.cache.UserConfigCache;
import com.dlsdlworld.spring.api.cache.UserConfigCacheRepository;
import com.dlsdlworld.spring.api.exception.CacheOperationException;
import com.dlsdlworld.spring.api.model.UserConfig;
import com.dlsdlworld.spring.api.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * 사용자 환경 설정 맵 핸들러
 */
@Slf4j
@Component
public class UserConfigListener implements ApplicationContextAware {

    /** 사용자 환경 설정 캐시 저장소*/
    // @Resource
    private static UserConfigCacheRepository userConfigCacheRepository;

    //  @Resource
    private static HospitalRepository hospitalRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        UserConfigListener.userConfigCacheRepository = applicationContext.getBean(UserConfigCacheRepository.class);
        UserConfigListener.hospitalRepository               = applicationContext.getBean(HospitalRepository.class);
    }

    /**
     *  사용자 환경 설정 생성 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostPersist
    public void postPersist(UserConfig entity) {
        log.trace("userConfigCacheRepository OptSiteMap:{} post persist start", entity);
        saveCache(entity);
    }

    /**
     *  사용자 환경 설정 수정 후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(UserConfig entity) {
        log.trace("userConfigCacheRepository OptSiteMap:{} post update start", entity);
        saveCache(entity);
    }

    /**
     * 사용자 환경 설정 삭제 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostRemove
    public void postRemove(UserConfig entity) {
        log.trace("userConfigCacheRepository Memu:{} post remove start", entity);
        try {
            userConfigCacheRepository.deleteById(entity.getId());
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    /**
     *  옵션 삭제 전 발생되는 이벤트
     * @param entity
     */
    @PreRemove
    public void preRemove(UserConfig entity) {
      /*  if (CollectionUtils.isNotEmpty(entity.getHospital())) {
            throw new LemonConstraintViolationException("hospital.menu");
        }*/
    }

    public Long getCount(){
        return userConfigCacheRepository.count();
    }
    /**
     * 엔티티 정보를 캐시에 저장
     * @param entity
     */
    public void saveCache(UserConfig entity) {

        try {
            Optional<UserConfigCache> maybeCache = userConfigCacheRepository.findById(entity.getId());// .orElseGet(HospitalOptSiteMapCache::new);
            //기존에 저장된 cache를 삭제한다
            if (maybeCache.isPresent()) {
                userConfigCacheRepository.deleteById(maybeCache.get().getId());
            }
            final UserConfigCache cache = buildCache(entity);
            //신규 캐시 메세지 저장
            UserConfigCache storedCache = userConfigCacheRepository.save(cache);
            log.trace("userConfigCacheRepository storedCache:{}", storedCache);
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    private UserConfigCache buildCache(UserConfig entity) {

        String myCi = entity.getUser().getMyCi();
        Long userId = entity.getUser().getId();

        log.info("UserConfigCache buildCache myCi:{}, userId:{}", myCi, userId);
        final UserConfigCache cache = UserConfigCache.builder()
                .id(entity.getId())
                .deptCd(entity.getDeptCd())
                .langCd(entity.getLangCd())
                .pushOnOff(entity.getPushOnOff())  // 푸시방해금지여부
                .alarmOffStarttime(entity.getAlarmOffStarttime())
                .alarmOffEndtime(entity.getAlarmOffEndtime())
                .sessTimeoutMm(entity.getSessTimeoutMm()) // 기본 30분
                .myCi(myCi)
                .userId(userId)
                .build();
        return cache;
    }



    @Transactional
    public int deleteCaches(){
        ArrayList<UserConfigCache> cdCaches = (ArrayList<UserConfigCache>) userConfigCacheRepository.findAll();
        int retCnt = cdCaches.size();
        for(UserConfigCache cdCache:cdCaches){
            userConfigCacheRepository.delete(cdCache);
        }
        return retCnt;
    }
    @Transactional
    public void saveCache(Set<UserConfig> entities) {
        HashSet<UserConfigCache> menuCaches = new HashSet<>();
        for(UserConfig entity: entities){
            menuCaches.add(buildCache(entity));
        }
        userConfigCacheRepository.saveAll(menuCaches);
    }

}
