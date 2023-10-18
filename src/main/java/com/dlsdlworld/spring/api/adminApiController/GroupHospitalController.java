package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.GroupHospitalService;
import com.dlsdlworld.spring.api.dto.GroupHospitalDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 그룹정보 커스텀 컨트롤러
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/30
 * Time : 19:27
 */
@Slf4j
@RestController
public class GroupHospitalController {

    private GroupHospitalService groupHospitalService;

    @Autowired
    public GroupHospitalController(GroupHospitalService groupHospitalService) {
        this.groupHospitalService = groupHospitalService;
    }

    @Transactional
    @GetMapping(value = {"/rest/groups/{id}/groupHospitals"})
    public Page<GroupHospitalDto> getGroupHospitals(Pageable page,
                                                    @PathVariable(name = "id") Long groupId) {

        return groupHospitalService.findByAllGroupHospitals(groupId, page);
    }

}
