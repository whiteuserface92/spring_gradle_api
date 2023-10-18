package com.dlsdlworld.spring.api.baseprojection;

import com.dlsdlworld.spring.api.cachemodel.MessageCache;
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
