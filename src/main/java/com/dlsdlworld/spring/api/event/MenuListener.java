package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.cachemodel.MenuCache;
import com.dlsdlworld.spring.api.cacherepository.MenuCacheRepository;
import com.dlsdlworld.spring.api.cachemodel.MessageCache;
import com.dlsdlworld.spring.api.cacherepository.MessageCacheRepository;
import com.dlsdlworld.spring.api.exception.CacheOperationException;
import com.dlsdlworld.spring.api.exception.ConstraintViolationException;
import com.dlsdlworld.spring.api.model.Menu;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 메뉴정보 엔티티 이벤트 핸들러
 */
@Slf4j
@Component
public class MenuListener {

    /** 메뉴정보 캐시 저장소*/
    @Resource
    private MenuCacheRepository menuCacheRepository;

    /** 다국어 캐시 저장소*/
    @Resource
    private MessageCacheRepository messageCacheRepository;

    /**
     * 메뉴 생성 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostPersist
    public void postPersist(Menu entity) {
        log.trace("MessageCacheRepository Memu:{} post persist start", entity);
        saveCache(entity);
    }

    /**
     * 메뉴 수정 후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(Menu entity) {
        log.trace("MessageCacheRepository Memu:{} post update start", entity);
        saveCache(entity);
    }

    /**
     * 메뉴 삭제 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostRemove
    public void postRemove(Menu entity) {
        log.trace("MessageCacheRepository Memu:{} post remove start", entity);

        try {
            menuCacheRepository.deleteById(entity.getId());
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    /**
     * 메뉴 삭제 전 발생되는 이벤트
     * @param entity
     */
    @PreRemove
    public void preRemove(Menu entity) {
        if (CollectionUtils.isNotEmpty(entity.getHospitalMenus())) {
            throw new ConstraintViolationException("hospital.menu");
        }
    }

    public Long getCount(){
        return menuCacheRepository.count();
    }
    /**
     * 엔티티 정보를 캐시에 저장
     * @param entity
     */
    public void saveCache(Menu entity) {

        try {
            Optional<MenuCache> maybeCache = menuCacheRepository.findById(entity.getId());
            //기존에 저장된 cache를 삭제한다
            if (maybeCache.isPresent())
                menuCacheRepository.deleteById(maybeCache.get().getId());

            final Set<MessageCache> nameCds = messageCacheRepository.findByMsgCd(entity.getNameCd());
            final Set<MessageCache> imgUrlCds = messageCacheRepository.findByMsgCd(entity.getImgUrlCd());
            final Set<MessageCache> serviceUrlCds = messageCacheRepository.findByMsgCd(entity.getServiceUrl());
            final Map<String, MessageCache> nameCdMap = nameCds.stream().collect(Collectors.toMap(MessageCache::getLangCd, Function.identity()));
            final Map<String, MessageCache> imgUrlCdMap = imgUrlCds.stream().collect(Collectors.toMap(MessageCache::getLangCd, Function.identity()));
            final Map<String, MessageCache> serviceUrlCdMap = serviceUrlCds.stream().collect(Collectors.toMap(MessageCache::getLangCd, Function.identity()));

            final MenuCache cache = MenuCache.builder()
                    .id(entity.getId())
                    .menuDesc(entity.getMenuDesc())
                    .serviceUrl(entity.getServiceUrl())
                    .nameCd(entity.getNameCd())
                    .nameCds(nameCds)
                    .imgUrlCds(imgUrlCds)
                    .nameCdMap(nameCdMap)
                    .imgUrlCdMap(imgUrlCdMap)
                    .serviceUrlCdMap(serviceUrlCdMap)
                    .build();

            //신규 캐시 메세지 저장
            MenuCache storedCache = menuCacheRepository.save(cache);
            log.trace("storedCache:{}", storedCache);
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    private MenuCache buildMenuCache(Menu entity){
        final Set<MessageCache> nameCds = messageCacheRepository.findByMsgCd(entity.getNameCd());
        final Set<MessageCache> imgUrlCds = messageCacheRepository.findByMsgCd(entity.getImgUrlCd());
        final Set<MessageCache> serviceUrlCds = messageCacheRepository.findByMsgCd(entity.getServiceUrl());
        final Map<String, MessageCache> nameCdMap = nameCds.stream().collect(Collectors.toMap(MessageCache::getLangCd, Function.identity()));
        final Map<String, MessageCache> imgUrlCdMap = imgUrlCds.stream().collect(Collectors.toMap(MessageCache::getLangCd, Function.identity()));
        final Map<String, MessageCache> serviceUrlCdMap = serviceUrlCds.stream().collect(Collectors.toMap(MessageCache::getLangCd, Function.identity()));

        final MenuCache cache = MenuCache.builder()
                .id(entity.getId())
                .menuDesc(entity.getMenuDesc())
                .serviceUrl(entity.getServiceUrl())
                .nameCd(entity.getNameCd())
                .nameCds(nameCds)
                .imgUrlCds(imgUrlCds)
                .nameCdMap(nameCdMap)
                .imgUrlCdMap(imgUrlCdMap)
                .serviceUrlCdMap(serviceUrlCdMap)
                .build();
        return cache;

    }

    @Transactional
    public void deleteCaches(){
        ArrayList<MenuCache> menuCaches = (ArrayList<MenuCache>) menuCacheRepository.findAll();
        for(MenuCache menuCache:menuCaches){
            menuCacheRepository.delete(menuCache);
        }
    }

    @Transactional
    public void saveCache(Set<Menu> entities) {
        HashSet<MenuCache> menuCaches = new HashSet<>();
        for(Menu menu: entities){
            menuCaches.add(buildMenuCache(menu));
        }
        menuCacheRepository.saveAll(menuCaches);
    }

}
