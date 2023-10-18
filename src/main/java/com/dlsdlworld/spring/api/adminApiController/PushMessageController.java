package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.PushMessageService;
import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.dto.PushMessageDTO;
import com.dlsdlworld.spring.api.types.ActionTypes;
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
public class PushMessageController {

    private PushMessageService pushMessageService;

    @Autowired
    public PushMessageController(PushMessageService pushMessageService) {

        this.pushMessageService = pushMessageService;
    }

    @Transactional
    @LogAdminExecution(code = ActionTypes.READ, descriptions = "푸시 메세지 조회")
    @GetMapping(value = "/pushMessages/search/findAllByKeyword")
    public Page<PushMessageDTO> getPrivilegeList(Pageable page
            , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt
            , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt
            , @RequestParam(required=false, defaultValue = "") String keyword) {

        Page<PushMessageDTO> pushList = pushMessageService.getPushList(startDt, endDt, keyword, page);

        return pushList;
    }

}
