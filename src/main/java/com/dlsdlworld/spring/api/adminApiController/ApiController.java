package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @Resource(name="apiService")
    ApiService apiService;

    @GetMapping("/test")
    public String apiTest(){
        log.info("apiTest");
        return "api Test Datas";
    }
}
