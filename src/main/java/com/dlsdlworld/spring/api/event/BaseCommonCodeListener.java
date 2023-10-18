package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.cachemodel.CommonCodeCache;
import com.dlsdlworld.spring.api.cacherepository.CommonCodeCacheRepository;
import com.dlsdlworld.spring.api.exception.CacheOperationException;
import com.dlsdlworld.spring.api.basemodel.BaseCommonCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import java.util.Optional;

/**
 * 메뉴정보 엔티티 이벤트 핸들러
 */
@Slf4j
@Component
public class BaseCommonCodeListener {

    @Resource
    private CommonCodeCacheRepository cachedCommonCodeRepository;

    /**
     * CommonCode 생성 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostPersist
    public void postPersist(BaseCommonCode entity) {
        log.trace("BaseCommonCode:{} post persist start", entity);
        saveCache(entity);
    }

    /**
     * CommonCode 수정 후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(BaseCommonCode entity) {
        log.trace("BaseCommonCode:{} post update start", entity);
        saveCache(entity);
    }

    /**
     * CommonCode 삭제 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostRemove
    public void postRemove(BaseCommonCode entity) {
        log.trace("BaseCommonCode:{} post remove start", entity);

        try {
            cachedCommonCodeRepository.deleteById(entity.getId());
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    /**
     * CommonCode 전 발생되는 이벤트
     * @param entity
     */
    @PreRemove
    public void preRemove(BaseCommonCode entity) {
//        if (StringUtils.isNotBlank(entity.getRefVal())) {
//            throw new LemonConstraintViolationException();
//        }
    }

    /**
     * 엔티티 정보를 캐시에 저장
     * @param entity
     */
    public void saveCache(BaseCommonCode entity) {

        try {
            Optional<CommonCodeCache> maybeCache = cachedCommonCodeRepository.findById(entity.getId());
            //기존에 저장된 cache를 삭제한다
            if (maybeCache.isPresent())
                cachedCommonCodeRepository.delete(maybeCache.get());

            //신규 캐시 메세지 저장
            final CommonCodeCache cache = CommonCodeCache.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .codeCls(entity.getCodeCls())
                .codeDesc(entity.getCodeDesc())
                .codeNm(entity.getCodeNm())
                .codeNmEng(entity.getCodeNmEng())
                .codeType(entity.getCodeType())
                .dispOrd(entity.getDispOrd())
                .refVal(entity.getRefVal())
                .build();
            CommonCodeCache storedCache = cachedCommonCodeRepository.save(cache);
            log.trace("storedCache:{}", storedCache);
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    @Transactional
    public void deleteCacheAll(){
        cachedCommonCodeRepository.deleteAll();
    }
    @Transactional
    public void deleteCacheByCodeCls(String codeCls){
        cachedCommonCodeRepository.deleteAll(cachedCommonCodeRepository.findByCodeCls(codeCls));
    }
}
