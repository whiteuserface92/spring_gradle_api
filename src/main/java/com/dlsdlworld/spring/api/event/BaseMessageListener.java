package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.cachemodel.MessageCache;
import com.dlsdlworld.spring.api.cacherepository.MessageCacheRepository;
import com.dlsdlworld.spring.api.exception.CacheOperationException;
import com.dlsdlworld.spring.api.basemodel.BaseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;

/**
 */
@Slf4j
@Component
public class BaseMessageListener {

    @Resource
    private MessageCacheRepository messageCacheRepository;

    /**
     * BaseMessage 생성 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostPersist
    public void postPersist(BaseMessage entity) {
        log.trace("Message:{} post persist start", entity);
        saveCache(entity);
    }

    /**
     * BaseMessage 수정 후 발생되는 이벤트
     * @param entity
     */
    @PostUpdate
    public void postUpdate(BaseMessage entity) {
        log.trace("Message:{} post update start", entity);
        saveCache(entity);
    }

    /**
     * BaseMessage 삭제 후 발생되는 이벤트 처리
     * @param entity
     */
    @PostRemove
    public void postRemove(BaseMessage entity) {
        log.trace("Message:{} post remove start", entity);

        try {
            messageCacheRepository.deleteById(entity.getId());
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    public void saveCache(BaseMessage entity) {

        try {
            Optional<MessageCache> maybeCache = messageCacheRepository.findById(entity.getId());
            //기존에 저장된 cache를 삭제한다
            if (maybeCache.isPresent())
                messageCacheRepository.delete(maybeCache.get());

            final MessageCache cache = MessageCache.builder()
                    .id(entity.getId())
                    .msgCd(entity.getMsgCd())
                    .langCd(entity.getLangCd())
                    .msgContent(entity.getMsgContent())
                    .build();

            //신규 캐시 메세지 저장
            MessageCache storedCache = messageCacheRepository.save(cache);
            log.trace("storedCache:{}", storedCache);
        } catch (Exception e) {
            throw new CacheOperationException(e);
        }
    }

    @Transactional
    public void deleteCaches(){
        messageCacheRepository.deleteAll();
    }

    @Transactional
    public void deleteCaches(Iterable<BaseMessage> entities){
        HashSet<MessageCache> messageCaches = new HashSet<>();
        for(BaseMessage entity: entities){
            Optional.of(messageCacheRepository.findById(entity.getId())).ifPresent(messageCache->{
                messageCaches.add(messageCache.get());
            });
        }
        messageCacheRepository.deleteAll(messageCaches);
    }

    @Transactional
    public void saveCaches(Iterable<BaseMessage> entities){
        HashSet<MessageCache> messageCaches = new HashSet<>();
        for(BaseMessage entity: entities){
            messageCaches.add(MessageCache.builder()
                    .id(entity.getId())
                    .msgCd(entity.getMsgCd())
                    .langCd(entity.getLangCd())
                    .msgContent(entity.getMsgContent())
                    .build());
        }
        messageCacheRepository.saveAll(messageCaches);
    }

}
