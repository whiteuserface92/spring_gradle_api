package com.dlsdlworld.spring.api.adminApiService;

import com.dlsdlworld.spring.api.dto.UpdateUserAdminDTO;
import com.dlsdlworld.spring.api.exception.EntityNotFoundException;
import com.dlsdlworld.spring.api.model.*;
import com.dlsdlworld.spring.api.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserAdminService {

    private UserRepository userRepository;
    private UserAdminRepository userAdminRepository;
    private IpAccessRepository ipAccessRepository;
    private HospitalAdminMapRepository hospitalAdminMapRepository;
    private UserRoleRepository userRoleRepository;
    private HospitalRepository hospitalRepository;
    private RoleRepository roleRepository;


    @Autowired
    public UserAdminService(UserRepository userRepository, UserAdminRepository userAdminRepository, IpAccessRepository ipAccessRepository, HospitalAdminMapRepository hospitalAdminMapRepository, UserRoleRepository userRoleRepository, HospitalRepository hospitalRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userAdminRepository = userAdminRepository;
        this.ipAccessRepository = ipAccessRepository;
        this.hospitalAdminMapRepository = hospitalAdminMapRepository;
        this.userRoleRepository = userRoleRepository;
        this.hospitalRepository = hospitalRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void updateUserRoles(Set<Long> roleIds, User user){

        /**
         * 왜인지 모르겠으나 user 에 userRoles 셋팅하고 save 하면 연관관계가 재정의 될 줄 알았으나 안됨. 일단 따로 체크해서 넣어줌
         */
        Set<UserRole> oldUserRoles = user.getUserRoles();

        List<UserRole> deleteUserRoles = new ArrayList<>();
        List<UserRole> newUserRoles = new ArrayList<>();

        for(UserRole userRole: oldUserRoles){
            Role role = userRole.getRole();
            if(!roleIds.contains(role.getId())){
                deleteUserRoles.add(userRole);
            }else{
                roleIds.remove(role.getId());
            }
        }

        if(!roleIds.isEmpty()){
            Set<Role> roles = roleRepository.findAllById(roleIds);
            for(Role role : roles){
                UserRole userRole = new UserRole();
                userRole.setRole(role);
                userRole.setUser(user);
                newUserRoles.add(userRole);
            }
        }

        userRoleRepository.deleteAll(deleteUserRoles);
        userRoleRepository.saveAll(newUserRoles);
    }

    @Transactional
    public void updateHospitalAdminMaps(Set<Long> hospitalIds, UserAdmin userAdmin){
        Set<HospitalAdminMap> oldHospitalAdminMaps = userAdmin.getHospitalAdminMaps();
        ArrayList<HospitalAdminMap> addHospitalAdminMaps = new ArrayList<>();
        ArrayList<HospitalAdminMap> deleteHospitalAdminMaps = new ArrayList<>();

        for(HospitalAdminMap hospitalAdminMap: oldHospitalAdminMaps){
            Hospital hospital = hospitalAdminMap.getHospital();
            if(!hospitalIds.contains(hospital.getId())){
                deleteHospitalAdminMaps.add(hospitalAdminMap);
            }else{
                hospitalIds.remove(hospital.getId());
            }
        }

        hospitalAdminMapRepository.deleteAll(deleteHospitalAdminMaps);

        if(!hospitalIds.isEmpty()){
            Set<Hospital> hospitalSet = hospitalRepository.findAllById(hospitalIds);
            for(Hospital hospital: hospitalSet){
                HospitalAdminMap hospitalAdminMap = new HospitalAdminMap();
                hospitalAdminMap.setUserAdmin(userAdmin);
                hospitalAdminMap.setHospital(hospital);
                addHospitalAdminMaps.add(hospitalAdminMap);
            }
        }
        hospitalAdminMapRepository.saveAll(addHospitalAdminMaps);
    }


    @Transactional
    public UserAdmin updateAdmin(Long id, UpdateUserAdminDTO updateAdminParam) {

        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User", id));

        UserAdmin userAdmin = userAdminRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UserAdmin", id));


        /**
         * 역할 수정
         */
        updateUserRoles(updateAdminParam.getRoleIds(), user);


        Integer updateAdminLevel = updateAdminParam.getLevel();

        /**
         * 병원 매핑 수정
         */
        if(updateAdminLevel > 1){
           if(null != updateAdminParam.getHospitalIds()){
                updateHospitalAdminMaps(updateAdminParam.getHospitalIds(), userAdmin);
           }
        }else{
            hospitalAdminMapRepository.deleteAll(userAdmin.getHospitalAdminMaps());
        }


        /**
         * 다른 userAdmin 컬럼 수정 일단은 level만 나중에 추가 될 수 있음.
         */
        userAdmin.setAdminLevel(updateAdminLevel);
        return userAdminRepository.save(userAdmin);


    }


}
