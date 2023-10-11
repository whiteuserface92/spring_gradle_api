package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.App;
import com.dlsdlworld.spring.api.model.Group;
import com.dlsdlworld.spring.api.model.GroupHospital;
import com.dlsdlworld.spring.api.model.Hospital;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * 병원정보 프로젝션

 */
@Projection(name = "hospital_projection1", types = {
        Hospital.class, App.class, Group.class
})
public interface HospitalProjection {

//    List<PhoneBook> getPhoneBooks();

//    List<Terms> getTermses();

    /**
     * 앱정보 리스트
     * @return
     */
    List<App> getApps();

    /**
     * 그룹정보 리스트
     * @return
     */
    List<GroupHospital> getGroups();

}
