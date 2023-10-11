package com.dlsdlworld.spring.api.cache;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

/**
 * 다국어정보 캐시.
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/11
 * Time : 15:07
 */
@Data
@NoArgsConstructor
@RedisHash("MessageCache")
public final class MessageCache implements Serializable {

    /** 식별자*/
    @Id
    private Long id;

    /** 메세지코드*/
    @Indexed
    private String msgCd;

    /** 언어코드*/
    @Indexed
    private String langCd;

    /** 메세지*/
    private String msgContent;

    @Builder
    public MessageCache(Long id, String msgCd, String langCd, String msgContent) {
        this.id = id;
        this.msgCd = msgCd;
        this.langCd = langCd;
        this.msgContent = msgContent;
    }
}
