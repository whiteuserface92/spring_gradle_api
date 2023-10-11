package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.cache.HospitalOptSiteMapCache;
import com.dlsdlworld.spring.api.cache.HospitalOptSiteMapCacheRepository;
import com.dlsdlworld.spring.api.exception.CacheOperationException;
import com.dlsdlworld.spring.api.model.Hospital;
import com.dlsdlworld.spring.api.model.HospitalOptSiteMap;
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
 * 병원별 옵션 사이트 맵 핸들러
 */
@Slf4j
@Component
public class HospitalOptSiteMapListener implements ApplicationContextAware {

    /** 메뉴정보 캐시 저장소*/
    // @Resource
    private static HospitalOptSiteMapCacheRepository hospitalOptSiteMapCacheRepository;

    //  @Resource
    private static HospitalRepository hospitalRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        HospitalOptSiteMapListener.hospitalOptSiteMapCacheRepository = applicationContext.getBean(HospitalOptSiteMapCacheRepository.class);
        HospitalOptSiteMapListener.hospitalRepository               = applicationContext.getBean(HospitalRepository.class);
    }

    /**
     *  옵션 사이트 맵 생성 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostPersist
    public void postPersist(HospitalOptSiteMap entity) {
        log.trace("HospitalOptSiteMapRepository OptSiteMap:{} post persist start", entity);
        saveCache(entity);
    }

    /**
     *  옵션 사이트 맵 수정 후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(HospitalOptSiteMap entity) {
        log.trace("HospitalOptSiteMapRepository OptSiteMap:{} post update start", entity);
        saveCache(entity);
    }

    /**
     * 옵션 삭제 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostRemove
    public void postRemove(HospitalOptSiteMap entity) {
        log.trace("HospitalOptSiteMapRepository Memu:{} post remove start", entity);

        try {
            hospitalOptSiteMapCacheRepository.deleteById(entity.getId());
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    /**
     *  옵션 삭제 전 발생되는 이벤트
     * @param entity
     */
    @PreRemove
    public void preRemove(HospitalOptSiteMap entity) {
      /*  if (CollectionUtils.isNotEmpty(entity.getHospital())) {
            throw new LemonConstraintViolationException("hospital.menu");
        }*/
    }

    public Long getCount(){
        return hospitalOptSiteMapCacheRepository.count();
    }
    /**
     * 엔티티 정보를 캐시에 저장
     * @param entity
     */
    public void saveCache(HospitalOptSiteMap entity) {

        try {
            Optional<HospitalOptSiteMapCache> maybeCache = hospitalOptSiteMapCacheRepository.findById(entity.getId());// .orElseGet(HospitalOptSiteMapCache::new);
            //기존에 저장된 cache를 삭제한다
            if (maybeCache.isPresent()) {
                hospitalOptSiteMapCacheRepository.deleteById(maybeCache.get().getId());
            }
            final HospitalOptSiteMapCache cache = buildCache(entity);
            //신규 캐시 메세지 저장
            HospitalOptSiteMapCache storedCache = hospitalOptSiteMapCacheRepository.save(cache);
            log.trace("HospitalOptSiteMapCache storedCache:{}", storedCache);
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    private HospitalOptSiteMapCache buildCache(HospitalOptSiteMap entity) {
        Optional<Hospital> hopiOpt = hospitalRepository.findById(entity.getHospitalId());
        String hospitalCd = "";
        if(hopiOpt.isPresent()) {
            hospitalCd = hopiOpt.get().getHospitalCd();
        }

        final HospitalOptSiteMapCache cache = HospitalOptSiteMapCache.builder()
                .id(entity.getId())
                .refCd(entity.getRefCd())
                .defCd(entity.getDefCd())
                .siteCd(entity.getSiteCd())
                .hospitalId(entity.getHospitalId())
                .hospitalCd(hospitalCd)
                .build();
        return cache;
    }


    @Transactional
    public int deleteCaches(){
        ArrayList<HospitalOptSiteMapCache> cdCaches = (ArrayList<HospitalOptSiteMapCache>) hospitalOptSiteMapCacheRepository.findAll();
        int retCnt = cdCaches.size();
        for(HospitalOptSiteMapCache cdCache:cdCaches){
            hospitalOptSiteMapCacheRepository.delete(cdCache);
        }
        return retCnt;
    }
    @Transactional
    public void saveCache(Set<HospitalOptSiteMap> entities) {
        HashSet<HospitalOptSiteMapCache> menuCaches = new HashSet<>();
        for(HospitalOptSiteMap optCd: entities){
            menuCaches.add(buildCache(optCd));
        }
        hospitalOptSiteMapCacheRepository.saveAll(menuCaches);
    }

}
