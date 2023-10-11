package com.dlsdlworld.spring.api.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.dlsdlworld.spring.api.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 관리자 조회용 projection

 */
@Projection(name = "user_admin_role_projection", types = {  UserAdmin.class, User.class, Role.class, HospitalAdminMap.class})
public interface UserAdminProjection {

    Long getId();

    String getAdminStatus();

    Integer getAdminLevel();

    LocalDateTime getAppliedOn();

    LocalDateTime getApprovedOn();

    @JsonIgnore
    User getUser();

    @Value("#{target.user.userNm}")
    String getUserNm();

    @Value("#{target.user.email}")
    String getEmail();

    @Value("#{target.user.hospitalCd}")
    String getHospitalCd();

    @Value("#{@commonApiProjector.getUserAuthPwd(target.user.id)}")
    Set<UserAuthPwd> getUserAuthPwd();

    // 맵정보를 무시한다.
    @JsonIgnore
    Set<HospitalAdminMap> getHospitalAdminMaps();

    @Value("#{@commonApiProjector.getAdminHospitals(target.adminLevel, target.hospitalAdminMaps)}")
    Set<HospitalSimpleProjection> getHospitals();

    @Value("#{@commonApiProjector.getAdminIpAccess(target.user.id)}")
    Set<IpAccess> getIpAccess();
    /**
     * 프로젝터를 통해 권한정보 리스트 조회
     * @return 권한정보 리스트
     */
    default List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        for (UserRole userRole : getUser().getUserRoles()) {
            roles.add(userRole.getRole());
        }
        return roles;
    }
}
