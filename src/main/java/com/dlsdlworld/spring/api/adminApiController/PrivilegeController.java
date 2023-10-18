package com.dlsdlworld.spring.api.adminApiController;


import com.dlsdlworld.spring.api.adminApiService.PrivilegeService;
import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.dto.PrivilegeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/rest")
public class PrivilegeController {


    private PrivilegeService privilegeService;

    @Autowired
    public PrivilegeController(PrivilegeService privilegeService) {

        this.privilegeService = privilegeService;
    }

    @Transactional
    @LogAdminExecution(descriptions = "권한 조회")
    @GetMapping(value = "/privileges/search/findAllByKeyword")
    public Page<PrivilegeDTO> getPrivilegeList(Pageable page
            , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt
            , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt
            , @RequestParam(required=false, defaultValue = "") String keyword) {

        Page<PrivilegeDTO> privilegeList = privilegeService.getPrivilegeList(startDt, endDt, keyword, page);

        return privilegeList;
    }
}
