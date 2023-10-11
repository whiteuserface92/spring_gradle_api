package com.dlsdlworld.spring.api.cache;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 다국어정보 저장소
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/11
 * Time : 15:06
 */
@Repository
public interface MessageCacheRepository extends PagingAndSortingRepository<MessageCache, Long> {

    /**
     * 메세지코드와 언어코드로 메세지 조회.
     * @param msgCd
     * @param langCd
     * @return
     */
    MessageCache findByMsgCdAndLangCd(String msgCd, String langCd);


    /**
     * 메세지 코드로 메세지 리스트 조회
     * @param msgCd
     * @return
     */
    Set<MessageCache> findByMsgCd(String msgCd);
}
