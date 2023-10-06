package com.dlsdlworld.spring.api.controller;

import com.dlsdlworld.spring.api.dto.TestTable;
import com.dlsdlworld.spring.api.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    @Resource(name="testService")
    TestService testService;

    @GetMapping("/page")
    public Iterable<TestTable> TestRestApi(){
        return testService.getTestTable();
    }

    @PostMapping("/page/test/page")
    public String TestRestApi2(){
        return "testData2";
    }
}
