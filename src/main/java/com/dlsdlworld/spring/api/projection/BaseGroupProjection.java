package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.cache.MessageCache;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 */
public interface BaseGroupProjection extends BaseModifiableProjection {

    String getGroupNm();

    String getGroupDesc();

    String getNameCd();

    String getServiceUrl();

    Integer getDispOrd();

    @Value("#{@commonProjector.getMessageCaches(target.nameCd)}")
    List<MessageCache> getNameCds();
}
