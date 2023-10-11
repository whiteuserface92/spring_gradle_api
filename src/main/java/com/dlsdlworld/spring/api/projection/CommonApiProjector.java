package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.Hospital;
import com.dlsdlworld.spring.api.model.HospitalAdminMap;
import com.dlsdlworld.spring.api.model.IpAccess;
import com.dlsdlworld.spring.api.model.UserAuthPwd;
import com.dlsdlworld.spring.api.repository.HospitalRepository;
import com.dlsdlworld.spring.api.repository.IpAccessRepository;
import com.dlsdlworld.spring.api.repository.UserAuthPwdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**

 */

@Slf4j
@Component
public class CommonApiProjector {

    private HospitalRepository hospitalRepository;

    private IpAccessRepository ipAccessRepository;

    private UserAuthPwdRepository userAuthPwdRepository;

    @Autowired
    public CommonApiProjector(
                          HospitalRepository hospitalRepository,
                          IpAccessRepository ipAccessRepository,
                          UserAuthPwdRepository userAuthPwdRepository) {
        this.hospitalRepository = hospitalRepository;
        this.ipAccessRepository = ipAccessRepository;
        this.userAuthPwdRepository = userAuthPwdRepository;
    }

    public HashSet<Hospital> getAdminHospitals(Integer adminLevel, Set<HospitalAdminMap> hospitalAdminMaps){
        if(adminLevel.compareTo(2) == -1){
            return (HashSet<Hospital>) hospitalRepository.findAll();
        }else{
            HashSet<Hospital> hospitals = new HashSet<>();
            for(HospitalAdminMap hospitalAdminMap : hospitalAdminMaps){
                hospitals.add(hospitalAdminMap.getHospital());
            }
            return hospitals;
        }
    }

    public Set<UserAuthPwd> getUserAuthPwd(Long userId){

        return this.userAuthPwdRepository.findByUserIdAndUserAccntIsNotNull(userId);
    }
    public Set<IpAccess> getAdminIpAccess(Long userId){
        return ipAccessRepository.findAllByUserId(userId);
    }

}
