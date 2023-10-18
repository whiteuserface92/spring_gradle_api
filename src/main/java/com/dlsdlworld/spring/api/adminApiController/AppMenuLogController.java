package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.AppMenuLogService;
import com.dlsdlworld.spring.api.dto.AppMenuLogDto;
import com.dlsdlworld.spring.api.model.AppMenuLog;
import com.dlsdlworld.spring.api.repository.AppMenuLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@Validated
@RestController
@RequestMapping("/rest")
public class AppMenuLogController {

    private AppMenuLogService appMenuLogService;
    private AppMenuLogRepository appMenuLogRepository;

    public AppMenuLogController(AppMenuLogService apiMenuLogService, AppMenuLogRepository appMenuLogRepository) {
        this.appMenuLogService = apiMenuLogService;
        this.appMenuLogRepository = appMenuLogRepository;
    }

    @Transactional
    @PostMapping(value = "/insAppMenuLog")
    public AppMenuLog insAppMenuLog(@RequestBody AppMenuLog appMenuLog) {
        return appMenuLogService.insertAppMenuLog(appMenuLog);
    }


    @Transactional
    @GetMapping(value = "/appmenulog/findAllByKeywordDto")
    public Page<AppMenuLogDto> findByCodeClsDetailList(Pageable page
            , @RequestParam(required=false, defaultValue = "") String keyword
            , @RequestParam(required=false, defaultValue = "1900-01-01") String startDt
            , @RequestParam(required=false, defaultValue = "1900-01-01") String endDt ) {
         Page<AppMenuLogDto> pages = appMenuLogRepository.findAllByKeywordDto(page, LocalDateTime.parse(startDt), LocalDateTime.parse(endDt) ,keyword );
       // Page<AppMenuLogDto> pages = appMenuLogRepository.findAllByKeywordDto(page, keyword );

        return pages;
    }

}
