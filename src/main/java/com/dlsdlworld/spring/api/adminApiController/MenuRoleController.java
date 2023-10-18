package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.exception.ApiParamNotFoundException;
import com.dlsdlworld.spring.api.exception.EntityNotFoundException;
import com.dlsdlworld.spring.api.model.HospitalMenu;
import com.dlsdlworld.spring.api.model.MenuRole;
import com.dlsdlworld.spring.api.model.Role;
import com.dlsdlworld.spring.api.repository.HospitalMenuRepository;
import com.dlsdlworld.spring.api.repository.MenuRoleRepository;
import com.dlsdlworld.spring.api.repository.RoleRepository;
import com.dlsdlworld.spring.api.types.ActionTypes;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 메뉴-역할 커스텀 컨드롤러
 */
@Slf4j
@RestController
@RequestMapping("/rest")
public class MenuRoleController {

    /** 병원별메뉴 저장소*/
    private HospitalMenuRepository hospitalMenuRepository;

    /** 역할정보 저장소*/
    private RoleRepository roleRepository;

    /** 역할 메뉴 설정*/
    private MenuRoleRepository menuRoleRepository;

    /**
     * 생성자
     * @param hospitalMenuRepository
     * @param roleRepository
     * @param menuRoleRepository
     */
    @Autowired
    public MenuRoleController(HospitalMenuRepository hospitalMenuRepository,
                              RoleRepository roleRepository,
                              MenuRoleRepository menuRoleRepository) {

        this.hospitalMenuRepository = hospitalMenuRepository;
        this.roleRepository = roleRepository;
        this.menuRoleRepository = menuRoleRepository;
    }

    /**
     * hospitalMenu에 role 다중 매핑
     * @param id
     * @param params
     */
    @Transactional
    @LogAdminExecution(code = ActionTypes.UPDATE, descriptions = "병원 메뉴 ROLE 수정")
    @PutMapping(value = "/hospitalMenus/{id}/menuRoles")
    public Map<String, Object> putMenuRoles(
            @PathVariable(name = "id") Long id,
            @RequestBody Map<String, Iterable<Long>> params) {
        log.trace("params:{}", params);
        log.trace("id:{}", id);
        //병원메뉴 정보 확인
        HospitalMenu hospitalMenu = hospitalMenuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No hospitalMenu found for the " + id));
        log.trace("find hospitalMenu:{}", hospitalMenu);
        log.trace("hospitalMenu.getMenuRoles size:{}", hospitalMenu.getMenuRoles().size());


        //파라미터에 매핑할 Role 정보가 있는지 확인
        if (params.isEmpty() || !params.containsKey("roleIds"))
            throw new ApiParamNotFoundException("putMenuRoles", "roleIds");
        //hospitalMenu와 매핑된 정보를 모두 삭제
        log.trace("##deleteAllByHospitalMenu start");
        menuRoleRepository.deleteAllByHospitalMenuId(hospitalMenu.getId());
//        menuRoleRepository.deleteAll(hospitalMenu.getMenuRoles());
        log.trace("##deleteAllByHospitalMenu end");

        Iterable<Long> roleIds = params.get("roleIds");
        List<MenuRole> newMenuRole = Lists.newArrayList();
        roleIds.forEach(roleId -> {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new EntityNotFoundException("No privilege found for the " + roleId));
            MenuRole menuRole = new MenuRole();
            menuRole.setHospitalMenu(hospitalMenu);
            menuRole.setRole(role);
            newMenuRole.add(menuRole);
        });

        log.trace("##newMenuRole {} ", newMenuRole);
        log.trace("##menuRoleRepository.saveAll start");
        Iterable<MenuRole> result = menuRoleRepository.saveAll(newMenuRole);
        log.trace("##menuRoleRepository.saveAll end");

        List<Map<String, Object>> content = Lists.newArrayList();
        for (MenuRole menuRole : result) {
            log.trace("menuRole.getId() : {} ", menuRole.getId());
            log.trace("menuRole.getRole().getRoleNm() : {} ", menuRole.getRole().getRoleNm());
            content.add(
                    ImmutableMap.of(
                            "id", menuRole.getId(),
                            "roleNm", menuRole.getRole().getRoleNm()
                    )
            );
        }

        return ImmutableMap.of("content", content);
    }

    @GetMapping(value = {"/hospitalMenuRoles/{id}/roles"})
    public Map<String, List<Long>> getHospitalMenuRoles(@PathVariable(name = "id") Long id) {

        Map<String, List<Long>> data = new HashMap<>();
        data.put("roleIds", menuRoleRepository.findMenuRoleHospitalMenuId(id));
        return data;
    }
}
