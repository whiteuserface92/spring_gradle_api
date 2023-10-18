package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.exception.ApiParamNotFoundException;
import com.dlsdlworld.spring.api.exception.EntityNotFoundException;
import com.dlsdlworld.spring.api.model.Privilege;
import com.dlsdlworld.spring.api.model.Role;
import com.dlsdlworld.spring.api.model.RolePrivilege;
import com.dlsdlworld.spring.api.repository.PrivilegeRepository;
import com.dlsdlworld.spring.api.repository.RolePrivilegeRepository;
import com.dlsdlworld.spring.api.repository.RoleRepository;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 역할정보 커스텀 컨드롤러
 */
@Slf4j
@RestController
public class RolePrivilegeController {

    /** 역할정보 저장소*/
    private RoleRepository roleRepository;

    /** 권한정보 저장소*/
    private PrivilegeRepository privilegeRepository;

    /** 역할권한정보 저장소*/
    private RolePrivilegeRepository rolePrivilegeRepository;

    /**
     * 생성자
     * @param roleRepository
     * @param privilegeRepository
     * @param rolePrivilegeRepository
     */
    @Autowired
    public RolePrivilegeController(RoleRepository roleRepository,
                                   PrivilegeRepository privilegeRepository,
                                   RolePrivilegeRepository rolePrivilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.rolePrivilegeRepository = rolePrivilegeRepository;
    }

    /**
     * role에 privilege 다중 매핑
     * Transactional 어노테이션이 반드시 필요함
     * @param id
     * @param params
     * @return
     */
    @Transactional
    @PutMapping(value = "/rest/roles/{id}/privileges")
    public Map<String, Object> putRolePrivileges(
            @PathVariable(name = "id") Long id,
            @RequestBody Map<String, Iterable<Long>> params) {
        log.trace("params:{}", params);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No role found for the " + id));
        log.trace("find role:{}", role);
        if (params.isEmpty() || !params.containsKey("privilegeIds"))
            throw new ApiParamNotFoundException("putRolePrivileges", "privilegeIds");

        //role과 매핑된 정보를 모두 삭제한다(by ricky 2020.01.09)
        log.trace("##deleteByRole start");
        rolePrivilegeRepository.deleteAllByRoleId(role.getId());
        log.trace("##deleteByRole end");

        Iterable<Long> privilegeIds = params.get("privilegeIds");
        List<RolePrivilege> newRolePrivilege = Lists.newArrayList();
        privilegeIds.forEach(privilegeId -> {
            Privilege privilege = privilegeRepository.findById(privilegeId)
                    .orElseThrow(() -> new EntityNotFoundException("No privilege found for the " + privilegeId));
            RolePrivilege rolePrivilege = new RolePrivilege();
            rolePrivilege.setRole(role);
            rolePrivilege.setPrivilege(privilege);
            newRolePrivilege.add(rolePrivilege);
        });

        Iterable<RolePrivilege> result = rolePrivilegeRepository.saveAll(newRolePrivilege);
        List<Map<String, Object>> content = Lists.newArrayList();
        for (RolePrivilege rolePrivilege : result) {
            content.add(
                    ImmutableMap.of(
                            "id", rolePrivilege.getId(),
                            "privilegeNm", rolePrivilege.getPrivilege().getPrivilegeNm().name()
                    )
            );
        }

        return ImmutableMap.of("content", content);
    }
}
