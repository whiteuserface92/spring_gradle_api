package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.repository.AppFileCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/appFileCode")
@RestController
public class AppFileCodeController {

    @Autowired
    AppFileCodeRepository appFileCodeRepository;

    @Transactional
    @PostMapping("/getFileCode")
    public String getCodeFile(@RequestParam String appKind){
        return appFileCodeRepository.getFileCodeByAppKind(appKind);
    }

    @Transactional
    @PostMapping("/updateFileCode")
    public void updateCodeFile(@RequestParam String appKind, @RequestParam String fileCode){
        appFileCodeRepository.updateFileCodeByAppKind(appKind, fileCode);
        log.info("update appKind : {}, CodeFile : {}", appKind, fileCode);
    }
}
