package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.cachemodel.HospitalOptionCodeCache;
import com.dlsdlworld.spring.api.cacherepository.HospitalOptionCodeCacheRepository;
import com.dlsdlworld.spring.api.exception.CacheOperationException;
import com.dlsdlworld.spring.api.model.Hospital;
import com.dlsdlworld.spring.api.model.HospitalOptionCode;
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
 * 병원별 옵션 정보 이벤트 핸들러
 */
@Slf4j
@Component
public class HospitalOptionCodeListener implements ApplicationContextAware {

    /** 메뉴정보 캐시 저장소*/
   // @Resource
    private static HospitalOptionCodeCacheRepository hospitalOptionCodeCacheRepository;

  //  @Resource
    private static HospitalRepository hospitalRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        HospitalOptionCodeListener.hospitalOptionCodeCacheRepository = applicationContext.getBean(HospitalOptionCodeCacheRepository.class);
        HospitalOptionCodeListener.hospitalRepository               = applicationContext.getBean(HospitalRepository.class);
    }

    /**
     *  옵션 생성 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostPersist
    public void postPersist(HospitalOptionCode entity) {
        log.trace("HospitalOptionCodeRepository OptionCode:{} post persist start", entity);
        saveCache(entity);
    }

    /**
     *  옵션 수정 후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(HospitalOptionCode entity) {
        log.trace("HospitalOptionCodeRepository OptionCode:{} post update start", entity);
        saveCache(entity);
    }

   /**
     * 옵션 삭제 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostRemove
    public void postRemove(HospitalOptionCode entity) {
        log.trace("HospitalOptionCodeRepository Memu:{} post remove start", entity);

        try {
            hospitalOptionCodeCacheRepository.deleteById(entity.getId());
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

       /**
     *  옵션 삭제 전 발생되는 이벤트
     * @param entity
     */
    @PreRemove
    public void preRemove(HospitalOptionCode entity) {
      /*  if (CollectionUtils.isNotEmpty(entity.getHospital())) {
            throw new LemonConstraintViolationException("hospital.menu");
        }*/
    }

    public Long getCount(){
        return hospitalOptionCodeCacheRepository.count();
    }
    /**
     * 엔티티 정보를 캐시에 저장
     * @param entity
     */
    public void saveCache(HospitalOptionCode entity) {

        try {
            Optional<HospitalOptionCodeCache> maybeCache = hospitalOptionCodeCacheRepository.findById(entity.getId());// .orElseGet(HospitalOptionCodeCache::new);
            //기존에 저장된 cache를 삭제한다
            if (maybeCache.isPresent()) {
                hospitalOptionCodeCacheRepository.deleteById(maybeCache.get().getId());
            }
            final HospitalOptionCodeCache cache = buildCache(entity);
            //신규 캐시 메세지 저장
            HospitalOptionCodeCache storedCache = hospitalOptionCodeCacheRepository.save(cache);
            log.trace("HospitalOptionCodeCache storedCache:{}", storedCache);
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    private HospitalOptionCodeCache buildCache(HospitalOptionCode entity) {
        Optional<Hospital> hopiOpt = hospitalRepository.findById(entity.getHospital().getId());
        String hospitalCd = "";
        if(hopiOpt.isPresent()) {
            hospitalCd = hopiOpt.get().getHospitalCd();
        }

        final HospitalOptionCodeCache cache = HospitalOptionCodeCache.builder()
                .id(entity.getId())
                .optCd(entity.getOptCd())
                .optVal(entity.getOptVal())
                .hospitalId(entity.getHospital().getId())
                .hospitalCd(hospitalCd)
                .build();
        return cache;
    }


    @Transactional
    public int deleteCaches(){
        ArrayList<HospitalOptionCodeCache> cdCaches = (ArrayList<HospitalOptionCodeCache>) hospitalOptionCodeCacheRepository.findAll();
        int retCnt = cdCaches.size();
        for(HospitalOptionCodeCache cdCache:cdCaches){
            hospitalOptionCodeCacheRepository.delete(cdCache);
        }
        return retCnt;
    }
    @Transactional
    public void saveCache(Set<HospitalOptionCode> entities) {
        HashSet<HospitalOptionCodeCache> menuCaches = new HashSet<>();
        for(HospitalOptionCode optCd: entities){
            menuCaches.add(buildCache(optCd));
        }
        hospitalOptionCodeCacheRepository.saveAll(menuCaches);
    }

}
