package com.dlsdlworld.spring.api.baseprojection;

import com.dlsdlworld.spring.api.cachemodel.MessageCache;
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
