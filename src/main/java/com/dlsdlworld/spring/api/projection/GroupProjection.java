package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.cache.MessageCache;
import com.dlsdlworld.spring.api.model.Group;
import com.dlsdlworld.spring.api.model.GroupHospital;
import com.dlsdlworld.spring.api.model.Hospital;
import com.dlsdlworld.spring.api.model.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 그룹정보 프로젝션
 */
@Projection(name = "group_projection1", types = {
        Group.class, Hospital.class, Message.class
})
public interface GroupProjection extends BaseModifiableProjection {

    String getGroupNm();

    String getGroupDesc();

    String getNameCd();

    String getServiceUrl();

    String getDispOrd();

    @Value("#{@commonProjector.getMessageCaches(target.nameCd)}")
    List<MessageCache> getNameCds();

    @JsonIgnore
    Set<GroupHospital> getGroupHospitals();

    /**
     * 프로젝터를 통해 병원정보 리스트 조회
     * @return 병원정보 리스트
     */
    default List<Hospital> getHospitals() {
        List<Hospital> hospitals = new ArrayList<>();
        for (GroupHospital groupHospital : getGroupHospitals()) {
            hospitals.add(groupHospital.getHospital());
        }

        return hospitals;
    }

    /**
     * 앱정보 리스트 조회
     * @return 앱정보 리스트
     */
//    List<App> getApps();
}
