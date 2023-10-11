package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.cache.*;
import com.dlsdlworld.spring.api.exception.CacheNotFoundException;
import com.dlsdlworld.spring.api.exception.CacheOperationException;
import com.dlsdlworld.spring.api.exception.ConstraintViolationException;
import com.dlsdlworld.spring.api.model.HospitalMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 병원메뉴 엔티이 이벤트 헨들러
 */
@Slf4j
@Component
public class HospitalMenuListener {

    /** 병원메뉴 캐시 저장소*/
    @Resource
    private HospitalMenuCacheRepository cachedHospitalMenuRepository;

    /** 다국어 캐시 저장소*/
    @Resource
    private MessageCacheRepository messageCacheRepository;

    /** 메뉴 캐시 저장소*/
    @Resource
    private MenuCacheRepository cachedMenuRepository;

    @Resource
    private HospitalMenuCstmsCacheRepository cachedHospitalMenuCstmsCacheRepository;
    /**
     * 생성 직후 커밋되기 전 발생되는 이벤트
     * @param entity
     */
    @PostPersist
    public void postPersist(HospitalMenu entity) {
        log.trace("HospitalMenuCstmsCacheRepository HospitalMenu:{} post persist start", entity);
        saveCache(entity);
    }

    /**
     * 수정 직후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(HospitalMenu entity) {
        log.trace("HospitalMenuCstmsCacheRepository HospitalMenu:{} post update start", entity);
        saveCache(entity);
    }

    /**
     * 삭제 전 발생되는 이벤트
     * @param entity
     */
    @PreRemove
    public void preRemove(HospitalMenu entity) {
        log.trace("HospitalMenuCstmsCacheRepository HospitalMenu:{} preRemove", entity);
        if (!entity.getChildren().isEmpty())
            throw new ConstraintViolationException("hospitalMenu.children");
    }

    /**
     * 삭제 후 커밋 전 발생되는 이벤트
     * @param entity
     */
    @PostRemove
    public void postRemove(HospitalMenu entity) {
        log.trace("HospitalMenuCstmsCacheRepository HospitalMenu:{} postRemove", entity);
        try {
            cachedHospitalMenuRepository.deleteById(entity.getId());
        } catch (Exception e) {
            throw new CacheOperationException();
        }
    }
    public void saveCache(HospitalMenu hospitalMenu) {
        HospitalMenuCstmsCache hospitalMenuCstmsCache = cachedHospitalMenuCstmsCacheRepository.findByHospitalMenuId(hospitalMenu.getId());
        saveCache(hospitalMenu, hospitalMenuCstmsCache);
    }
    /**
     * 엔티티 정보를 캐시에 저장
     * @param hospitalMenu
     */
    public void saveCache(HospitalMenu hospitalMenu, HospitalMenuCstmsCache hospitalMenuCstmsCache) {
        final HospitalMenuCache cachedHospitalMenu = new HospitalMenuCache();
        cachedHospitalMenu.setId(hospitalMenu.getId());
        cachedHospitalMenu.setProdCd(hospitalMenu.getProdCd());
        cachedHospitalMenu.setHospitalCd(hospitalMenu.getHospital().getHospitalCd());
        cachedHospitalMenu.setParentId(hospitalMenu.getParent() == null ? null : hospitalMenu.getParent().getId());
        cachedHospitalMenu.setMenuType(hospitalMenu.getMenuType());
        cachedHospitalMenu.setLevel(hospitalMenu.getLevel());
        cachedHospitalMenu.setDispOrd(hospitalMenu.getDispOrd());
        cachedHospitalMenu.setOvrdNameCd(hospitalMenu.getOvrdNameCd());
        cachedHospitalMenu.setOvrdServiceUrl(hospitalMenu.getOvrdServiceUrl());
        cachedHospitalMenu.setOvrdImgUrl(hospitalMenu.getOvrdImgUrl());
        cachedHospitalMenu.setEnabled(hospitalMenu.getEnabled());
        // 부서 WhiteList조회후 , 로 분리된 값 저장 2022-02-25 woongjang
        cachedHospitalMenu.setDeptWhiteList(hospitalMenu.getDeptWhiteList());

        cachedHospitalMenu.setRoles(hospitalMenu.getMenuRoles().stream().map(menuRole-> { return menuRole.getRole().getRoleNm();}).collect(Collectors.toSet()));
        cachedHospitalMenu.setPrivileges(hospitalMenu.getMenuRoles().stream().map(menuRole -> {
            return menuRole.getRole().getRolePrivileges().stream().map(
                    rolePrivilege -> {
                        return rolePrivilege.getPrivilege().getPrivilegeNm().getCode();
                    }).collect(Collectors.toSet());
        }).flatMap(Collection::stream).collect(Collectors.toSet()));
        cachedHospitalMenu.setHospitalMenuCstmsCache(hospitalMenuCstmsCache);
        MenuCache cachedMenu = cachedMenuRepository.findById(hospitalMenu.getMenu().getId())
            .orElseThrow(() -> new CacheNotFoundException("CachedMenu:" + hospitalMenu.getMenu().getId()));
        cachedHospitalMenu.setMenu(cachedMenu);
        if (hospitalMenu.getOvrdNameCd() != null)
            cachedHospitalMenu.setOvrdNameCds(messageCacheRepository.findByMsgCd(hospitalMenu.getOvrdNameCd()));

        Optional<MenuCache> maybeCachedMenu = cachedMenuRepository.findById(hospitalMenu.getMenu().getId());
        if (maybeCachedMenu.isPresent())
            cachedHospitalMenu.setMenu(maybeCachedMenu.get());
        else
            throw new CacheNotFoundException("MenuCache:" + hospitalMenu.getMenu().getId());

        cachedHospitalMenuRepository.save(cachedHospitalMenu);
    }
}
