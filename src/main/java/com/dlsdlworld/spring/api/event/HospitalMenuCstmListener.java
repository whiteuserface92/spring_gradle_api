package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.cache.HospitalMenuAttr;
import com.dlsdlworld.spring.api.cache.HospitalMenuCstmsCache;
import com.dlsdlworld.spring.api.cache.HospitalMenuCstmsCacheRepository;
import com.dlsdlworld.spring.api.exception.CacheOperationException;
import com.dlsdlworld.spring.api.model.HospitalMenuCstm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 병원별 메뉴 속성 정보 리스너
 * 병원별 속성정보는 따로 조회 될 일은 없고 hospitalMenuId 로 검색하면 나오면 리스트로 받기 위해서 아래와 같이 처리함
 */
@Slf4j
@Component
public class HospitalMenuCstmListener {

    /** 메뉴정보 캐시 저장소*/
    @Resource
    private HospitalMenuCstmsCacheRepository hospitalMenuCstmsCacheRepository;

    /**
     * 메뉴 속성 정보 생성 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostPersist
    public void postPersist(HospitalMenuCstm entity) {
        saveCache(entity);
        log.trace("HospitalMenuCstmListener HosptialMenuCsmt:{} post persist start", entity);
    }

    /**
     * 메뉴 수정 후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(HospitalMenuCstm entity) {
        log.trace("HospitalMenuCstmListener HosptialMenuCsmt:{} post update start", entity);
        saveCache(entity);
    }

    /**
     * 메뉴 삭제 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostRemove
    public void postRemove(HospitalMenuCstm entity) {
        log.trace("HospitalMenuCstmListener HosptialMenuCsmt:{} post remove start", entity);
        try {
            saveCache(entity);
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }


    private HospitalMenuAttr generateHospitalMenuAttr(HospitalMenuCstm entity){
        HospitalMenuAttr hospitalMenuAttr = HospitalMenuAttr.builder()
                .attrKey(entity.getAttrKey())
                .attrVal(entity.getAttrVal())
                .msgCd(entity.getMsgCd()).build();
        return hospitalMenuAttr;
    }
    /**
     * 엔티티 정보를 캐시에 저장
     * @param entity
     * @return
     */
    public HospitalMenuCstmsCache saveCache(HospitalMenuCstm entity) {
        try {
            Optional<HospitalMenuCstmsCache> maybeCache = Optional.ofNullable(hospitalMenuCstmsCacheRepository.findByHospitalMenuId(entity.getHospitalMenu().getId()));
            //기존에 저장된 cache를 삭제한다
            HospitalMenuCstmsCache storedCache = null;
            if (maybeCache.isPresent()){
                HospitalMenuCstmsCache cache = maybeCache.get();
                Set<HospitalMenuAttr> hospitalMenuAttrs = cache.getCustomAttrs();
                hospitalMenuAttrs.add(generateHospitalMenuAttr(entity));
                cache.setCustomAttrs(hospitalMenuAttrs);
                storedCache = hospitalMenuCstmsCacheRepository.save(cache);
            }else{
                Set<HospitalMenuAttr> hospitalMenuAttrs = new HashSet<HospitalMenuAttr>();
                hospitalMenuAttrs.add(generateHospitalMenuAttr(entity));

                final HospitalMenuCstmsCache cache = HospitalMenuCstmsCache.builder()
                        .hospitalMenuId(entity.getHospitalMenu().getId())
                        .customAttrs(hospitalMenuAttrs).build();
                //신규 캐시 메세지 저장
                storedCache = hospitalMenuCstmsCacheRepository.save(cache);
                log.trace("storedCache:{}", storedCache);
            }
            return storedCache;
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    public HospitalMenuCstmsCache saveCache(Long hospitalMenuId, Set<HospitalMenuCstm> entities) {

        try {
            Optional<HospitalMenuCstmsCache> maybeCache = Optional.ofNullable(hospitalMenuCstmsCacheRepository.findByHospitalMenuId(hospitalMenuId));
            //기존에 저장된 cache를 삭제한다
            if (maybeCache.isPresent())
                hospitalMenuCstmsCacheRepository.delete(maybeCache.get());

            Set<HospitalMenuAttr> hospitalMenuAttrs = entities.stream().map(entity -> generateHospitalMenuAttr(entity)).collect(Collectors.toSet());
            final HospitalMenuCstmsCache cache = HospitalMenuCstmsCache.builder().hospitalMenuId(hospitalMenuId).customAttrs(hospitalMenuAttrs).build();
            //신규 캐시 메세지 저장
            HospitalMenuCstmsCache storedCache = hospitalMenuCstmsCacheRepository.save(cache);
            log.trace("storedCache:{}", storedCache);

            return storedCache;
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }
}
