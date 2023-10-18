package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.exception.EntityNotFoundException;
import com.dlsdlworld.spring.api.model.Group;
import com.dlsdlworld.spring.api.model.GroupHospital;
import com.dlsdlworld.spring.api.model.Hospital;
import com.dlsdlworld.spring.api.model.HospitalMenu;
import com.dlsdlworld.spring.api.param.PostHospitalParam;
import com.dlsdlworld.spring.api.repository.GroupHospitalRepository;
import com.dlsdlworld.spring.api.repository.GroupRepository;
import com.dlsdlworld.spring.api.repository.HospitalMenuRepository;
import com.dlsdlworld.spring.api.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/rest")
public class HospitalController {

    private HospitalRepository hospitalRepository;

    private GroupHospitalRepository groupHospitalRepository;

    private GroupRepository groupRepository;

    private HospitalMenuRepository hospitalMenuRepository;

    @Autowired
    public HospitalController(
        HospitalRepository hospitalRepository,
        GroupHospitalRepository groupHospitalRepository,
        HospitalMenuRepository hospitalMenuRepository,
        GroupRepository groupRepository
        ) {
        this.hospitalRepository = hospitalRepository;
        this.groupHospitalRepository = groupHospitalRepository;
        this.hospitalMenuRepository = hospitalMenuRepository;
        this.groupRepository = groupRepository;
    }

    /**
     * 병원 기본정보 저장
     * @param param
     */
    @Transactional
    @PostMapping(value = "/hospitals")
    public ResponseEntity postHospital(@RequestBody @Valid PostHospitalParam param) {
        log.trace("postHospitalParam:{}", param);
//        //메뉴를 복사할 대상 병원 조회
//        final Hospital baseMenuHospital = hospitalRepository.findById(param.getBaseMenuHospitalId())
//                .orElseThrow(() -> new EntityNotFoundException("No hospital found for the " + param.getBaseMenuHospitalId()));
//
//        Hospital baseApiHospital = null;
//        //동일한 병원이면 가져올 필요 없음
//        if (param.getBaseMenuHospitalId() != param.getBaseApiHospitalId()) {
//            baseApiHospital = hospitalRepository.findById(param.getBaseApiHospitalId())
//                .orElseThrow(() -> new EntityNotFoundException("No hospital found for the " + param.getBaseApiHospitalId()));
//        } else {
//            baseApiHospital = baseMenuHospital;
//        }

        //기본 정보 저장
        final Hospital hospital = new Hospital();
        BeanUtils.copyProperties(param, hospital);


        final Hospital savedHospital = hospitalRepository.save(hospital);

        log.trace("savedHospital {} ", savedHospital);

        //병원 메뉴 복사(기초데이터가 없어서 옵션으로 변경함)
        if (param.getBaseMenuHospitalId() != null) {
            final Hospital baseMenuHospital = hospitalRepository.findById(param.getBaseMenuHospitalId())
                .orElseThrow(() -> new EntityNotFoundException("No hospital found for the " + param.getBaseMenuHospitalId()));

            final Set<HospitalMenu> hospitalMenus = baseMenuHospital.getHospitalMenus();
            final List<HospitalMenu> newHospitalMenus = new ArrayList<HospitalMenu>();
            HospitalMenu newHospitalMenu = null;
            log.trace("hospitalMenus : {}", hospitalMenus.size());
            log.trace("hospitalMenus : {}", hospitalMenus);

            for (HospitalMenu hospitalMenu : hospitalMenus) {
                newHospitalMenu = new HospitalMenu();
                newHospitalMenu.setProdCd(hospitalMenu.getProdCd());
                newHospitalMenu.setOvrdServiceUrl(hospitalMenu.getOvrdServiceUrl());
                newHospitalMenu.setOvrdNameCd(hospitalMenu.getOvrdNameCd());
                newHospitalMenu.setOvrdImgUrl(hospitalMenu.getOvrdImgUrl());
                newHospitalMenu.setMenuType(hospitalMenu.getMenuType());
                newHospitalMenu.setMenu(hospitalMenu.getMenu());
                newHospitalMenu.setLevel(hospitalMenu.getLevel());
                newHospitalMenu.setEnabled(hospitalMenu.getEnabled());
                newHospitalMenu.setDispOrd(hospitalMenu.getDispOrd());
                newHospitalMenu.setParent(hospitalMenu.getParent());
                newHospitalMenu.setHospital(savedHospital);
                newHospitalMenus.add(newHospitalMenu);
            }
            hospitalMenuRepository.saveAll(newHospitalMenus);
        }

        //병원과 그룹의 매핑은 필수가 아님
        if (param.getGroupId() != null && param.getGroupId() > 0) {
            log.trace("param.getGroupId() : {} ", param.getGroupId());
            Group group = groupRepository.findById(param.getGroupId())
                    .orElseThrow(() -> new EntityNotFoundException("No group found for the " + param.getGroupId()));

            GroupHospital groupHospital = new GroupHospital();
            groupHospital.setGroup(group);
            groupHospital.setHospital(savedHospital);
            groupHospital.setOvrdImgUrl(param.getOvrdImageUrl());
            groupHospital.setDispOrd("99"); //임시로 99로 세팅

            groupHospitalRepository.save(groupHospital);
        }

        URI uri = WebMvcLinkBuilder.linkTo(HospitalController.class).slash(savedHospital.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
