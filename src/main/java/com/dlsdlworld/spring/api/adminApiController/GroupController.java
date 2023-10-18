package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.exception.ApiParamNotFoundException;
import com.dlsdlworld.spring.api.exception.EntityNotFoundException;
import com.dlsdlworld.spring.api.exception.HospitalIdAlreadyExistsException;
import com.dlsdlworld.spring.api.model.Group;
import com.dlsdlworld.spring.api.model.GroupHospital;
import com.dlsdlworld.spring.api.model.Hospital;
import com.dlsdlworld.spring.api.repository.GroupHospitalRepository;
import com.dlsdlworld.spring.api.repository.GroupRepository;
import com.dlsdlworld.spring.api.repository.HospitalRepository;
import com.dlsdlworld.spring.api.types.ActionTypes;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 그룹정보 커스텀 컨트롤러
 */
@Slf4j
@RestController
public class GroupController {

    /** 그룹 저장소*/
    private GroupRepository groupRepository;

    /** 그룹병원정보 저장소*/
    private GroupHospitalRepository groupHospitalRepository;

    /** 병원정보 저장소*/
    private HospitalRepository hospitalRepository;

    /**
     * 생성
     * @param groupRepository
     * @param groupHospitalRepository
     * @param hospitalRepository
     */
    @Autowired
    public GroupController(
        GroupRepository groupRepository,
        GroupHospitalRepository groupHospitalRepository,
        HospitalRepository hospitalRepository) {
        this.groupRepository = groupRepository;
        this.groupHospitalRepository = groupHospitalRepository;
        this.hospitalRepository = hospitalRepository;
    }

    /**
     * 병원정보 아이디 리스트로 그룹 다중 매핑
     * @param id group id
     * @param params hospital id set
     * @return
     */
    @Transactional
    @LogAdminExecution(code = ActionTypes.UPDATE, descriptions = "그룹 매핑")
    @PutMapping(value = {"/rest/groups/{id}/hospitals"})
    public Map<String, Object>  putGroupHospitals(
            @PathVariable(name = "id") Long id,
            @RequestBody Map params) {

        log.trace("params:{}", params);

        Long hospitalId = Long.parseLong(params.get("hospitalId").toString());
        String ovrdImgUrl = params.get("ovrdImgUrl").toString();
        //저장된 병원ID가 있는지 확인
        long count = groupHospitalRepository.countGroupHospital(hospitalId);
        log.trace("###Count {} ",count);
        if(count>0) {
            throw new HospitalIdAlreadyExistsException(hospitalId);
        }

        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No group found for the " + id));
        log.trace("found group:{}", group);
        if (params.isEmpty() || !params.containsKey("hospitalId")) {
            throw new ApiParamNotFoundException("/groups/" + id + "/hospitals", "no hospitalId");
        }

        //group에 매핑된 정보를 모두 삭제한다
//        log.trace("##deleteByGroup start");
//        groupHospitalRepository.deleteAllByGroupId(group.getId());
//        log.trace("##deleteByGroup end");


        AtomicInteger order = new AtomicInteger();
//        hospitalIds.forEach(hospitalId -> {
            Hospital hospital = hospitalRepository.findById(hospitalId)
                    .orElseThrow(() -> new EntityNotFoundException("No hospital found for the " + hospitalId));
            GroupHospital groupHospital = new GroupHospital();
            groupHospital.setGroup(group);
            groupHospital.setHospital(hospital);
            groupHospital.setDispOrd(String.valueOf(order.incrementAndGet()));
            groupHospital.setOvrdImgUrl(ovrdImgUrl);
//            newGroupHospital.add(groupHospital);
//        });

        GroupHospital result = groupHospitalRepository.save(groupHospital);
        List<Object> content = Lists.newArrayList();
//        for (GroupHospital groupHospital : result) {
            content.add(mappedHospital(result.getHospital()));
//        }

        return ImmutableMap.of("content", content);
    }

    private Map<String, Object> mappedHospital(Hospital hospital) {
        final Map<String, Object> row = new HashMap<>();
        row.put("id", hospital.getId());
        row.put("hospitalCd", hospital.getHospitalCd());
        row.put("serviceIp", hospital.getServiceIp());
        row.put("domainUrl", hospital.getDomainUrl());
        row.put("apiUrl", hospital.getApiUrl());
        row.put("enabled", hospital.getEnabled());
        row.put("fidoEnabled", hospital.getFidoEnabled());
        row.put("hospitalType", hospital.getHospitalType());

        return row;
    }
}
