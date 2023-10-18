package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.cachemodel.MessageCache;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 */
public interface BaseHospitalProjection extends BaseModifiableProjection {

    String getHospitalCd();

    String getHospitalNmCd();

    String getHospitalAddrCd();

    String getHospitalTelnoCd();

    String getServiceIp();

    String getServiceUrl();

    String getApiUrl();

    String getImgUrl();

    Boolean getEnabled();

    Boolean getFidoEnabled();

    String getHospitalType();

    String getMemo();

    String getLocCd();

    @Value("#{@commonProjector.getMessageCaches(target.hospitalNmCd)}")
    List<MessageCache> getHospitalNmCds();

    @Value("#{@commonProjector.getMessageCaches(target.locCd)}")
    List<MessageCache> getLocCds();

    @Value("#{@commonProjector.getMessageCaches(target.hospitalAddrCd)}")
    List<MessageCache> getHospitalAddrCds();

    @Value("#{@commonProjector.getMessageCaches(target.hospitalTelnoCd)}")
    List<MessageCache> getHospitalTelnoCds();
}
