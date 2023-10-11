package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.cache.MessageCache;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;


/**
 */
public interface BaseMenuProjection extends BaseModifiableProjection {

    String getNameCd();

    String getImgUrlCd();

    String getMenuDesc();

    String getServiceUrl();

    @Value("#{@commonProjector.getMessageCaches(target.nameCd)}")
    List<MessageCache> getNameCds();

    @Value("#{@commonProjector.getMessageCaches(target.imgUrlCd)}")
    List<MessageCache> getImgUrlCds();
}
