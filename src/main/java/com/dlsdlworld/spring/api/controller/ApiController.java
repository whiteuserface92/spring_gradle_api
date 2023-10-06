package com.dlsdlworld.spring.api.controller;

import com.dlsdlworld.spring.api.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping("/test")
    public String apiTest(){
        log.info("apiTest");
        return "api Test Datas";
    }
}
