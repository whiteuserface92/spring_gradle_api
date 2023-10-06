package com.dlsdlworld.spring.api.controller;

import com.dlsdlworld.spring.api.cacheRepository.AppCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/auth")
public class AdminAuthRestController extends AuthRestController {

    @Autowired
    public AdminAuthRestController(AppCacheRepository appCacheRepository) {
        super(appCacheRepository);
    }
}
