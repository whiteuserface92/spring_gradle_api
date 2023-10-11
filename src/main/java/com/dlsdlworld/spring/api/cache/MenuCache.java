package com.dlsdlworld.spring.api.cache;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 메뉴정보 캐시
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/29
 * Time : 13:56
 */
@Data
@NoArgsConstructor
@RedisHash("MenuCache")
public final class MenuCache implements Serializable {

    @Id
    private Long id;

    /** 메뉴 설명*/
    private String menuDesc;

    /** 서비스주소*/
    private String serviceUrl;

    /** 메뉴명 코드*/
    @Indexed
    private String nameCd;

    /** 이름 다국어정보 리스트*/
    @Reference
    private Set<MessageCache> nameCds;

    /** 이미지주소 다국어정보 리스트*/
    @Reference
    private Set<MessageCache> imgUrlCds;

    /** 이름 다국어정보 리스트*/
    private Map<String, MessageCache> nameCdMap;

    /** 이미지주소 다국어정보 리스트*/
    private Map<String, MessageCache> imgUrlCdMap;

    /** 서비스 주소 다국어정보 리스트 */
    private Map<String, MessageCache> serviceUrlCdMap;

    /**
     * 생성자
     * @param id
     * @param menuDesc
     * @param serviceUrl
     * @param nameCd
     * @param nameCds
     * @param imgUrlCds
     */
    @Builder
    public MenuCache(Long id,
                     String menuDesc,
                     String serviceUrl,
                     String nameCd,
                     Set<MessageCache> nameCds,
                     Set<MessageCache> imgUrlCds,
                     Map<String, MessageCache> nameCdMap,
                     Map<String, MessageCache> imgUrlCdMap,
                     Map<String, MessageCache> serviceUrlCdMap) {
        this.id = id;
        this.menuDesc = menuDesc;
        this.serviceUrl = serviceUrl;
        this.nameCd = nameCd;
        this.nameCds = nameCds;
        this.imgUrlCds = imgUrlCds;
        this.nameCdMap = nameCdMap;
        this.imgUrlCdMap = imgUrlCdMap;
        this.serviceUrlCdMap = serviceUrlCdMap;
    }
}
